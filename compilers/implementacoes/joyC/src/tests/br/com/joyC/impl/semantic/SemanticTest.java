package tests.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import main.br.com.joyC.impl.semantic.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Semantic parser test")
class SemanticTest {

    private String fromFile(String fileName) {
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
    private String textFrom(String... lines) {
        var buffer = new StringBuffer();
        for (String line: lines) {
            buffer.append(line).append("\n");
        }
        return buffer.toString();
    }

    @Test
    @DisplayName("Prorama apenas com uma linha")
    void emptyProgram() {
        var entry = textFrom(
                "main [",
                    "output (1);",
                "]"
        );
        try {
            var result = Parser.parse(entry);
            System.out.println(result);
        } catch (LexicalError | SyntaticError | SemanticError lexicalError) {
            lexicalError.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("teste_02 da lista do trabalho")
    void test02() {
        var entry = fromFile("example02/entry.txt");
        try {
            var result = Parser.parse(entry);
            var expectedResult = fromFile("example02/output.txt");
            Assertions.assertEquals(expectedResult, result);
        } catch (LexicalError | SemanticError | SyntaticError lexicalError) {
            lexicalError.printStackTrace();
            fail("Shouldn't throw error");
        }
    }

    @Test
    @DisplayName("test_03 da lista do trabalho")
    void test03() {
        var entry = fromFile("example03/entry.txt");
        try {
            var result = Parser.parse(entry);
            var expectedResult = fromFile("example03/output.txt");
            Assertions.assertEquals(expectedResult, result);
        } catch (LexicalError | SemanticError | SyntaticError lexicalError) {
            lexicalError.printStackTrace();
            fail("Shouldn't throw error");
        }
    }
}
