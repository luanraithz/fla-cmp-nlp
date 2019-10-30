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

public class Parser {
    public static List<Output> parse(String str) throws LexicalContentError {
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
                out.line = LineCounter.count(str.substring(0, t.getPosition() + 1));
                arr.add(out);
            }
            return arr;
        } catch (LexicalError e) {
            throw LexicalErrorParser.toException(str, e);
        }
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
