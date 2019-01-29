package org.rebecalang.modeltransformer.java.timedrebeca;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.utils.CodeCompilationException;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.ExceptionContainer;
import org.rebecalang.modeltransformer.AbstractExpressionTransformer;
import org.rebecalang.modeltransformer.AbstractModelTransformer;
import org.rebecalang.modeltransformer.TransformingFeature;
import org.rebecalang.modeltransformer.java.packageCreator.JavaPackageCreator;
import org.rebecalang.modeltransformer.java.timedrebeca.CoreRebecaExpressionTransformer;
import org.rebecalang.modeltransformer.java.timedrebeca.ReactiveClassTransformer;
import org.rebecalang.modeltransformer.java.packageCreator.IncludeDirectoryCreator;
import org.rebecalang.modeltransformer.java.packageCreator.SrcDirectoryCreator;

public class TimedRebecaModelTransformer extends AbstractModelTransformer {
	@Override
	public void prepare(String modelName, RebecaModel rebecaModel, Set<CompilerFeature> compilerFeatures,
			Set<TransformingFeature> transformingFeatures, CommandLine commandLine, File destinationLocation,
			ExceptionContainer container) {
		super.prepare(modelName, rebecaModel, compilerFeatures, transformingFeatures, commandLine, destinationLocation,
				container);
	}

	@Override
	public void transformModel() throws IOException, CodeCompilationException {
		// TODO Auto-generated method stub
		/* configuration files */
		JavaPackageCreator javaPackageCreator = new JavaPackageCreator(destinationLocation, modelName, container);
		
		for (ReactiveClassDeclaration rc: rebecaModel.getRebecaCode().getReactiveClassDeclaration()) {
			AbstractExpressionTransformer expressionTransformer = null;
			expressionTransformer = new CoreRebecaExpressionTransformer(compilerFeatures, transformingFeatures, container, modelName, rc, rebecaModel);

			if(rc.getAnnotations().isEmpty()){
				ReactiveClassTransformer reactiveClassTransformer =
						new ReactiveClassTransformer(rebecaModel, rc, modelName, expressionTransformer, compilerFeatures, transformingFeatures);
				reactiveClassTransformer.transformReactiveClass();
				
				// header
				String headerFileContent = reactiveClassTransformer.getHeaderFileContent();
				IncludeDirectoryCreator includeDirCreator = new IncludeDirectoryCreator(destinationLocation, modelName, container);
				includeDirCreator.addFile(rc.getName() + ".h", headerFileContent);
				
				// cpp file content
				String cppFileContent = reactiveClassTransformer.getCppFileContent();
				SrcDirectoryCreator srcDirCreator = new SrcDirectoryCreator(destinationLocation, modelName, container);
				srcDirCreator.addFile(rc.getName() + ".cpp", cppFileContent);
				
			}

		}

	}
}
