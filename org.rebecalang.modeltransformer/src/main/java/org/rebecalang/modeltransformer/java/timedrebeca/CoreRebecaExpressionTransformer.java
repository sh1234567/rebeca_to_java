package org.rebecalang.modeltransformer.java.timedrebeca;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Expression;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.ExceptionContainer;
import org.rebecalang.compiler.utils.Pair;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
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
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
