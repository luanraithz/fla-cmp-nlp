package main.br.com.joyC.impl.semantic;

public class Utils {
    static VariableType getVariableType(String name) {
        if (name.startsWith("I")) {
            return VariableType.Int64;
        } else if (name.startsWith("F")) {
            return VariableType.Float64;
        } else if (name.startsWith("B")) {
            return VariableType.Bool;
        }
        return VariableType.String;
    }

    static Boolean isNumber(VariableType type) {
        return type == VariableType.Int64 || type == VariableType.Float64;
    }

    static Boolean canSumTypes(VariableType type1, VariableType type2) {
        return isNumber(type1) && isNumber(type2);
    }

    static Boolean canDivideTypes(VariableType type, VariableType type2) {
        return isNumber(type) && isNumber(type2);
    }

}
