package main.br.com.joyC.impl.lexic;

public class MessageTranslator {
    static String translate(String str) {
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
}
