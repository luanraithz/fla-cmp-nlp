package main.br.com.joyC.impl.semantic.impl;

import main.br.com.joyC.impl.models.Pair;
import main.br.com.joyC.impl.semantic.interfaces.LoopContextController;

import java.util.Stack;

public class LoopContextControllerImpl implements LoopContextController {
    final private Stack<Pair<Integer, Integer>> contextStack;
    private Integer contextCount;
    public LoopContextControllerImpl() {
        contextCount = 0;
        contextStack = new Stack<>();
    }

    @Override
    public Pair<Integer, Integer> startContext() {
        contextCount++;
        var pair = new Pair<>(contextCount, contextCount + 1);
        contextStack.push(pair);
        contextCount++;
        return pair;
    }

    @Override
    public Integer getCurrentContext() {
        return contextStack.peek().right;
    }

    @Override
    public Pair<Integer, Integer> closeCurrentContext() {
        return contextStack.pop();
    }

}
