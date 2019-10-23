package main.br.com.joyC.impl.syntatic;

import main.br.com.joyC.gaals.*;

public class Parser {
    static public String parse(String input) {
        var semantic = new Semantico();
        var lexical = new Lexico();
        var syntatic = new Sintatico();

        try {
            syntatic.parse(lexical, semantic);
        } catch (LexicalError lexicalError) {
            lexicalError.printStackTrace();
        } catch (SemanticError semanticError) {
            semanticError.printStackTrace();
        } catch (SyntaticError syntaticError) {
            syntaticError.printStackTrace();
        }

        return "";
    }
}
