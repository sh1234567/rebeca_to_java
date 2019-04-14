package org.rebecalang.modeltransformer.java.timedrebeca;

public class DirCreator {

	public String getDirCreatorContent() {
		// TODO Auto-generated method stub
		String retValue = "import java.io.BufferedWriter;\r\n" + 
				"import java.io.File;\r\n" + 
				"import java.io.FileWriter;\r\n" + 
				"import java.io.IOException;\r\n" + 
				"import java.io.Writer;\r\n" + 
				"\r\n" + 
				"public class DirectoryCreator {\r\n" + 
				"	protected String dirPath;\r\n" + 
				"\r\n" + 
				"	public DirectoryCreator() {\r\n" + 
				"		this.dirPath = \"C:\\\\Users\\\\Fujitsu\\\\Desktop\";\r\n" + 
				"		this.createDirectory();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean createDirectory() {\r\n" + 
				"		boolean success = true;\r\n" + 
				"		File file = new File(dirPath);\r\n" + 
				"		file.mkdirs();\r\n" + 
				"		return success;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean addFile(String fileName, String fileContent) throws IOException {\r\n" + 
				"		boolean success = true;\r\n" + 
				"\r\n" + 
				"		/* create file */\r\n" + 
				"		String filePath = dirPath + File.separatorChar + fileName;\r\n" + 
				"		File file = new File(filePath);\r\n" + 
				"		try {\r\n" + 
				"			file.createNewFile();\r\n" + 
				"		} catch (IOException e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"			success = false;\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		/* fill the file */\r\n" + 
				"		try {\r\n" + 
				"			Writer writer = new FileWriter(file);\r\n" + 
				"			BufferedWriter bufferedWriter = new BufferedWriter(writer);\r\n" + 
				"			bufferedWriter.write(fileContent);\r\n" + 
				"			bufferedWriter.close();\r\n" + 
				"		} catch (IOException e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"			success = false;\r\n" + 
				"		}\r\n" + 
				"		return success;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
