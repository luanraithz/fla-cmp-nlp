package main.br.com.joyC.impl.semantic.interfaces;

import main.br.com.joyC.impl.models.Pair;

public interface IfContextController {
    Integer createIf();
    Pair<Integer, Integer> createElse();
    Integer closeIf();
}
