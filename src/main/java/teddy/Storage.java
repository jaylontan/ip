package teddy;

import java.io.*;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Writes the task input by user into teddy.txt
    public void writeToFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(input);
        fw.write("\n");
        fw.close();
    }
}
