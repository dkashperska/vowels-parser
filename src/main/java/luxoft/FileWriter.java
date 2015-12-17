package luxoft;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Odarka
 * Date: 16.12.15
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public class FileWriter {

    public static final String ENCODING = "UTF-8";

    public static void writeLineToFile(String filePath, String line) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), line, ENCODING, true);
    }
}
