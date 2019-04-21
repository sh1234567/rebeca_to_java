package org.rebecalang.modeltransformer.java.timedrebeca;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.BinaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.CastExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.DotPrimary;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Expression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FieldDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Literal;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.NonDetExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ParentSuffixPrimary;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.PlusSubExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.PrimaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecInstantiationPrimary;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.TermPrimary;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.TernaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.UnaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableDeclarator;
import org.rebecalang.compiler.modelcompiler.timedrebeca.objectmodel.TimedRebecaParentSuffixPrimary;
import org.rebecalang.compiler.utils.CodeCompilationException;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.ExceptionContainer;
import org.rebecalang.compiler.utils.Pair;
import org.rebecalang.compiler.utils.TypesUtilities;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer_2;
import org.rebecalang.modeltransformer.StatementTransformingException;
import org.rebecalang.modeltransformer.TransformingFeature;
import org.rebecalang.modeltransformer.java.Utilities;

public class CoreRebecaExpressionTransformer extends AbstractExpressionTransformer_2{
	static Integer i = 0;
	static Integer num = 1;
	private String modelName;
	private ReactiveClassDeclaration rc;
	private RebecaModel rebecaModel;
	private Map <Pair<String, String>, String> methodCalls = new HashMap<Pair<String, String>, String>();


	public CoreRebecaExpressionTransformer(Set<CompilerFeature> cFeatures,
			Set<TransformingFeature> tFeatures, ExceptionContainer container, String modelName, ReactiveClassDeclaration rc, RebecaModel rebecaModel) {
		super(cFeatures, tFeatures, container);
		this.modelName = modelName;
		this.rebecaModel = rebecaModel;
		this.rc = rc;
	}

	@Override
	public String translate(Expression expression, ExceptionContainer container, int fType) {
		String retValue = "";
		if (expression instanceof BinaryExpression) {
			BinaryExpression bExpression = (BinaryExpression) expression;
			String op = bExpression.getOperator();
			if (!(op.equals("=") && fType == 1)) {
			retValue = translate(bExpression.getLeft(), container, fType) +
					 " " + op + " " + translate(bExpression.getRight(), container, fType);
			}
			if (op.equals("=") && fType == 1) {
				retValue +=  "a.set" + translate(bExpression.getLeft(), container, fType) + "(" + translate(bExpression.getRight(), container, fType) +")";
			}
		} else if (expression instanceof UnaryExpression) {
			UnaryExpression uExpression = (UnaryExpression) expression;
			retValue = uExpression.getOperator() + " " + translate(uExpression.getExpression(), container, fType);
		} else if (expression instanceof Literal) {
			Literal lExpression = (Literal) expression;
			retValue = lExpression.getLiteralValue();
			if (retValue.equals("null"))
				retValue = "\"dummy\"";
		} else if (expression instanceof PlusSubExpression) {
				retValue = translate(((PlusSubExpression)expression).getValue(), container, fType) + 
						((PlusSubExpression)expression).getOperator();
		} else if (expression instanceof PrimaryExpression) {
			PrimaryExpression pExpression = (PrimaryExpression) expression;
			retValue = translatePrimaryExpression(pExpression, fType);
		} 
			else {
			container.addException(new StatementTransformingException("Unknown translation rule for expression type " 
					+ expression.getClass(), expression.getLineNumber(), expression.getCharacter()));
		}
		return retValue;
	}
	protected String translatePrimaryExpression(PrimaryExpression pExpression, int fType) {
		String retValue = "";
		if (pExpression instanceof DotPrimary) {
			DotPrimary dotPrimary = (DotPrimary) pExpression;
			retValue = translateDotPrimary(dotPrimary, fType);
		} else if (pExpression instanceof TermPrimary) {
			retValue = translatePrimaryTermExpression((TermPrimary) pExpression);
		} else {
			container.addException(new StatementTransformingException("Unknown translation rule for initializer type " 
					+ pExpression.getClass(), pExpression.getLineNumber(), pExpression.getCharacter()));
		}
		return retValue;
	}

	private String translateDotPrimary(DotPrimary dotPrimary, int fType) {
		// TODO Auto-generated method stub
		String retValue = "";
		if (!(dotPrimary.getLeft() instanceof TermPrimary) || !(dotPrimary.getRight() instanceof TermPrimary)) {
			container.addException(new StatementTransformingException("This version of transformer does not supprt " +
					"nested record access expression.", 
					dotPrimary.getLineNumber(), dotPrimary.getCharacter()));
		} else {
			if(TypesUtilities.getInstance().getSuperType(dotPrimary.getRight().getType()) == TypesUtilities.MSGSRV_TYPE) {
				retValue = mapToJAVAPublishing(dotPrimary, fType);
			} 
		}
		return retValue;
	}

