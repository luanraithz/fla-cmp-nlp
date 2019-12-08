package main.br.com.joyC.impl.semantic.interfaces;

import main.br.com.joyC.impl.models.Pair;

public interface IfContextController {
    /**
     *
     * @return creates a new unique a identifier
     */
    Integer createIf();

    /**
     *
     * @return pair with the closing block (left) and the opening block (right)
     */
    Pair<Integer, Integer> createElse();

    /**
     *
     * @return the integer with the closing block
     */
    Integer closeIf();
}
