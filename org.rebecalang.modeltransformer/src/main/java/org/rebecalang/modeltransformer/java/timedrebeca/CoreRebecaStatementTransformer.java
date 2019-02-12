package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Set;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.BlockStatement;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ConditionalStatement;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Expression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FieldDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.OrdinaryPrimitiveType;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.OrdinaryVariableInitializer;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Statement;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableDeclarator;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableInitializer;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
import org.rebecalang.modeltransformer.AbstractStatementTransformer;
import org.rebecalang.modeltransformer.StatementTransformingException;
import org.rebecalang.modeltransformer.TransformingFeature;
import org.rebecalang.modeltransformer.java.Rebeca2JavaTypesUtilities;

public class CoreRebecaStatementTransformer extends AbstractStatementTransformer{
	public CoreRebecaStatementTransformer(AbstractExpressionTransformer expressionTranslator,
			Set<CompilerFeature> cFeatures, Set<TransformingFeature> tFeatures) {
		super(expressionTranslator, cFeatures, tFeatures);
	}
	
	protected String resolveStatement(Statement statement) {
		if (statement instanceof BlockStatement) {
			return resolveBlockStatement((BlockStatement) statement);
		}
		
		if (statement instanceof ConditionalStatement) {
			return resolveIfStatement((ConditionalStatement) statement);
		}
		/*
		if (statement instanceof WhileStatement) {
			return resolveWhileStatement((WhileStatement) statement);
		}
		if (statement instanceof ForStatement) {
			return resolveForStatement((ForStatement)statement);
		}
		if (statement instanceof ReturnStatement) {
			return resolveReturnStatement((ReturnStatement) statement);
		}
		if (statement instanceof BreakStatement) {
			return resolveBreakStatement((BreakStatement)statement);
		}
		if (statement instanceof ContinueStatement) {
			return resolveContinueStatement((ContinueStatement) statement);
		}
		if (statement instanceof SwitchStatement) {
			return resolveSwitchStatement((SwitchStatement) statement);
		}
		if (statement instanceof FieldDeclaration) {
			return resolveFieldDeclaration((FieldDeclaration) statement);
		}
		*/
		if (statement instanceof Expression) {
			System.out.println(resolveExpression((Expression) statement));
			return (resolveExpression((Expression) statement) + ";");
		}
		
		container.addException(new StatementTransformingException("Unknown translation rule for type " + 
				statement.getClass(), statement.getLineNumber(), statement.getCharacter()));
		return "";
	}
	
	protected String resolveIfStatement(ConditionalStatement statement) {
		String retValue = "if (";
		retValue += resolveExpression(statement.getCondition()) + ") {" + NEW_LINE;
		if (statement.getStatement() != null)
			retValue += resolveStatement(statement.getStatement());
		retValue += "}";
		if (statement.getElseStatement() != null) {
			retValue += NEW_LINE + "else {" + NEW_LINE;
			retValue += resolveStatement(statement.getElseStatement());
			retValue += "}";
		}
		return retValue;
	}

	public String resolveBlockStatement(BlockStatement blockStatement) {
		String retValue = "";
		for (Statement statement : blockStatement.getStatements()) {
			retValue += resolveStatement(statement) + NEW_LINE;
		}
		retValue += NEW_LINE;
		return retValue;
	}
	protected String resolveExpression(Expression expression) {
		return expressionTransformer.translate(expression, container);
	}

	public String resolveVariableDeclaration(FieldDeclaration fd, VariableDeclarator vd) {
		// TODO Auto-generated method stub
		String retValue = "";
		VariableInitializer variableInitializer = vd.getVariableInitializer();
		
		if(fd.getType() instanceof OrdinaryPrimitiveType) {
			retValue += Rebeca2JavaTypesUtilities.getCorrespondingJavaType(fd.getType()) +
			" " + vd.getVariableName();
			if(variableInitializer != null && variableInitializer instanceof OrdinaryVariableInitializer) {
				String value = resolveExpression(((OrdinaryVariableInitializer)variableInitializer).getValue());
				retValue += " = " + value;
			}
		}
		return retValue;
	}


}
