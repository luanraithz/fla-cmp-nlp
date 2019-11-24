package tests.br.com.joyC.impl.semantic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Utils {
    static String fromFile(String fileName) {
        var filePath = new File("").getAbsolutePath();
        File file = new File( filePath + "/src/tests/br/com/joyC/impl/semantic/examples/" + fileName);
        var output = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
                output.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();

    }
    static String textFrom(String... lines) {
        var buffer = new StringBuffer();
        for (String line: lines) {
            buffer.append(line).append("\n");
        }
        return buffer.toString();
    }
}
