package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Set;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.utils.CompilerFeature;
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
				"\r\n" + 
				"}\r\n" + 
				"}";
		return retValue;
	}

}
