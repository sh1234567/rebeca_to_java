package org.rebecalang.modeltransformer.java.timedrebeca;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FormalParameterDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaStatementTransformer;


public class MessageServerTransformer {
	public final static String NEW_LINE = "\r\n";
	private MsgsrvDeclaration msgsrv;
	private String modelName;
	private String msgFileContent;
	private String callbackFuncationSignature;
	private String callbackFunctionBody;
	public CoreRebecaStatementTransformer statementTransformer;
	private ReactiveClassDeclaration rc;

	public MessageServerTransformer(CoreRebecaStatementTransformer statementTransformer, MsgsrvDeclaration msgsrv,
			String modelName, ReactiveClassDeclaration rc) {
		this.msgsrv = msgsrv;
		this.modelName = modelName;
		this.statementTransformer = statementTransformer;
		this.rc = rc;
	}

	public String getCallbackFunctionSignature() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCallbackFunctionBody() {
		// TODO Auto-generated method stub
		callbackFunctionBody = "Cloner cloner = new Cloner();\r\n" + 
				"State s_2 = cloner.deepClone(s_1);\r\n"
				+ "MessageQueue<Message> mq = new MessageQueue<Message>();\r\n" + "mq = s_1.getMessageQueue().clone();\r\n" + 
						"Actors[] actors = s_1.getActors().clone();\r\n" + rc.getName() + " a = (" + rc.getName() + ") actors[id].clone();\r\n";
		for (FormalParameterDeclaration param : msgsrv.getFormalParameters()) {
			System.out.println("param.getName()");
			// callbackFunctionBody += "#define " + param.getName() + " " + "thisMsg." +
			// param.getName() + NEW_LINE;
		}
		callbackFunctionBody += statementTransformer.resolveBlockStatement(msgsrv.getBlock(), 1);

		callbackFunctionBody += "s_2.setMessageQueue(mq);\r\n" + "actors[id] = a;\r\n" + "s_2.setActors(actors);\r\n" + "return s_2;\r\n";
		return callbackFunctionBody;
	}

}
