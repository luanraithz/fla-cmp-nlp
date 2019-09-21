package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.impl.lexical.models.LexemeType;
import org.junit.jupiter.api.Test;

import static main.br.com.joyC.impl.lexical.LexicalParser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LexicalParserTest {

    @Test
    void idInt(){
        try {
            var result = parse("I_syop");
            assertEquals(1, result.size());
            var res = result.get(0);
            assertEquals(0, res.position);
            assertEquals("I_syop", res.lexeme);
            assertEquals(LexemeType.t_idInt.getDesc(), res.type);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void errorIdInt() {
        try {
            var result = parse("I_syop__");
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    void invalidIdInt() {
        try {
            var result = parse("I___op");
            fail();
        } catch (Exception ignored) { }
    }


    @Test
    void idString(){
        try {
            var lexeme = "S_syop";
            var result = parse(lexeme);
            assertEquals(1, result.size());
            var res = result.get(0);
            assertEquals(0, res.position);
            assertEquals(lexeme, res.lexeme);
            assertEquals(LexemeType.t_idString.getDesc(), res.type);
        } catch (Exception ignored) {
            fail();
        }
    }

    @Test
    void errorIdString() {

        try {
            var result = parse("S_syop__");
            fail();
        } catch (Exception ignored) { }
    }

    @Test
    void invalidIdString() {
        try {
            var result = parse("I___op");
            fail();
        } catch (Exception ignored) { }
    }


    void validReservedWord(String content) {
        try {
            var result = parse(content).get(0);
            assertEquals(LexemeType.t_palavraReservada.getDesc(), result.type);
            assertEquals(0, result.position);
            assertEquals(content, result.lexeme);
        } catch (Exception ignored) { }
    }

    @Test
    void testReservedWords() {
        validReservedWord("e");
        validReservedWord("ou");
        validReservedWord("enquantoVerdadeiro");
    }

    void invalidReservedWord(String content) {
        try {
            var result = parse(content).get(0);
            fail();
        } catch (Exception ex) { }
    }

    @Test
    void testInvalidReservedWords() {
        invalidReservedWord("E");
        invalidReservedWord("OU");
        try {
            var result = parse("enquantoVerdaDEiro");
            fail();
        } catch (Exception ignored) {}

        try {
            var result = parse("proGRAMA");
            fail();
        } catch (Exception ignored) { }
    }


}