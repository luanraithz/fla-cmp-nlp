package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.Semantico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.models.IdentifierMetadata;

import java.util.HashMap;
import java.util.function.Consumer;

public class CustomSemantic extends Semantico {
    private final StringBuilder output = new StringBuilder();
    private final HashMap<String, IdentifierMetadata> symbolTable = new HashMap();
    private final HashMap<Integer, Consumer<Token>> functions = new HashMap<>();

    CustomSemantic() {
        this.functions.put(10, (token -> {
        }));

    }

    @Override
    public void executeAction(int action, Token token) throws SemanticError
    {
        System.out.println("Ação #"+action+", Token: "+token);
    }

    public String getResult() {
        return this.output.toString();
    }

}
