package tests.br.com.joyC.impl.lexic;

import main.br.com.joyC.impl.lexic.models.LexemeType;
import main.br.com.joyC.impl.models.LexicalContentError;
import main.br.com.joyC.impl.utils.LexicalErrorParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

import static main.br.com.joyC.impl.lexic.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Lexic parser")
class ParserTest {

    @Test
    @DisplayName("Valid int identifier")
    void validIntIdentifier(){
        try {
            var result = parse("I_syop");
            assertEquals(1, result.size());
            var res = result.get(0);
            assertEquals(0, res.position);
            assertEquals("I_syop", res.lexeme);
            assertEquals(LexemeType.t_idInt, res.type);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("Invalid int identifier")
    void invalidIntIdentifier() {
        var id = "I___op";
        try {
            parse(id);
            fail();
        } catch (LexicalContentError lexicalContentError) {
            var expectedMessage = MessageFormat.format( "Erro na linha 1 - {0} {1}", id, LexicalErrorParser.INVALID_INT_ID);
            assertEquals( expectedMessage,lexicalContentError.getMessage());
        }
    }


    @Test
    @DisplayName("Valid string identifier")
    void validStringIdentifier(){
        try {
            var lexeme = "S_syop";
            var result = parse(lexeme);
            assertEquals(1, result.size());
            var res = result.get(0);
            assertEquals(0, res.position);
            assertEquals(lexeme, res.lexeme);
            assertEquals(LexemeType.t_idString, res.type);
        } catch (Exception ignored) {
            fail();
        }
    }

    @Test
    @DisplayName("Invalid string identifier")
    void invalidStringIdentifier() {
        var id = "SxSyop";
        try {
            parse(id);
            fail("should have thrown erro with an invalid string identifier");
        } catch (LexicalContentError err) {
            var expectedMessage = MessageFormat.format( "Erro na linha 1 - {0} {1}", id, LexicalErrorParser.INVALID_STRING_ID);
            assertEquals( expectedMessage, err.getMessage());
        }
    }

    void validReservedWord(String content, LexemeType lexeme) throws Exception {
        var result = parse(content).get(0);
        assertEquals(lexeme, result.type);
        assertEquals(0, result.position);
        assertEquals(content, result.lexeme);
    }

    @Test
    @DisplayName("Valid reserved words")
    void validReservedWords() {
        try {
            validReservedWord("isFalseDo", LexemeType.t_isFalseDo);
            validReservedWord("isTrueDo", LexemeType.t_isTrueDo);
            validReservedWord("main", LexemeType.t_main);
            validReservedWord("while", LexemeType.t_while);
            validReservedWord("types", LexemeType.t_types);
            validReservedWord("true", LexemeType.t_true);
            validReservedWord("output", LexemeType.t_output);
        } catch (Exception e){
            fail();
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("Invalid reserved word (case sensitive)")
    void invalidReservedWordsCaseSensitive() {
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
    @DisplayName("Invalid reserved words")
    void invalidReservedWords() {
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