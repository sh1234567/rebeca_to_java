package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Set;

import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
import org.rebecalang.modeltransformer.AbstractStatementTransformer;
import org.rebecalang.modeltransformer.TransformingFeature;

public class CoreRebecaStatementTransformer extends AbstractStatementTransformer{
	public CoreRebecaStatementTransformer(AbstractExpressionTransformer expressionTranslator,
			Set<CompilerFeature> cFeatures, Set<TransformingFeature> tFeatures) {
		super(expressionTranslator, cFeatures, tFeatures);
	}

}
