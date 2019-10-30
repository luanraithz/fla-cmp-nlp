package main.br.com.joyC.impl.syntactic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import org.junit.jupiter.api.Test;

/*
 * Tests for the syntactic parser
 */
class ParserTest {

    private String build(String... lines) {
        var buffer = new StringBuffer();
        for (String line: lines) {
            buffer.append(line).append("\n");
        }

        return buffer.toString();
    }

    @Test
    void testEmptyEntry() {
        try {
            Parser.parse("");
            fail("Should throw and error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 1 - encontrado fim de arquivo esperado main types", MessageTranslator.translate(syntacticError, ""));
        } catch (SemanticError | LexicalError er) {
            fail("Should throw a syntacticError", er);
        }
    }

    @Test
    void testInvalidEntry() {
        var entry = build("", "S_i");
        try {
            Parser.parse(entry);
            fail("Should throw and error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 2 - encontrado S_i esperado main types", MessageTranslator.translate(syntacticError, entry));
        } catch (SemanticError | LexicalError er) {
            fail("Should throw a syntacticError", er);
        }
    }

    @Test
    void testExpression1() {
        var entry = "main [ S_lo = 1 ++ ]";
        try {
            Parser.parse(entry);
            fail("Should throw an error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 1 - encontrado ] esperada expressão", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Should throw a syntacticError", er);
        }
    }

    @Test
    void testExpression() {
        var entry = this.build(
            "main [",
            "S_lo = () ]",
            "");
        try {
            Parser.parse(entry);
            fail("Should throw an error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 2 - encontrado ) esperada expressão", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Should throw a syntacticError", er);
        }
    }

    @Test
    void testExpression2() {
        var entry = this.build(
                "main [",
                "S_lo = (+) ]",
                "");
        try {
            Parser.parse(entry);
            fail("Should throw an error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 2 - encontrado ) esperada expressão", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Should throw a syntacticError", er);
        }
    }

    @Test
    void testSuccess() {
        var entry = this.build(
            "main [",
            "  output (\"Qual o valor da base e altura\") ;",
            "  input (I_base, I_altura) ;",
            "  F_area = I_base * I_altura / 2 ;",
            "  output (F_area) ;",
            "]"
        );
        try {
            var result = Parser.parse(entry);
            assertEquals("Programa compilado com sucesso", result);
        } catch (SyntaticError | LexicalError | SemanticError er) {
            fail("Program should compile", er);
        }
    }

    @Test
    void testEndProgram() {
        var entry = this.build(
                "main [",
                "  output (\"Qual o valor da base e altura\") ;",
                "] S_i"
        );
        try {
            Parser.parse(entry);
            fail("Program should throw an syntatic error");
        } catch (SyntaticError err) {
            assertEquals("Erro na linha 3 - encontrado S_i esperado fim de arquivo", MessageTranslator.translate(err, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Program should throw an syntatic error", er);
        }
    }

    @Test
    void testEmptyOutput() {
        var entry = this.build(
                "main [",
                "  output () ;",
                "]"
        );
        try {
            Parser.parse(entry);
            fail();
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 2 - encontrado ) esperada expressão", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Program should throw a syntatic error", er);
        }
    }

    @Test
    void testOneInvalidCommand() {

        var entry = this.build(
                "main [",
                "  output (\"Q\")",
                "]"
        );
        try {
            Parser.parse(entry);
            fail();
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 3 - encontrado ] esperado ;", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Program should throw a syntatic error", er);
        }
    }

    @Test
    void testProgramList() {
        var entry = this.build(
                "main [",
                "]"
        );
        try {
            Parser.parse(entry);
            fail();
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 2 - encontrado ] esperado identificador if while output input :", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Program should throw a syntatic error", er);
        }
    }

    @Test
    void testWhile() {
        var entry = this.build(
                "main [",
                "output (2 == 2)",
                "]"
        );
        try {
            Parser.parse(entry);
            fail("Should throw a syntatic error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 3 - encontrado ] esperado ;", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Program should throw a syntatic error", er);
        }
    }

    @Test
    void fileEnd()  {
        var entry = this.build(
                "main [",
                "output (2 == 2);",
                ""
        );
        try {
            Parser.parse(entry);
            fail("Should throw a syntatic error");
        } catch (SyntaticError syntacticError) {
            assertEquals("Erro na linha 2 - encontrado fim de arquivo esperado input output while if identificador ]", MessageTranslator.translate(syntacticError, entry));
        } catch (LexicalError | SemanticError er) {
            fail("Program should throw a syntatic error", er);
        }
    }


}