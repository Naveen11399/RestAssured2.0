package api.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class FileToBase64Converter {

	public static String fileToBase64(File file) throws IOException {

		byte[] fileContent = Files.readAllBytes(file.toPath());
		String base64EncodedString = Base64.getEncoder().encodeToString(fileContent);

		return base64EncodedString;
	}

}
