package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.Semantico;
import main.br.com.joyC.gaals.Token;
import main.br.com.joyC.impl.models.IdentifierMetadata;
import org.apache.logging.log4j.message.Message;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Stack;

public class CustomSemantic extends Semantico {
    private final StringBuilder output = new StringBuilder();
    private final HashMap<String, IdentifierMetadata> symbolTable = new HashMap();
    private final String MODULE_NAME = "programa_1";
    private Token currentToken = null;
    private Stack<VariableType> varTypeStack = new Stack<>();

    CustomSemantic() {
    }

    private void addLine(String line){
        output.append(line);
        output.append("\n");
    }

    private void addCommandLine(String str) {
        addLine("       "+ str);

    }

    /**
     * Adiciona um int na no output
     */
    private void action5() {
        varTypeStack.push(VariableType.Int64);
        addCommandLine("ldc.i8 " + this.currentToken.getLexeme());
        addCommandLine("conv.r8");
    }

    private void action14() {
        var type = varTypeStack.pop();
        if (type == VariableType.Int64) {
            addCommandLine("conv.i8");
        }
        addCommandLine(MessageFormat.format("call void [mscorlib]System.Console::Write({0})", type.value));
    }


    private void action15() {
        addLine(".assembly extern mscorlib {}");
        addLine(".assembly _codigo_objeto{}");
        addLine(".module __codigo_objeto.exe");
        addLine("");
        addLine(".class public _UNICA {");
    }

    private void action16() {
        addLine("   .method static public void " + MODULE_NAME + "() {");
        addLine("       .entrypoint");
    }

    private void action17() {
        addLine("       ret");
        addLine("   }");
        addLine("}");
    }

    private void action10() {
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
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Some method was implemented wrongly");
            e.printStackTrace();
        }
    }

    public String getResult() {
        return this.output.toString();
    }

}
