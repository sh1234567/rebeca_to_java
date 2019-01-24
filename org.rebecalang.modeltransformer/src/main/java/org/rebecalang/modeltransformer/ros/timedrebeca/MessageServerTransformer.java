package org.rebecalang.modeltransformer.ros.timedrebeca;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FormalParameterDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Type;
import org.rebecalang.compiler.utils.TypesUtilities;
import org.rebecalang.modeltransformer.ros.Rebeca2ROSModelTransformer;
import org.rebecalang.modeltransformer.ros.Rebeca2ROSTypesUtilities;

public class MessageServerTransformer{
	
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
	
	public void transform() {
		this.msgFileContent = getMsgFileContent();
		this.callbackFuncationSignature = getCallbackFunctionSignature();
		this.callbackFunctionBody = getCallbackFunctionBody();
	}
	
	public String getSubscriberDeclaration() {
		String retValue = "";
		return retValue;
	}
	
	public String getSubscriberDefinition() {
		String retValue = "";
		return retValue;
	}
	
	public String getMsgFileContent() {
		msgFileContent = "";
		msgFileContent += "string sender" + NEW_LINE;
		for(FormalParameterDeclaration param : msgsrv.getFormalParameters()) {
			//System.out.println(TypesUtilities.getTypeName(param.getType()));
			String rebecaType = TypesUtilities.getTypeName(param.getType());
			msgFileContent += Rebeca2ROSTypesUtilities.getROSMessageType(TypesUtilities.getTypeName(param.getType())) + " " + param.getName() + NEW_LINE;
		}
		return msgFileContent;
	}
	
	public String getCallbackFunctionSignature() {
		callbackFuncationSignature = "";
		callbackFuncationSignature += msgsrv.getName() + "Callback" + "(const " + 
		modelName + "::" + msgsrv.getName() + " &" + " thisMsg)";
		return callbackFuncationSignature;
	}
	
	public String getCallbackFunctionBody() {
		callbackFunctionBody = "";
		for(FormalParameterDeclaration param : msgsrv.getFormalParameters()) {
			callbackFunctionBody += "#define " + param.getName() + " " + "thisMsg." + param.getName() + NEW_LINE;
		}
		 callbackFunctionBody += statementTransformer.resolveBlockStatement(msgsrv.getBlock());
		 
		for(FormalParameterDeclaration param : msgsrv.getFormalParameters()) {
			callbackFunctionBody += "#undef " + param.getName() + NEW_LINE;
		}
		return callbackFunctionBody;
	}
	
	private String convertToROSType(Type type) {
		String ROSType = "";
		if(type == TypesUtilities.BOOLEAN_TYPE)
			ROSType = "bool";
		if(type == TypesUtilities.BYTE_TYPE)
			ROSType = "int8";
		if(type == TypesUtilities.CHAR_TYPE)
			ROSType = "uint8";
		if(type == TypesUtilities.INT_TYPE) {
			ROSType = "int64";
			System.out.println("int64");
		}
		if(type == TypesUtilities.DOUBLE_TYPE)
			ROSType = "float64";
		/*if(type == TypesUtilities.REACTIVE_CLASS_TYPE)
			ROSType = ""; */
		if(type == TypesUtilities.STRING_TYPE)
			ROSType = "string";
		System.out.println(ROSType);
		return ROSType;
	}
}
