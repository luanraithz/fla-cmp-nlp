package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.Lexico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.lexical.models.LexemType;
import main.br.com.joyC.impl.lexical.models.Output;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LexicalParser {

    public static ArrayList<Output> parse(String content) {
        return new ArrayList<>(parse(new StringReader(content)));
    }

    static private List<Output> parse(StringReader reader) {
        return rawParse(reader).stream().map(s -> {
            var out = new Output();
            out.position = s.getPosition();
            try {
                out.type = LexemType.fromInt(s.getId()).getDesc();
                out.lexeme = s.getLexeme();
            } catch (Exception e) {
                out.type = "UNEXPECTED CHARACTER";
                out.lexeme = "LEXICAL ERROR";
            }
            return out;
        }).collect(Collectors.toList());
    }

    static private ArrayList<Token> rawParse(StringReader reader) {
        var lex = new Lexico();

        lex.setInput(reader);
        ArrayList<Token> entries = new ArrayList<Token>();
        try {
            Token t;
            while ( (t = lex.nextToken()) != null )
            {
                entries.add(t);
            }
        } catch (LexicalError e ) {
            var t = new Token(9999, e.getMessage() ,e.getPosition());
            entries.add(t);
        }

        return entries;
    }
}
