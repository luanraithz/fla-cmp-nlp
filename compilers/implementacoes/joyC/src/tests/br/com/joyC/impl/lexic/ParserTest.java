package tests.br.com.joyC.impl.lexic;

import main.br.com.joyC.impl.lexic.models.LexemeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static main.br.com.joyC.impl.lexic.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Lexic parser")
class ParserTest {

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
            parse("I_syop__");
            fail();
        } catch (Exception ignored) { }
    }

    @Test
    void invalidIdInt() {
        try {
            parse("I___op");
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
            parse("S_syop__");
            fail();
        } catch (Exception ignored) { }
    }

    @Test
    void invalidIdString() {
        try {
            parse("I___op");
            fail();
        } catch (Exception ignored) { }
    }


    void validReservedWord(String content, LexemeType lexeme) throws Exception {
        var result = parse(content).get(0);
        assertEquals(lexeme.getDesc(), result.type);
        assertEquals(0, result.position);
        assertEquals(content, result.lexeme);
    }

    @Test
    void testReservedWords() {
        try {
            validReservedWord("isFalseDo", LexemeType.t_isFalseDo);
            validReservedWord("isTrueDo", LexemeType.t_isTrueDo);
            validReservedWord("main", LexemeType.t_main);
            validReservedWord("while", LexemeType.t_while);
            validReservedWord("types", LexemeType.t_types);
            validReservedWord("true", LexemeType.t_true);
            validReservedWord("output", LexemeType.t_output);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }

        // Should be case sensitive
        invalidReservedWord("isFalseDO");
        invalidReservedWord("isTRueDo");
        invalidReservedWord("maIn");
        invalidReservedWord("wHile");
        invalidReservedWord("Types");
        invalidReservedWord("True");
        invalidReservedWord("OUTPUT");

    }

    void invalidReservedWord(String content) {
        try {
            parse(content);
            fail();
        } catch (Exception ignored) { }
    }

    @Test
    void testInvalidReservedWords() {
        invalidReservedWord("E");
        invalidReservedWord("OU");
        try {
            parse("enquantoVerdaDEiro");
            fail();
        } catch (Exception ignored) {}

        try {
            parse("proGRAMA");
            fail();
        } catch (Exception ignored) {}
    }


}