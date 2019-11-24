package main.br.com.joyC.impl.semantic;

public class Utils {
    static VariableType getVariableType(String name) {
        if (name.startsWith("I")) {
            return VariableType.Int64;
        } else if (name.startsWith("F")) {
            return VariableType.Float64;
        }
        return VariableType.String;
    }
}
