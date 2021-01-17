package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.Semantico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.models.IdentifierMetadata;
import main.br.com.joyC.impl.semantic.impl.IfContextControllerImpl;
import main.br.com.joyC.impl.semantic.impl.LoopContextControllerImpl;
import main.br.com.joyC.impl.semantic.interfaces.IfContextController;
import main.br.com.joyC.impl.semantic.interfaces.LoopContextController;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;

public class CustomSemantic extends Semantico {
    private final StringBuilder output = new StringBuilder();
    private final HashMap<String, IdentifierMetadata> symbolTable = new HashMap();
    private Token currentToken;
    private Stack<VariableType> varTypeStack = new Stack<>();
    private String operator;
    private Queue<String> ids = new ArrayDeque<>();
    private Integer currentArraySize;
    private String currentVarName;
    private Boolean currentVarIsArray = false;
    private Boolean arrayContext = false;
    private IfContextController ifContextController;
    private LoopContextController loopContextController;

    CustomSemantic() {
        ifContextController = new IfContextControllerImpl();
        loopContextController = new LoopContextControllerImpl();
    }

    private void addLine(String line){
        output.append(line);
        output.append("\n");
    }

    private void addCommandLine(String str) {
        addLine("    "+ str);
    }

    private void assignNextExpressionType() throws SemanticError {
        var type1 = varTypeStack.pop();
        var type2 = varTypeStack.pop();
        if (Utils.canSumTypes(type1, type2)) {
            var expressionType = type1 == VariableType.Float64 || type2 == VariableType.Float64 ? VariableType.Float64 : VariableType.Int64;
            if (expressionType == VariableType.Float64) {
                addCommandLine("conv.r8");
            }
            varTypeStack.push(expressionType);
        } else {
            throw new SemanticError(Errors.incompatibleTypesInArithmeticExpression);
        }
    }

    private void action1() throws SemanticError {
        assignNextExpressionType();
        addCommandLine("add");
    }

    private void action2() throws SemanticError {
        assignNextExpressionType();
        addCommandLine("sub");
    }

    private void action3() throws SemanticError {
        assignNextExpressionType();
        addCommandLine("mul");
    }

