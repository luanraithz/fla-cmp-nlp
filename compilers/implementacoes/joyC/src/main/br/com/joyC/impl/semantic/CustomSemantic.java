package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.Semantico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.models.IdentifierMetadata;
import org.apache.logging.log4j.message.Message;
import org.springframework.jmx.access.InvalidInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;

public class CustomSemantic extends Semantico {
    private final StringBuilder output = new StringBuilder();
    private final HashMap<String, IdentifierMetadata> symbolTable = new HashMap();
    private final String MODULE_NAME = "_principal";
    private Token currentToken;
    private Stack<VariableType> varTypeStack = new Stack<>();
    private String operator;
    private Queue<String> ids = new ArrayDeque<>();
    private VariableType currentVarDecType;

    CustomSemantic() {
    }

    private void addLine(String line){
        output.append(line);
        output.append("\n");
    }

    private void addCommandLine(String str) {
        addLine("    "+ str);

    }

    private void assignNextExpressionType() {
        var type1 = varTypeStack.pop();
        var type2 = varTypeStack.pop();
        var expressionType = type1 == VariableType.Float64 || type2 == VariableType.Float64 ? VariableType.Float64 : VariableType.Int64;
        varTypeStack.push(expressionType);
    }

    private void action1() {
        assignNextExpressionType();
        addCommandLine("add");
    }

    private void action2() {
        assignNextExpressionType();
        addCommandLine("sub");
    }

    private void action3() {
        assignNextExpressionType();
        addCommandLine("mul");
    }

    private void action4() throws SemanticError {
        var type1 = varTypeStack.pop();
        var type2 = varTypeStack.pop();
        if (type1 == type2) {
            varTypeStack.push(type1);
        } else {
            throw new SemanticError("Tipos invalidos");
        }
        addCommandLine("div");
    }

    private void action5() {
        varTypeStack.push(VariableType.Int64);
        addCommandLine("ldc.i8 " + this.currentToken.getLexeme());
        addCommandLine("conv.r8");
    }

    private void action6() {
        varTypeStack.push(VariableType.Float64);
        addCommandLine("ldc.r8 " + this.currentToken.getLexeme().replace(",", "."));
    }

    private void validateExpressionType() throws SemanticError {
        var type = varTypeStack.pop();
        if (type == VariableType.Float64 || type == VariableType.Int64) {
            varTypeStack.push(type);
        } else {
            throw new SemanticError("Esperado int ou float");
        }
    }
    private void action7() throws SemanticError {
        validateExpressionType();
    }

    private void action8() throws SemanticError {
        validateExpressionType();
        addCommandLine("ldc.i8 -1");
        addCommandLine("conv.r8");
        addCommandLine("mul");
    }


    private void action9() {
        this.operator = currentToken.getLexeme();
    }

    private void action10() throws SemanticError {
        var type1 = varTypeStack.pop();
        var type2 = varTypeStack.pop();
        if (type1 == type2) {
            varTypeStack.push(VariableType.Bool);
        } else {
            throw new SemanticError("Tipos invalidos");
        }
        // @TODO implement greater/less or equal
        switch (operator) {
            case ">":
                addCommandLine("cgt");
                break;
            case "<":
                addCommandLine("clt");
                break;
            case "=":
                addCommandLine("ceq");
        }
    }

    private void action11() {
        varTypeStack.push(VariableType.Bool);
        addCommandLine("ldc.i4.1");
    }

    private void action12() {
        varTypeStack.push(VariableType.Bool);
        addCommandLine("ldc.i4.0");
    }

    private void action13() throws SemanticError {
        var type = varTypeStack.pop();
        if (type == VariableType.Bool) {
            varTypeStack.push(VariableType.Bool);
        } else {
            throw new SemanticError("Esperado bool em expressao");
        }
        addCommandLine("ldc.i4.1");
        addCommandLine("xor");

    }


