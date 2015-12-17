package luxoft;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileWriter {

    public static final String ENCODING = "UTF-8";
    private static String nl = System.getProperty("line.separator");

    public static void deleteExistingFile(String filePath) {
        FileUtils.deleteQuietly(new File(filePath));
    }

    public static void writeMapToFile(Map<?, ?> map, String filePath) throws IOException {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            FileWriter.writeLineToFile(filePath, entry.getKey() + " -> " + entry.getValue() + nl);
        }
    }

    public static void writeLineToFile(String filePath, String line) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), line, ENCODING, true);
    }
}
