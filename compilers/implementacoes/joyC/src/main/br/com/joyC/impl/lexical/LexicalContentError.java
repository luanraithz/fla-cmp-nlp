package main.br.com.joyC.impl.lexical;

import main.br.com.joyC.gaals.LexicalError;

public class LexicalContentError extends LexicalError {
    private String lexeme;
    private Integer line;

    public String getLexeme() {
        return lexeme;
    }

    public void setLexem(String lexeme) {
        this.lexeme = lexeme;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    LexicalContentError(String message, Integer position, Integer line, String lexeme) {
        super(message, position);
        this.line = line;
        this.lexeme = lexeme;
    }
}
