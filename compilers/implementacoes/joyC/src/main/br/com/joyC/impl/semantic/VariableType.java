package main.br.com.joyC.impl.semantic;

public enum VariableType {
    Float64("float64", "Double"),
    Int64("int64", "Int64"),
    Bool("bool", "Boolean"),
    String("string", "String");
    private String primitiveType;
    private String classType;

    VariableType(String value, String classType) {
        this.primitiveType = value;
        this.classType = classType;
    }

    public String getPrimitiveType() {
        return primitiveType;
    }

    public String getClassType() {
        return classType;
    }
}
