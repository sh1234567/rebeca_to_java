package org.rebecalang.modeltransformer.java.packageCreator;

import java.io.File;

import org.rebecalang.compiler.utils.ExceptionContainer;

public class JavaPackageCreator {
	protected String dirPath;
	protected String javaPackagePath;
	protected ExceptionContainer container;

	public JavaPackageCreator(File destinationLocation, String modelName, ExceptionContainer container) {
		// TODO Auto-generated constructor stub
		this.javaPackagePath = destinationLocation.getAbsolutePath() + File.separatorChar + modelName;
		this.dirPath = javaPackagePath;
		this.container = container;
		this.createDirectory();
	}

	private boolean createDirectory() {
		// TODO Auto-generated method stub
		boolean success = true;
		File file = new File(dirPath);
		file.mkdirs();
		return success;
	}

}
