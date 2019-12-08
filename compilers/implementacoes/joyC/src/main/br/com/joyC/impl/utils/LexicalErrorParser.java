package main.br.com.joyC.impl.utils;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.impl.lexic.models.LexemeType;
import main.br.com.joyC.impl.lexic.models.TranslateResult;
import main.br.com.joyC.impl.models.LexicalContentError;
import org.jboss.logging.Messages;

import java.text.MessageFormat;

public class LexicalErrorParser {
    public static final String INVALID_COMPOSED_ID = "identificador composto inválido";
    public static final String INVALID_STRING_CONSTANT = "constante string inválida";
    public static final String INVALID_SPECIAL_SYMBOL = "símbolo especial inválido";
    public static final String INVALID_BOOLEAN_ID = "identificador boolean inválido";
    public static final String INVALID_FLOAT_ID = "identificador float inválido";
    public static final String INVALID_INT_ID = "identificador int inválido";
    public static final String INVALID_STRING_ID = "identificador string inválido";
    public static final String INVALID_FLOAT_CONSTANT = "constante float inválida";
    public static final String INVALID_RESERVED_WORD = "palavra reservada inválida";
    public static final String INVALID_SYMBOL = "símbolo inválido";

    static private TranslateResult translate(String str) {
        switch (str) {
            case "Erro identificando string":
                return TranslateResult.of(INVALID_STRING_CONSTANT, true);
            case "Erro identificando simboloEspecial":
                return TranslateResult.of(INVALID_SPECIAL_SYMBOL, true);
            case "Erro identificando idBool":
                return TranslateResult.of(INVALID_BOOLEAN_ID, true);
            case "Erro identificando idComposto":
                return TranslateResult.of(INVALID_COMPOSED_ID, true);
            case "Erro identificando idFloat":
                return TranslateResult.of(INVALID_FLOAT_ID, true);
            case "Erro identificando idInt":
                return TranslateResult.of(INVALID_INT_ID, true);
            case "Erro identificando idString":
                return TranslateResult.of(INVALID_STRING_ID, true);
            case "Erro identificando float":
                return TranslateResult.of(INVALID_FLOAT_CONSTANT, true);
            case "Erro identificando palavraReservada":
                return TranslateResult.of(INVALID_RESERVED_WORD , true);
            case "Erro identificando <ignorar>":
                return TranslateResult.constantOf("Bloco de comentário inválido");
            case "Caractere não esperado":
                return TranslateResult.of(INVALID_SYMBOL);
            default:
                return TranslateResult.of(str);
        }
    }

    private static String firstWord(String entry) {
        var builder = new StringBuilder();
        for (char c : entry.toCharArray()) {
            if (LexemeType.isSpecialSymbol(String.valueOf(c)) || " ".equals(String.valueOf(c))) {
                break;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    public static LexicalContentError toException(String entry, LexicalError er) {
        var position = er.getPosition();
        var line = LineCounter.count(entry.substring(0, position + 1));
        var lexeme = String.valueOf(entry.charAt(position));
        var result = translate(er.getMessage());
        if (result.isWord) {
            var s =  firstWord(entry.substring(position));
            return new LexicalContentError("Erro na linha " + line + " - " + s +  " " + result.message, position, line, lexeme);
        }
        if (result.constant) {
            return new LexicalContentError(MessageFormat.format("Erro na linha {0} - {1}", line, result.message), position, line, lexeme);
        }
        return new LexicalContentError("Erro na linha " + line + " - " + lexeme +  " " + result.message, position, line, lexeme);
    }
}
