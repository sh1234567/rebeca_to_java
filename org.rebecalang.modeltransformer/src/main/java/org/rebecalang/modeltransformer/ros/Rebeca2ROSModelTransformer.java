package org.rebecalang.modeltransformer.ros;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.rebecalang.compiler.modelcompiler.RebecaCompiler;
import org.rebecalang.compiler.modelcompiler.SymbolTable;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.utils.CodeCompilationException;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.ExceptionContainer;
import org.rebecalang.compiler.utils.Pair;
import org.rebecalang.modeltransformer.AbstractModelTransformer;
import org.rebecalang.modeltransformer.TransformingFeature;
import org.rebecalang.modeltransformer.ros.timedrebeca.TimedRebecaModelTransformer;

public class Rebeca2ROSModelTransformer{
	private static Rebeca2ROSModelTransformer instance = new Rebeca2ROSModelTransformer();
	ExceptionContainer container = new ExceptionContainer();
	
	private Rebeca2ROSModelTransformer() {
	}
	
	public static Rebeca2ROSModelTransformer getInstance() {
			return instance;
	}
	
	public ExceptionContainer getExceptionContainer() {
		return container;
	}
	
	public void transformModel(File rebecaFile, File destinationLocation,
			Set<CompilerFeature> compilerFeatures,
			Set<TransformingFeature> transformingFeatures,
			CommandLine commandLine) {

		RebecaCompiler rebecaCompiler = new RebecaCompiler();
		this.container = new ExceptionContainer();

		Pair<RebecaModel, SymbolTable> model = rebecaCompiler.compileRebecaFile(rebecaFile, compilerFeatures);
		if (!container.getExceptions().isEmpty()) {
			return;
		}
		AbstractModelTransformer modelTransformer = null;
		model = rebecaCompiler.compileRebecaFile(rebecaFile, compilerFeatures);
		if (!container.getExceptions().isEmpty()) {
			return;
		}
		
		System.out.println("This point");
		for(ReactiveClassDeclaration rc : model.getFirst().getRebecaCode().getReactiveClassDeclaration()) {
			for(MsgsrvDeclaration msgsrv : rc.getMsgsrvs()) {
				System.err.println(rc.getName() + "&&&" + msgsrv.getName() );
			}
		}
		if (compilerFeatures.contains(CompilerFeature.PROBABILISTIC_REBECA)) {
		} else {
			modelTransformer = new TimedRebecaModelTransformer();
			String modelName = rebecaFile.getName().split("\\.")[0];
			
			modelTransformer.prepare(modelName, model.getFirst(), compilerFeatures,
					transformingFeatures, commandLine, destinationLocation,
					container);
		}
		if (compilerFeatures
				.contains(CompilerFeature.PROBABILISTIC_REBECA)) {
		} else {
		}
		try {
			try {
				modelTransformer.transformModel();
			} catch (CodeCompilationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			this.container.addException(e);
		}

	}
	
	
}
