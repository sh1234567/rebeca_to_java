package org.rebecalang.modeltransformer.java.timedrebeca;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Annotation;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FieldDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FormalParameterDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableDeclarator;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.Pair;
import org.rebecalang.compiler.utils.TypesUtilities;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
import org.rebecalang.modeltransformer.TransformingFeature;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaExpressionTransformer;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaStatementTransformer;
import org.rebecalang.modeltransformer.java.timedrebeca.MessageServerTransformer;

public class ReactiveClassTransformer {
	public final static String NEW_LINE = "\r\n";
	public final static String TAB = "\t";
	public final static String QUOTE_MARK = "\"";
	public final static String SEMICOLON = ";";
	public final static String publishersQueueSize = "30";
	public final static String subscribersQueueSize = "30";

	private ReactiveClassDeclaration rc;
	private String modelName;
	private RebecaModel rebecaModel;
	private CoreRebecaStatementTransformer statementTransformer;
	private CoreRebecaExpressionTransformer expressionTransformer;
	private String nodeName;

	public ReactiveClassTransformer(RebecaModel rebecaModel, ReactiveClassDeclaration rc, String modelName,
			AbstractExpressionTransformer expressionTransformer, Set<CompilerFeature> cFeatures,
			Set<TransformingFeature> tFeatures) {
		this.statementTransformer = new CoreRebecaStatementTransformer(expressionTransformer, cFeatures, tFeatures);
		this.expressionTransformer = (CoreRebecaExpressionTransformer) expressionTransformer;
		this.rc = rc;
		this.modelName = modelName;
		this.rebecaModel = rebecaModel;
		this.nodeName = rc.getName() + "_node";
		prepare();
	}

	private void prepare() {
		// TODO Auto-generated method stub

	}

	public void transformReactiveClass() {
		// TODO Auto-generated method stub

		if (!rc.getAnnotations().isEmpty()) {
			for (Annotation annot : rc.getAnnotations()) {
				if (annot.getIdentifier() == "topic")
					transformTopicClass();
			}
		}
	}

	private void transformTopicClass() {
		// TODO Auto-generated method stub
		for (FieldDeclaration fd : rc.getKnownRebecs()) {
			for (VariableDeclarator knowrebecDefinition : fd.getVariableDeclarators()) {

			}
		}

	}

	public String getCppFileContent() {
		String retValue = "";
		// defining imports
		retValue += "public class " + rc.getName() + "{" + NEW_LINE;
		retValue += createVariablesDefinition();
		for (MsgsrvDeclaration msgsrv : rc.getMsgsrvs()) {
			System.out.println("msgsrv");
			MessageServerTransformer messageServerTransformer = new MessageServerTransformer(statementTransformer,
					msgsrv, modelName);
			retValue += "public void " + msgsrv.getName() + " (" + ")" + NEW_LINE + "{" + NEW_LINE
					+ messageServerTransformer.getCallbackFunctionBody() + NEW_LINE + "}" + NEW_LINE + NEW_LINE;
		}
		retValue += "}" + NEW_LINE + NEW_LINE;

		/*
		 * for(SynchMethodDeclaration method : rc.getSynchMethods()) { retValue +=
		 * transformSynchMethod(method); }
		 */
		return retValue;
	}

	private String createVariablesDefinition() {
		String variablesDefinition = "";
		// TODO Auto-generated method stub
		for(FieldDeclaration fd : rc.getStatevars()) {
			for(VariableDeclarator var : fd.getVariableDeclarators()) {
				variablesDefinition +=  "private " + statementTransformer.resolveVariableDeclaration(fd, var) + SEMICOLON + NEW_LINE;
			}
		}
		return variablesDefinition;
	}
	

	public String getHeaderFileContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
