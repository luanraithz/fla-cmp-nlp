package main.br.com.joyC.impl.lexic;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.Lexico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.lexic.models.LexemeType;
import main.br.com.joyC.impl.lexic.models.Output;
import main.br.com.joyC.impl.models.LexicalContentError;
import main.br.com.joyC.impl.utils.LexicalErrorParser;
import main.br.com.joyC.impl.utils.LineCounter;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Parser {
    public static List<Output> parse(String str) throws LexicalContentError {
        try {
            var raw = rawParse(new StringReader(str));
            var formatted = raw.stream().map(t -> {
                var out = new Output();
                out.position = t.getPosition();
                out.type = LexemeType.fromInt(t.getId());
                out.lexeme = t.getLexeme();
                out.line = LineCounter.count(str.substring(0, t.getPosition() + 1));
                return out;
            }).collect(Collectors.toList());
            var op = formatted.stream().filter(s -> s.type == LexemeType.t_palavraReservada).findFirst();
            if (op.isPresent()) {
                throw new LexicalError("palavra reservada invalida", op.get().position);
            }
            return formatted;
        } catch (LexicalError e) {
            throw LexicalErrorParser.toException(str, e);
        }
    }

    static private ArrayList<Token> rawParse(StringReader reader) throws LexicalError {
        var lex = new Lexico();

        lex.setInput(reader);
        ArrayList<Token> entries = new ArrayList<>();
        Token t;
        while ( (t = lex.nextToken()) != null )
        {
            entries.add(t);
        }

        return entries;
    }
}
