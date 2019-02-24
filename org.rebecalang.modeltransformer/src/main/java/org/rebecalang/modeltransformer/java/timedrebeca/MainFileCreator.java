package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Set;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MainRebecDefinition;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.OrdinaryPrimitiveType;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.utils.CodeCompilationException;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.TypesUtilities;
import org.rebecalang.modeltransformer.TransformingFeature;

public class MainFileCreator {
	public final static String NEW_LINE = "\r\n";
	public final static String TAB = "\t";
	private String modelName;
	private RebecaModel rebecaModel;
	private CoreRebecaStatementTransformer statementTransformer;
	private CoreRebecaExpressionTransformer expressionTransformer;

	public MainFileCreator(RebecaModel rebecaModel, String modelName, Set<CompilerFeature> compilerFeatures,
			Set<TransformingFeature> transformingFeatures) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
		this.rebecaModel = rebecaModel;
	}

	public String getMainFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "public class Main{\r\n" + 
				"public static void main(String[] args){\r\n" + 
				defineRebecs() +
				"	\r\n" + 
				"}\r\n" + 
				"}";
		return retValue;
	}

	private String defineRebecs() {
		// TODO Auto-generated method stub
		String retValue = "";
		for(MainRebecDefinition md: rebecaModel.getRebecaCode().getMainDeclaration().getMainRebecDefinition()) {
			try {
				ReactiveClassDeclaration metaData = TypesUtilities.getInstance().getMetaData(md.getType());
				retValue +=  metaData.getName() + " " + md.getName() + " = new " + metaData.getName() + "(\"" + md.getName() +"\");" + NEW_LINE;
			} catch (CodeCompilationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return retValue;
	}

}
