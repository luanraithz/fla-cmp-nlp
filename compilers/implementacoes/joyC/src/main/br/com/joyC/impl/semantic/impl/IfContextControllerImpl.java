package main.br.com.joyC.impl.semantic.impl;

import main.br.com.joyC.impl.models.Pair;
import main.br.com.joyC.impl.semantic.interfaces.IfContextController;

import java.text.MessageFormat;
import java.util.Stack;

public class IfContextControllerImpl implements IfContextController {
    private Integer contextCount;
    private Integer currentContext;
    private Stack<Integer> contextStack;
    public IfContextControllerImpl() {
        contextCount = 0;
        contextStack = new Stack<>();
    }

    @Override
    public Integer createIf() {
        contextCount++;
        currentContext = contextCount;
        contextStack.push(currentContext);
        return currentContext;
    }

    @Override
    public Pair<Integer, Integer> createElse() {
        var closingContext = contextStack.pop();
        var next = closingContext + 1;
        contextStack.push(next);
        contextCount++;
        return new Pair(closingContext, next);
    }

    @Override
    public Integer closeIf() {
        return contextStack.pop();
    }

}
