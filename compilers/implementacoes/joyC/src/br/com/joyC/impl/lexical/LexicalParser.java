package br.com.joyC.impl.lexical;

import br.com.joyC.gaals.LexicalError;
import br.com.joyC.gaals.Lexico;
import br.com.joyC.gaals.Token;
import br.com.joyC.impl.lexical.models.LexemType;
import br.com.joyC.impl.lexical.models.Output;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class LexicalParser {

    static ArrayList<Output> parse(String content) {
        return new ArrayList<>(parse(new StringReader(content)));
    }

    static private List<Output> parse(StringReader reader) {
        return rawParse(reader).stream().map(s -> {
            var out = new Output();
            out.lexeme = s.getLexeme();
            out.position = s.getPosition();
            out.type = LexemType.fromInt(s.getId()).getDesc();
            return out;
        }).collect(Collectors.toList());
    }

    static private ArrayList<Token> rawParse(StringReader reader) {
        var lex = new Lexico();

        lex.setInput(reader);
        ArrayList<Token> entries = new ArrayList<Token>();
        try {
            Token t = null;
            while ( (t = lex.nextToken()) != null )
            {
                System.out.println(t.getLexeme() + " " + t.getPosition() + " " + t.getId());
                entries.add(t);
            }
        } catch (LexicalError e ) {
            System.err.println(e.getMessage() + "e" + e.getPosition());
        }

        return entries;
    }
}
