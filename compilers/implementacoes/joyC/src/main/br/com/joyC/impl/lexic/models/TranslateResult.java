package main.br.com.joyC.impl.lexic.models;

public class TranslateResult {
    public String message;
    public Boolean isWord;
    TranslateResult(String m, Boolean b) {
        this.message = m;
        this.isWord = b;
    }

    static public TranslateResult of(String message, Boolean isWord) {
        return new TranslateResult(message, isWord);
    }

    static public TranslateResult of(String message) {
        return new TranslateResult(message, false);
    }
}
