package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Set;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Annotation;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FieldDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableDeclarator;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
import org.rebecalang.modeltransformer.TransformingFeature;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaExpressionTransformer;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaStatementTransformer;

public class ReactiveClassTransformer {
	private ReactiveClassDeclaration rc;
	private String modelName;
	private RebecaModel rebecaModel;
	private CoreRebecaStatementTransformer statementTransformer;
	private CoreRebecaExpressionTransformer expressionTransformer;
	private String nodeName;

	public ReactiveClassTransformer(RebecaModel rebecaModel, ReactiveClassDeclaration rc, String modelName, AbstractExpressionTransformer expressionTransformer,
			Set<CompilerFeature> cFeatures, Set<TransformingFeature> tFeatures ) {
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
		if(! rc.getAnnotations().isEmpty()) {
			for(Annotation annot: rc.getAnnotations()) {
				if (annot.getIdentifier() == "topic")
					transformTopicClass();
			}
		}
	}

	private void transformTopicClass() {
		// TODO Auto-generated method stub
		for(FieldDeclaration fd: rc.getKnownRebecs()) {
			for(VariableDeclarator knowrebecDefinition : fd.getVariableDeclarators()) {
				
			}
		}
		
	}

	public String getCppFileContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHeaderFileContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
