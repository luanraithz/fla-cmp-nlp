package main.br.com.joyC.impl.syntatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void testEmptyEntry() {
        try {
            Parser.parse("");
        } catch (SyntaticError syntaticError) {
            assertEquals("encontrado fim de arquivo esperado main types", MessageTranslator.translate(syntaticError, ""));
        } catch (SemanticError | LexicalError semanticError) {
            fail();
        }
    }

    @Test
    void testExpression() {
        var entry = "main [ S_lo = 1 ++ ]";
        try {
            Parser.parse(entry);
        } catch (SyntaticError syntaticError) {
            assertEquals("", MessageTranslator.translate(syntaticError, entry));
        } catch (Exception semanticError) {
            fail();
        }
    }

}