    private void action4() throws SemanticError {
        var type1 = varTypeStack.pop();
        var type2 = varTypeStack.pop();
        if (Utils.canDivideTypes(type1, type2)) {
            varTypeStack.push(VariableType.Float64);
        } else {
            throw new SemanticError(Errors.incompatibleTypesInArithmeticExpression);
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
        if (Utils.isNumber(type))
            varTypeStack.push(type);
        else
            throw new SemanticError(Errors.incompatibleTypesInArithmeticExpression);
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
            throw new SemanticError(Errors.incompatibleTypesInRelationalExpression);
        }
        // @TODO implement greater/less or equal
        switch (operator) {
            case ">":
                addCommandLine("cgt");
                break;
            case "<":
                addCommandLine("clt");
                break;
            case "==":
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
            throw new SemanticError(Errors.incompatibleTypesInLogicExpression);
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
        ids.clear();
    }


    private void action15() {
        addLine(".assembly extern mscorlib {}");
        addLine(".assembly _codigo_objeto{}");
        addLine(".module _codigo_objeto.exe");
        addLine("");
        addLine(".class public _UNICA {");
    }

    private void action16() {
        String MODULE_NAME = "_principal";
        addLine("  .method static public void " + MODULE_NAME + "() {");
        addCommandLine(".entrypoint");
    }

    private void action17() {
        addCommandLine("ret");
        addLine("  }");
        addLine("}");
    }

    private void action18() throws SemanticError {
        var type = varTypeStack.pop();
        if (type == VariableType.Bool) {
            varTypeStack.push(VariableType.Bool);
        } else {
            throw new SemanticError(Errors.incompatibleTypesInLogicExpression);
        }
        addCommandLine("and");
    }

    private void action19() throws SemanticError {
        var type = varTypeStack.pop();
        if (type == VariableType.Bool) {
            varTypeStack.push(VariableType.Bool);
        } else {
            throw new SemanticError(Errors.incompatibleTypesInLogicExpression);
        }
        addCommandLine("or");
    }

    private void action20() {
        varTypeStack.push(VariableType.String);
        addCommandLine("ldstr " + currentToken.getLexeme());
    }

    private void action30() throws SemanticError {
        var id = currentToken.getLexeme();
        if (symbolTable.containsKey(id)) throw new SemanticError(Errors.variableAlreadyDeclared(id));
        var type = Utils.getVariableType(id);
        if (type != null) {
            var metadata = new IdentifierMetadata();
            metadata.isArray = false;
            currentVarIsArray = false;
            currentVarName = id;
            metadata.type = type;
            symbolTable.put(id, metadata);
        } else {
            throw new SemanticError("Tipo ainda nao implementado");
        }
    }

    private void action31() throws SemanticError {
        for (String identifier: ids) {
            var metadata = symbolTable.get(identifier);
            metadata.isArray = currentVarIsArray;
            metadata.arraySize = currentArraySize;
            symbolTable.put(identifier, metadata);
            if (currentVarIsArray) {
                addCommandLine(MessageFormat.format(".locals ({0}[] {1})", metadata.type.getPrimitiveType(), identifier));
                addCommandLine("ldc.i8 " + currentArraySize);
                addCommandLine(MessageFormat.format("newarr [mscorlib]System.{0}", metadata.type.getClassType()));
                addCommandLine("stloc " + identifier);
            } else {
                addCommandLine(MessageFormat.format(".locals ({0} {1})", metadata.type.getPrimitiveType(), identifier));
            }
        }
        ids.clear();
    }

    private void action32() throws SemanticError {
        var id = currentToken.getLexeme();
        if (!symbolTable.containsKey(id)) throw new SemanticError(Errors.undeclaredVariable(id));
        if (!arrayContext) {
            ids.add(currentToken.getLexeme());
        }
    }

    private void action33() throws SemanticError {
        var id = currentToken.getLexeme();
        if (id.equals("]")) {
            if (!symbolTable.containsKey(currentVarName)) throw new SemanticError(Errors.undeclaredVariable(id));
            var metadata = symbolTable.get(currentVarName);
            var type = metadata.type;
            addCommandLine(MessageFormat.format("ldelem {0}", type.getPrimitiveType()));
            if (type == VariableType.Int64) {
                addCommandLine("conv.r8");
            }

        } else {
            if (!symbolTable.containsKey(id)) throw new SemanticError(Errors.undeclaredVariable(id));
            var type = symbolTable.get(id).type;
            varTypeStack.push(type);
            addCommandLine("ldloc " + id);
            if (type == VariableType.Int64) {
                addCommandLine("conv.r8");
            }
        }
    }

    private void action34() throws SemanticError {
        // @TODO devia ser uma fila?
        var id = ids.poll();
        if (!symbolTable.containsKey(id)) throw new SemanticError(Errors.undeclaredVariable(id));
        var metadata = symbolTable.get(id);
        var type = metadata.type;

        if (type == VariableType.Int64) addCommandLine("conv.i8");
        if (metadata.isArray) {
            addCommandLine(MessageFormat.format("stelem {0}", metadata.type.getPrimitiveType()));
        } else {
            addCommandLine("stloc " + id);
        }
        ids.clear();
    }

    private void action35() throws SemanticError {
        for (String id: ids) {
            if (!symbolTable.containsKey(id)) throw new SemanticError(Errors.undeclaredVariable(id));
            var metadata = symbolTable.get(id);
            var type = metadata.type;
            addCommandLine("call string [mscorlib]System.Console::ReadLine()");
            if (type != VariableType.String) {
                addCommandLine(MessageFormat.format("call {0} [mscorlib]System.{1}::Parse(string)", type.getPrimitiveType(), type.getClassType()));
            }
            if (metadata.isArray) {
                addCommandLine(MessageFormat.format("stelem {0}", type.getPrimitiveType()));
            } else {
                addCommandLine(MessageFormat.format("stloc {0}", id));
            }
        }
        ids.clear();
    }

    /* * action chamada dentro da declaracao do tamanho de um array */
    private void action36() throws SemanticError {
        var size = Integer.parseInt(currentToken.getLexeme());
        if (size <= 0) {
            throw new SemanticError("Tamanho do array deve ser maior que 0");
        }
        currentVarIsArray = true;
        currentArraySize = size;
    }

    private void action37() throws SemanticError {
        var id = currentToken.getLexeme();
        var metadata = symbolTable.get(id);
        if (!metadata.isArray) {
            throw new SemanticError("Variavel nao 'e um array :)");
        }
        currentVarName = id;
        arrayContext = true;
        addCommandLine("ldloc " + id);
    }

    private void action38() {
        addCommandLine("conv.i8");
        arrayContext = false;
    }

    private void action39() {
        addCommandLine("brfalse r"+ ifContextController.createIf());
        ids.clear();
    }

    /**
        Fecha o contexto o else (ou apenas if) atual
     */
    private void action40() {
        var currentClosingContext = ifContextController.closeIf();
        addCommandLine(MessageFormat.format("r{0}:", currentClosingContext));
    }

    /**
     * Inicia um contexto de false se for chamado
     */
    private void action41() {
        var pair = ifContextController.createElse();
        addCommandLine("br r"+pair.right);
        addCommandLine(MessageFormat.format("r{0}:", pair.left));
    }

    /* Action a ser chamada antes da palavra "while" */
    private void action42() {
        var startingContext = loopContextController.startContext();
        addCommandLine("l"+startingContext.left + ":");
    }

    /* Action a ser chamada depois da palavra "isFalseDo" ou "isTrueDo" no comando de loop */
    private void action43() {
        ids.clear();
        var right = loopContextController.getCurrentContext();
        if(currentToken.getLexeme().equals("isTrueDo"))
            addCommandLine("brfalse l" + right);
        else
            addCommandLine("brtrue l" + right);
    }

    /* Action a ser chamada ao comando ser finalizado */
    private void action44() {
        var closingContext = loopContextController.closeCurrentContext();
        addCommandLine("br l" + closingContext.left);
        addCommandLine("l" + closingContext.right + ":");
    }

    @Override
    public void executeAction(int action, Token token) throws SemanticError
    {
        this.currentToken = token;
        System.out.println("action:" + action + "token:" + (token != null ? token.getLexeme(): ""));
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
            var cause = err.getCause();
            if (cause instanceof SemanticError) {
                var semanticError = (SemanticError) cause;
                semanticError.position = currentToken.getPosition();
                System.out.println(semanticError.position);
                throw semanticError;
            }
        }
    }

    public String getResult() {
        return this.output.toString();
    }

}
