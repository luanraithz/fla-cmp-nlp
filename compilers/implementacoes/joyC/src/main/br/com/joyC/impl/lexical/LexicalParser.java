package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.Lexico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.lexical.models.LexemeType;
import main.br.com.joyC.impl.lexical.models.Output;
import main.br.com.joyC.impl.models.LexicalContentError;
import main.br.com.joyC.impl.utils.LexicalErrorParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

class LexicalParser {
    static List<Output> parse(String str) throws LexicalContentError {
        var arr = new ArrayList<Output>();
        try {
            for (Token t: rawParse(new StringReader(str))) {
                var out = new Output();
                out.position = t.getPosition();
                var lexeme = LexemeType.fromInt(t.getId());
                if (lexeme == LexemeType.t_palavraReservada) {
                    throw new LexicalError("Palavra reservada invalida", t.getPosition());
                }
                out.type = lexeme.getDesc();
                out.lexeme = t.getLexeme();
                out.line = countLines(str.substring(0, t.getPosition() + 1));
                arr.add(out);
            }
            return arr;
        } catch (LexicalError e) {
            throw LexicalErrorParser.toException(str, e);
        }
    }

    static private Integer countLines(String str) {
        return str.split(System.getProperty("line.separator")).length;
    }

    static private ArrayList<Token> rawParse(StringReader reader) throws LexicalError {
        var lex = new Lexico();

        lex.setInput(reader);
        ArrayList<Token> entries = new ArrayList<Token>();
        Token t;
        while ( (t = lex.nextToken()) != null )
        {
            entries.add(t);
        }

        return entries;
    }
}
