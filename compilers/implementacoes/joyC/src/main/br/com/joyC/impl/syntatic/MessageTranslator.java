package main.br.com.joyC.impl.syntatic;

import java.util.HashMap;

public class MessageTranslator {
    private static HashMap<String, String> messages = new HashMap<>() {{

    }};

    public String translate(String entry) {
        return messages.get(entry);
    }
}
