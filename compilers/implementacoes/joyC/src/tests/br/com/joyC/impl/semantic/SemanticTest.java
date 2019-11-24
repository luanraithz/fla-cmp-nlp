package tests.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import main.br.com.joyC.impl.semantic.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Semantic parser test")
class SemanticTest {

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
}
