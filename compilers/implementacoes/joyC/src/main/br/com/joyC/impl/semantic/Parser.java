package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.*;

import java.io.StringReader;

public class Parser {
    public static String parse(String input) throws SyntaticError, SemanticError, LexicalError {
        var semantic = new CustomSemantic();
        var lexical = new Lexico();
        var syntactic = new Sintatico();
        lexical.setInput(new StringReader(input));
        syntactic.parse(lexical, semantic);

        return semantic.getResult();
    }
}
