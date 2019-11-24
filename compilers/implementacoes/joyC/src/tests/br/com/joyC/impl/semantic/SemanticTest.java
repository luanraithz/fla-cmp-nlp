package tests.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import main.br.com.joyC.impl.semantic.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Semantic parser test")
class SemanticTest {

    @Test
    @DisplayName("Prorama apenas com uma linha")
    void emptyProgram() {
        var entry = Utils.textFrom(
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
    @DisplayName("teste_01 da lista do trabalho")
    void test01() {
        var entry = Utils.fromFile("example01/entry.txt");
        try {
            var result = Parser.parse(entry);
            var expectedResult = Utils.fromFile("example01/output.txt");
            Assertions.assertEquals(expectedResult, result);
        } catch (LexicalError | SemanticError | SyntaticError lexicalError) {
            lexicalError.printStackTrace();
            fail("Shouldn't throw error");
        }
    }

    @Test
    @DisplayName("teste_02 da lista do trabalho")
    void test02() {
        var entry = Utils.fromFile("example02/entry.txt");
        try {
            var result = Parser.parse(entry);
            var expectedResult = Utils.fromFile("example02/output.txt");
            Assertions.assertEquals(expectedResult, result);
        } catch (LexicalError | SemanticError | SyntaticError lexicalError) {
            lexicalError.printStackTrace();
            fail("Shouldn't throw error");
        }
    }

    @Test
    @DisplayName("teste_03 da lista do trabalho")
    void test03() {
        var entry = Utils.fromFile("example03/entry.txt");
        try {
            var result = Parser.parse(entry);
            var expectedResult = Utils.fromFile("example03/output.txt");
            Assertions.assertEquals(expectedResult, result);
        } catch (LexicalError | SemanticError | SyntaticError lexicalError) {
            lexicalError.printStackTrace();
            fail("Shouldn't throw error");
        }
    }

    @Test
    @DisplayName("teste_04 da lista do trabalho")
    void test04() {
        var entry = Utils.fromFile("example04/entry.txt");
        try {
            var result = Parser.parse(entry);
            var expectedResult = Utils.fromFile("example04/output.txt");
            Assertions.assertEquals(expectedResult, result);
        } catch (LexicalError | SemanticError | SyntaticError lexicalError) {
            lexicalError.printStackTrace();
            fail("Shouldn't throw error");
        }
    }
}
