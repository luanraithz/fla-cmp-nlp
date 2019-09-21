package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.impl.lexical.models.LexemType;
import org.junit.jupiter.api.Test;

import static main.br.com.joyC.impl.lexical.LexicalParser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LexicalParserTest {

    @Test
    void idInt(){
        var result = parse("I_syop");
        assertEquals(1, result.size());
        var res = result.get(0);
        assertEquals(0, res.position);
        assertEquals("I_syop", res.lexeme);
        assertEquals(LexemType.t_idInt.getDesc(), res.type);
    }

    @Test
    void errorIdInt() {
        // Should find one int identifier and a Lexical Error
        var result = parse("I_syop__");
        assertEquals(2, result.size());
        var res = result.get(0);
        assertEquals(0, res.position);
        assertEquals("I_syop", res.lexeme);
        assertEquals(LexemType.t_idInt.getDesc(), res.type);

        res = result.get(1);
        assertEquals("LEXICAL ERROR", res.lexeme);
        assertEquals(6, res.position);
        assertEquals("UNEXPECTED CHARACTER", res.type);
    }

    @Test
    void invalidIdInt() {
        // Should not recognize any identifier
        var result = parse("I___op");
        assertEquals(1, result.size());
        var res = result.get(0);
        assertEquals(0, res.position);
        assertEquals("LEXICAL ERROR", res.lexeme);
        assertEquals("UNEXPECTED CHARACTER", res.type);
    }


    @Test
    void idString(){
        var lexem = "S_syop";
        var result = parse(lexem);
        assertEquals(1, result.size());
        var res = result.get(0);
        assertEquals(0, res.position);
        assertEquals(lexem, res.lexeme);
        assertEquals(LexemType.t_idString.getDesc(), res.type);
    }

    @Test
    void errorIdString() {
        var lexem = "S_syop";
        var result = parse(lexem + "__");
        assertEquals(2, result.size());
        var res = result.get(0);
        assertEquals(0, res.position);
        assertEquals(lexem, res.lexeme);
        assertEquals(LexemType.t_idString.getDesc(), res.type);

        res = result.get(1);
        assertEquals("LEXICAL ERROR", res.lexeme);
        assertEquals(6, res.position);
        assertEquals("UNEXPECTED CHARACTER", res.type);
    }

    @Test
    void invalidIdString() {
        var result = parse("I___op");
        assertEquals(1, result.size());
        var res = result.get(0);
        assertEquals(0, res.position);
        assertEquals("LEXICAL ERROR", res.lexeme);
        assertEquals("UNEXPECTED CHARACTER", res.type);
    }


    void validReservedWord(String content) {
        var result = parse(content).get(0);
        assertEquals(LexemType.t_palavraReservada.getDesc(), result.type);
        assertEquals(0, result.position);
        assertEquals(content, result.lexeme);

    }

    @Test
    void testReservedWords() {
        validReservedWord("e");
        validReservedWord("ou");
        validReservedWord("enquantoVerdadeiro");
    }

    void invalidReservedWord(String content) {
        var result = parse(content).get(0);
        assertEquals("UNEXPECTED CHARACTER", result.type);
        assertEquals(0, result.position);
        assertEquals("LEXICAL ERROR", result.lexeme);
    }

    @Test
    void testInvalidReservedWords() {
        invalidReservedWord("E");
        invalidReservedWord("OU");
        var result = parse("enquantoVerdaDEiro");

        assertEquals(2, result.size());
        assertEquals(LexemType.t_palavraReservada.getDesc(), result.get(0).type);
        assertEquals(0, result.get(0).position);
        assertEquals("enquantoVerda", result.get(0).lexeme);

        assertEquals("UNEXPECTED CHARACTER", result.get(1).type);
        assertEquals(13, result.get(1).position);
        assertEquals("LEXICAL ERROR", result.get(1).lexeme);

        result = parse("proGRAMA");
        assertEquals(2, result.size());
        assertEquals(LexemType.t_palavraReservada.getDesc(), result.get(0).type);
        assertEquals(0, result.get(0).position);
        assertEquals("pro", result.get(0).lexeme);

        assertEquals("UNEXPECTED CHARACTER", result.get(1).type);
        assertEquals(3, result.get(1).position);
        assertEquals("LEXICAL ERROR", result.get(1).lexeme);
    }


}