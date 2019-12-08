package main.br.com.joyC.impl.lexic.models;

public class TranslateResult {
    public String message;
    public Boolean isWord;
    public Boolean constant;

    TranslateResult(String m, Boolean b, Boolean constant) {
        this.message = m;
        this.isWord = b;
        this.constant = constant;
    }

    static public TranslateResult constantOf(String message) {
        return new TranslateResult(message, false, true);
    }
    static public TranslateResult of(String message, Boolean isWord) {
        return new TranslateResult(message, isWord, false);
    }

    static public TranslateResult of(String message) {
        return new TranslateResult(message, false, false);
    }

}
