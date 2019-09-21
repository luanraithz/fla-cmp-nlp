package br.com.joyC.test;

import br.com.joyC.impl.lexical.LexicalParser;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

class LexicalParserTest {

    @Test
    void parse() {
        var builder = new StringBuilder()
            .append(" I_bla_bla");
        var result = LexicalParser.parse(builder.toString());
        System.out.println(result);
        Assertions.assertEquals(result, null);
    }
}