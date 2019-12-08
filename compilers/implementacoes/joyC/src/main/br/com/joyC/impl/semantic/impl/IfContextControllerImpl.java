package main.br.com.joyC.impl.semantic.impl;

import main.br.com.joyC.impl.models.Pair;
import main.br.com.joyC.impl.semantic.interfaces.IfContextController;

import java.text.MessageFormat;
import java.util.Stack;

public class IfContextControllerImpl implements IfContextController {
    private Integer contextCount;
    private Stack<Integer> contextStack;
    public IfContextControllerImpl() {
        contextCount = 0;
        contextStack = new Stack<>();
    }

    @Override
    public Integer createIf() {
        contextCount++;
        contextStack.push(contextCount);
        return contextCount;
    }

    @Override
    public Pair<Integer, Integer> createElse() {
        var closingContext = contextStack.pop();
        contextCount++;
        contextStack.push(contextCount);
        return new Pair(closingContext, contextCount);
    }

    @Override
    public Integer closeIf() {
        return contextStack.pop();
    }

}
