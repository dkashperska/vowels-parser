package luxoft;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileWriter {

    public static final String ENCODING = "UTF-8";

    public static void deleteExistingFile(String filePath){
        FileUtils.deleteQuietly(new File(filePath));
    }

    public static void writeLineToFile(String filePath, String line) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), line, ENCODING, true);
    }
}
