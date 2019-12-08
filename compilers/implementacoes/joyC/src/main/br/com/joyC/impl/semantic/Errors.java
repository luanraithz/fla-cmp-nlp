package main.br.com.joyC.impl.semantic;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;

public class Errors {
    public static String undeclaredVariableMessage = "Variavel nao declarada: {0}";
    public static String alreadyDeclaredVariableMessage = "Identificador ja declarado: {0}";
    public static final String incompatibleTypesInLogicExpression = "Tipo(s) incompativel(is) em expressao logica";
    public static final String incompatibleTypesInArithmeticExpression = "Tipo(s) incompativel(is) em expressao aritmetica";
    public static final String incompatibleTypesInRelationalExpression = "Tipos (s) incompativel(is) em expressao relacional";

    public static String undeclaredVariable(@NotNull String id) {
        return MessageFormat.format(undeclaredVariableMessage, id);
    }

    public static String variableAlreadyDeclared(@NotNull String id) {
        return MessageFormat.format(alreadyDeclaredVariableMessage, id);
    }

}
