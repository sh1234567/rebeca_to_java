package TestCase2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DirectoryCreator {
	protected String dirPath;

	public DirectoryCreator() {
		this.dirPath = "C:\\Users\\Fujitsu\\Desktop";
		this.createDirectory();
	}

	public boolean createDirectory() {
		boolean success = true;
		File file = new File(dirPath);
		file.mkdirs();
		return success;
	}

	public boolean addFile(String fileName, String fileContent) throws IOException {
		boolean success = true;

		/* create file */
		String filePath = dirPath + File.separatorChar + fileName;
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}

		/* fill the file */
		try {
			Writer writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(fileContent);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

}
