package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.gaals.LexicalError;

public class LexicalContentError extends LexicalError {
    private String lexem;
    private Integer line;

    public String getLexem() {
        return lexem;
    }

    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
    LexicalContentError(String message, Integer position) {
        super(message, position);
    }

    LexicalContentError(String message, Integer position, Integer line, String lexem) {
        super(message, position);
        this.line = line;
        this.lexem = lexem;
    }
}
