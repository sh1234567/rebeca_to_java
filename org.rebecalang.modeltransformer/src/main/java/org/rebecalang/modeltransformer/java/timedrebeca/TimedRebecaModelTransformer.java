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
		
		SrcDirectoryCreator srcDirCreator = new SrcDirectoryCreator(destinationLocation, modelName, container);
		
		MainFileCreator mainFileCreator = new MainFileCreator(rebecaModel, modelName, compilerFeatures, transformingFeatures);
		String mainFileContent = mainFileCreator.getMainFileContent();
		srcDirCreator.addFile("main.java", mainFileContent);
		
		TimerFileCreator timerFileCreator = new TimerFileCreator();
		String timerFileContent = timerFileCreator.getTimerFileContent();
		srcDirCreator.addFile("TimeClass.java", timerFileContent);
		
		MessageClassCreator messageClassCreator = new MessageClassCreator();
		String messageClassContent = messageClassCreator.getMessageClassContent();
		srcDirCreator.addFile("Message.java", messageClassContent);
		
		MsgQueueFileCreator msgQueueFileCreator = new MsgQueueFileCreator();
		String msgQueueFileContent = msgQueueFileCreator.getMsgQueueFileContent();
		srcDirCreator.addFile("MessageQueue.java", msgQueueFileContent);


		for (ReactiveClassDeclaration rc : rebecaModel.getRebecaCode().getReactiveClassDeclaration()) {
			System.out.println("reactive class");
			AbstractExpressionTransformer expressionTransformer = null;
			expressionTransformer = new CoreRebecaExpressionTransformer(compilerFeatures, transformingFeatures,
					container, modelName, rc, rebecaModel);

			if (rc.getAnnotations().isEmpty()) {
				ReactiveClassTransformer reactiveClassTransformer = new ReactiveClassTransformer(rebecaModel, rc,
						modelName, expressionTransformer, compilerFeatures, transformingFeatures);
				reactiveClassTransformer.transformReactiveClass();

				// create java file content
				String cppFileContent = reactiveClassTransformer.getCppFileContent();
				srcDirCreator.addFile(rc.getName() + ".java", cppFileContent);

			}

		}
		System.out.println("finished");

	}
}
