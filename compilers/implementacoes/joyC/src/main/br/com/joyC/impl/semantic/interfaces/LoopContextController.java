package main.br.com.joyC.impl.semantic.interfaces;

import main.br.com.joyC.impl.models.Pair;

public interface LoopContextController {
    Pair<Integer, Integer> startContext();
    Pair<Integer, Integer> getCurrentContext();
    Pair<Integer, Integer> closeCurrentContext();
}
