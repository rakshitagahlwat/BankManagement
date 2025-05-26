import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static final String DIRECTORY = "data";
    private static final String FILE_PATH = DIRECTORY + "/accounts.txt";

    public static void initializeFile() {
        try {
            File dir = new File(DIRECTORY);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error while creating file or directory: " + e.getMessage());
        }
    }
}
