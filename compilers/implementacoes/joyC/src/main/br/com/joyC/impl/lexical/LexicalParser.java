package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.Lexico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.lexical.models.LexemType;
import main.br.com.joyC.impl.lexical.models.Output;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class LexicalParser {

    static public List<Output> parse(String str) throws LexicalContentError {
        var arr = new ArrayList<Output>();
        try {
            for (Token t: rawParse(new StringReader(str))) {
                var out = new Output();
                out.position = t.getPosition();
                out.type = LexemType.fromInt(t.getId()).getDesc();
                out.lexeme = t.getLexeme();
                arr.add(out);
            }
            return arr;
        } catch (LexicalError e) {
            // Parse position to line
            var position = e.getPosition();
            var line = countLines(str.substring(0, position));
            var lexem = String.valueOf(str.charAt(position));
            var message = "Erro na linha " + line + " - " + lexem +  " " + e.getMessage();
            throw new LexicalContentError(message, position, line, lexem);
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
