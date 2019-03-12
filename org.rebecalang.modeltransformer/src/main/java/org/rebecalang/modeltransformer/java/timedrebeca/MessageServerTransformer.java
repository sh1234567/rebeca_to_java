package org.rebecalang.modeltransformer.java.timedrebeca;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FormalParameterDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaStatementTransformer;

public class MessageServerTransformer {
	public final static String NEW_LINE = "\r\n";
	private MsgsrvDeclaration msgsrv;
	private String modelName;
	private String msgFileContent;
	private String callbackFuncationSignature;
	private String callbackFunctionBody;
	public CoreRebecaStatementTransformer statementTransformer;
	
	
	public MessageServerTransformer(CoreRebecaStatementTransformer statementTransformer,
			MsgsrvDeclaration msgsrv, String modelName) {
		this.msgsrv = msgsrv;
		this.modelName = modelName;
		this.statementTransformer = statementTransformer;
	}


	public String getCallbackFunctionSignature() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCallbackFunctionBody() {
		// TODO Auto-generated method stub
		callbackFunctionBody = "State s_2 = (State) s_1.clone();\r\n";
		for(FormalParameterDeclaration param : msgsrv.getFormalParameters()) {
			System.out.println("param.getName()");
			//callbackFunctionBody += "#define " + param.getName() + " " + "thisMsg." + param.getName() + NEW_LINE;
		}
		 callbackFunctionBody += statementTransformer.resolveBlockStatement(msgsrv.getBlock());
		 
		 callbackFunctionBody += "return s_2;\r\n";
		return callbackFunctionBody;
	}

}
