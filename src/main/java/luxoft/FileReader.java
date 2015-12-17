package luxoft;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static final String ENCODING = "UTF-8";

    public static List<String> readFromFile(String filePath) throws IOException {
        return FileUtils.readLines(new File(filePath), ENCODING);
    }
}
