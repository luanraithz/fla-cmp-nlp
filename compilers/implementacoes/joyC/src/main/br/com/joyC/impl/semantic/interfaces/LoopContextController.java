package main.br.com.joyC.impl.semantic.interfaces;

import main.br.com.joyC.impl.models.Pair;

public interface LoopContextController {
    /**
     *
     * @return a pair with the block to jump (left) and the block that must be started
     */
    Pair<Integer, Integer> startContext();

    /**
     *
     * @return a pair with the current block (in the right)
     */
    Integer getCurrentContext();
    Pair<Integer, Integer> closeCurrentContext();
}
