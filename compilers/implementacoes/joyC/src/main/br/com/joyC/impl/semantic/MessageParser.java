package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.impl.utils.LineCounter;

import java.text.MessageFormat;

public class MessageParser {
    public static String parseError(String entry, SemanticError err) {
        var textUntilPosition = entry.substring(0, err.getPosition());
        var linesUntilError = LineCounter.count(textUntilPosition);
        var message = "Erro na linha {0} - " + err.getMessage();
        return MessageFormat.format(message, linesUntilError);
    }
}
