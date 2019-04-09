package org.rebecalang.modeltransformer;

import java.util.Set;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.ExceptionContainer;

public class AbstractStatementTransformer_2 {
	public final static String NEW_LINE = "\r\n";
	protected final static String TAB = "\t";
	protected ExceptionContainer container = new ExceptionContainer();
	protected AbstractExpressionTransformer_2 expressionTransformer;
	protected Set<CompilerFeature> cFeatures;
	protected Set<TransformingFeature> tFeatures;

	public AbstractStatementTransformer_2(AbstractExpressionTransformer_2 expressionTranslator,
			Set<CompilerFeature> cFeatures, Set<TransformingFeature> tFeatures) {
		this.expressionTransformer = expressionTranslator;
		this.cFeatures = cFeatures;
		this.tFeatures = tFeatures;
	}
	
	public AbstractExpressionTransformer_2 getExpressionTranslator() {
		return expressionTransformer;
	}
	
	public ExceptionContainer getExceptionContainer() {
		return container;
	}
	


}