	private String mapToJAVAPublishing(DotPrimary dotPrimary, int fType) {
		// TODO Auto-generated method stub
		String retValue = "";
		String receiver = "";
		
		TimedRebecaParentSuffixPrimary parentSuffixPrimary = (TimedRebecaParentSuffixPrimary) 
				((TermPrimary)dotPrimary.getRight()).getParentSuffixPrimary();
		
		if (((TermPrimary) dotPrimary.getLeft()).getName().equals("self"))
			receiver = "this.name";
		else
			receiver = "\"" + ((TermPrimary) dotPrimary.getLeft()).getName() + "\"";
		
		retValue += "Message msg" + num.toString() + " = new Message();\r\n" + 
				 "msg" + num.toString() + ".setMsgName(\"" + 
				((TermPrimary)dotPrimary.getRight()).getName() +
				"\");\r\n" + 
				"msg" + num.toString() + ".setSender(this.name);\r\n" + 
				"msg" + num.toString() + ".setReceiver(" +
				receiver +
				");\r\n" +
				"msg" + num.toString() + ".setAfter(" ; 
		if (parentSuffixPrimary.getAfterExpression() != null)
			retValue += "t + " + translate(parentSuffixPrimary.getAfterExpression(), container, fType);
		else 
			retValue += "t";
		retValue += ");\r\n" +
				"msg" + num.toString() + ".setDeadline(";
		if (parentSuffixPrimary.getDeadlineExpression() != null)
			retValue += "t + " + translate(parentSuffixPrimary.getDeadlineExpression(), container, fType);
	else 
		retValue += "t + 100000";
		retValue +=	");\r\n" ;
		if (fType == 1) {
				retValue += "s_2.getMessageQueue().add(msg" + num.toString() + ")";
		}
		else
			retValue += "mq.add(msg" + num.toString() + ")";
		num = num + 1;
		/* fill the ROS message fields with the arguments to be published */
		int argumentIndex = 0;
		
		for (Expression expression : ((TermPrimary)dotPrimary.getRight()).getParentSuffixPrimary().getArguments()) {
				ReactiveClassDeclaration toClass = null;
				TermPrimary toRebec = (TermPrimary)dotPrimary.getLeft();
				toClass = Utilities.findKnownReactiveClass(rc, toRebec.getName(), rebecaModel);
				String toMsgsrvName = ((TermPrimary)dotPrimary.getRight()).getName();
				MsgsrvDeclaration toMsgsrv = Utilities.findTheMsgsrv(toClass, toMsgsrvName);
				System.err.println(toMsgsrv.getName());
				String argumentName = toMsgsrv.getFormalParameters().get(argumentIndex).getName();
				retValue += "pubMsg" + i.toString() + "." + argumentName + " = " + translate(expression, container, fType) + ";" + NEW_LINE;
				argumentIndex ++;
				
		} 
		/*
		retValue += "pubMsg" + i.toString() + "." + "sender" + "=" + "sender" + ";" + NEW_LINE;			
		retValue += ((TermPrimary) dotPrimary.getLeft()).getName() + "_" + ((TermPrimary)dotPrimary.getRight()).getName() + "_pub"
				+ "." + "publish(" + "pubMsg" + i.toString() + ")" + ";" + NEW_LINE;
		*/
		i ++; /* to prevent from repeated names */
		/* end of publishing */
		
		/* storing the name of callee rebec and the name of called msgsrv in order to declare publishers */
		Pair<String, String> methodCall = new Pair<String, String>(
				((TermPrimary)dotPrimary.getLeft()).getName(), ((TermPrimary)dotPrimary.getRight()).getName() );
		System.out.println(methodCall.getSecond() + " published to " + methodCall.getFirst());
		methodCalls.put(methodCall, "");

		//ReactiveClassDeclaration rcd = (ReactiveClassDeclaration) TransformingContext.getInstance().lookupInContext("current-reactive-class");
		//retValue = ((TermPrimary) dotPrimary.getLeft()).getName();
		//String typeName = TypesUtilities.getTypeName(((TermPrimary) dotPrimary.getLeft()).getType());
		//System.out.println(typeName);
		return retValue;
	
	}

	private String translatePrimaryTermExpression(TermPrimary pExpression) {
		String retValue = "";
		retValue += pExpression.getName();

		/* to support arrays */
		for(Expression ex: pExpression.getIndices()) {
			retValue += "[" + translate(ex, container, 0) + "]";
		}
		
		/* to be handled later */
		if(pExpression.getName().equals("after")) {
			retValue = "";
		}

		return retValue;
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