    private void action14() {
        var type = varTypeStack.pop();
        if (type == VariableType.Int64) {
            addCommandLine("conv.i8");
        }
        addCommandLine(MessageFormat.format("call void [mscorlib]System.Console::Write({0})", type.getPrimitiveType()));
    }


    private void action15() {
        addLine(".assembly extern mscorlib {}");
        addLine(".assembly _codigo_objeto{}");
        addLine(".module _codigo_objeto.exe");
        addLine("");
        addLine(".class public _UNICA {");
    }

    private void action16() {
        addLine("  .method static public void " + MODULE_NAME + "() {");
        addCommandLine(".entrypoint");
    }

    private void action17() {
        addCommandLine("ret");
        addLine("  }");
        addLine("}");
    }

    private void action18() {
        addCommandLine("and");
    }

    private void action19() {
        addCommandLine("or");
    }

    private void action20() {
        varTypeStack.push(VariableType.String);
        addCommandLine("ldstr " + currentToken.getLexeme());
    }

    private void action30() throws SemanticError {
        var type = Utils.getVariableType(currentToken.getLexeme());
        if (type != null) {
            currentVarDecType = type;
        } else {
            System.out.println("variable not implemented yet");
        }
    }

    private void action31() throws SemanticError {
        for (String identifier: ids) {
            if (symbolTable.containsKey(identifier)) throw new SemanticError("Identificador ja declarado");
            var metadata = new IdentifierMetadata();
            metadata.type = currentVarDecType;
            symbolTable.put(identifier, metadata);
            addCommandLine(MessageFormat.format(".locals ({0} {1})", metadata.type.getPrimitiveType(), identifier));
        }
        ids.clear();
    }

    private void action32() throws SemanticError {
        ids.add(currentToken.getLexeme());
    }

    private void action33() throws SemanticError {
        var id = currentToken.getLexeme();
        if (!symbolTable.containsKey(id)) throw new SemanticError("Identificador nao declarado");
        var type = Utils.getVariableType(id);
        varTypeStack.push(type);
        addCommandLine("ldloc " + id);
        if (type == VariableType.Int64) {
            addCommandLine("conv.r8");
        }
    }

    private void action34() throws SemanticError {
                // @todo devia ser uma fila?
        var id = ids.peek();
        if (!symbolTable.containsKey(id)) {
            throw new SemanticError("Identificador nao declarado");
        }
        var type = Utils.getVariableType(id);
        var expressionType = varTypeStack.pop();
        if (type != expressionType) {
            throw new SemanticError("tipos incompativeis");
        }
        if (type == VariableType.Int64) {
            addCommandLine("conv.i8");
        }
        addCommandLine("stloc " + id);
    }

    private void action35() throws SemanticError {
        for (String id: ids) {
            if (!symbolTable.containsKey(id)) {
                throw new SemanticError("Identificador nao declarado");
            }
            var type = Utils.getVariableType(id);
            addCommandLine("call string [mscorlib]System.Console::ReadLine()");
            addCommandLine(MessageFormat.format("call {0} [mscorlib]System.{1}::Parse(string)", type.getPrimitiveType(), type.getClassType()));
            addCommandLine(MessageFormat.format("stloc {0}", id));
        }
        ids.clear();
    }

    private void action39() throws SemanticError {
        addCommandLine("brfalse r1");
    }

    private void action40() throws SemanticError {
        addCommandLine("r1:");
    }

    @Override
    public void executeAction(int action, Token token) throws SemanticError
    {
        this.currentToken = token;
        System.out.println("Ação #"+action+", Token: "+token);
        try {
            var method = this.getClass().getDeclaredMethod("action"+action);
            method.setAccessible(true);
            method.invoke(this);
        } catch (NoSuchMethodException e) {
            System.out.println(MessageFormat.format("Action {0} not implemented", action));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Some method was implemented wrongly");
            e.printStackTrace();
        } catch (InvocationTargetException err) {
            err.printStackTrace();
            var ex = (SemanticError) err.getCause();
            throw ex;
        }
    }

    public String getResult() {
        return this.output.toString();
    }

}
