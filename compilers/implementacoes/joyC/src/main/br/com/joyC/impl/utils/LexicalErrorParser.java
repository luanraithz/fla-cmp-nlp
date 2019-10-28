package main.br.com.joyC.impl.utils;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.impl.models.LexicalContentError;

public class LexicalErrorParser {
    static private String translate(String str) {
        switch (str) {
            case "Erro identificando string":
                return "Constante string inválida";
            case "Erro identificando simboloEspecial":
                return "Simbolo especial inválido";
            case "Erro identificando idBool":
                return "Identificador boolean inválido";
            case "Erro identificando idComposto":
                return "Identificador composto inválido";
            case "Erro identificando idFloat":
                return "Identificador float inválido";
            case "Erro identificando idInt":
                return "Identificador int inválido";
            case "Erro identificando idString":
                return "Identificador string inválido";
            case "Erro identificando float":
                return "Constante float inválida";
            case "Erro identificando palavraReservada":
                return "Palavra reservada inválida";
            default:
                return str;
        }
    }
    public static LexicalContentError toException(String entry, LexicalError er) {
        var position = er.getPosition();
        var line = LineCounter.count(entry.substring(0, position + 1));
        var lexeme = String.valueOf(entry.charAt(position));
        var message = "Erro na linha " + line + " - " + lexeme +  " " + translate(er.getMessage());
        return new LexicalContentError(message, position, line, lexeme);
    }
}
