package main.br.com.joyC.impl.syntactic;

import main.br.com.joyC.gaals.*;

import java.io.StringReader;

class Parser {
    static String parse(String input) throws SyntaticError, SemanticError, LexicalError {
        var semantic = new Semantico();
        var lexical = new Lexico();
        var syntatic = new Sintatico();
        lexical.setInput(new StringReader(input));
        syntatic.parse(lexical, semantic);

        return "Programa compilado com sucesso";
    }
}
