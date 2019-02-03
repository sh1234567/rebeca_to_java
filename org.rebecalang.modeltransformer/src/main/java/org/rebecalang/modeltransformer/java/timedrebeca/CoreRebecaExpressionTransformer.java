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
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.NonDetExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.PlusSubExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.PrimaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecInstantiationPrimary;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.TermPrimary;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.TernaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.UnaryExpression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableDeclarator;
import org.rebecalang.compiler.utils.CodeCompilationException;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.ExceptionContainer;
import org.rebecalang.compiler.utils.Pair;
import org.rebecalang.compiler.utils.TypesUtilities;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
import org.rebecalang.modeltransformer.StatementTransformingException;
import org.rebecalang.modeltransformer.TransformingFeature;

public class CoreRebecaExpressionTransformer extends AbstractExpressionTransformer{
	static Integer i = 0;
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
	public String translate(Expression expression, ExceptionContainer container) {
		String retValue = "";
		if (expression instanceof BinaryExpression) {
			BinaryExpression bExpression = (BinaryExpression) expression;
			String op = bExpression.getOperator();
			retValue =  translate(bExpression.getLeft(), container) +
					 " " + op + " " + translate(bExpression.getRight(), container);
		} else if (expression instanceof UnaryExpression) {
			UnaryExpression uExpression = (UnaryExpression) expression;
			retValue = uExpression.getOperator() + " " + translate(uExpression.getExpression(), container);
		} else if (expression instanceof Literal) {
			Literal lExpression = (Literal) expression;
			retValue = lExpression.getLiteralValue();
			if (retValue.equals("null"))
				retValue = "\"dummy\"";
		} else if (expression instanceof PlusSubExpression) {
				retValue = translate(((PlusSubExpression)expression).getValue(), container) + 
						((PlusSubExpression)expression).getOperator();
		} else if (expression instanceof PrimaryExpression) {
			PrimaryExpression pExpression = (PrimaryExpression) expression;
			retValue = translatePrimaryExpression(pExpression);
		} 
			else {
			container.addException(new StatementTransformingException("Unknown translation rule for expression type " 
					+ expression.getClass(), expression.getLineNumber(), expression.getCharacter()));
		}
		return retValue;
	}
	protected String translatePrimaryExpression(PrimaryExpression pExpression) {
		String retValue = "";
		if (pExpression instanceof DotPrimary) {
			DotPrimary dotPrimary = (DotPrimary) pExpression;
			//retValue = translateDotPrimary(dotPrimary);
		} else if (pExpression instanceof TermPrimary) {
			retValue = translatePrimaryTermExpression((TermPrimary) pExpression);
		} else {
			container.addException(new StatementTransformingException("Unknown translation rule for initializer type " 
					+ pExpression.getClass(), pExpression.getLineNumber(), pExpression.getCharacter()));
		}
		return retValue;
	}

	private String translatePrimaryTermExpression(TermPrimary pExpression) {
		String retValue = "";
		retValue += pExpression.getName();

		/* to support arrays */
		for(Expression ex: pExpression.getIndices()) {
			retValue += "[" + translate(ex, container) + "]";
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
