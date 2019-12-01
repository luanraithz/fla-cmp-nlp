package tests.br.com.joyC.impl.semantic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.br.com.joyC.impl.semantic.impl.LoopContextControllerImpl;
import main.br.com.joyC.impl.semantic.interfaces.LoopContextController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopContextControllerTest {
    LoopContextController contextController;

    @BeforeEach
    void startContext() {
        contextController = new LoopContextControllerImpl();
    }

    @Test
    void simpleLoop() {
        var pair = contextController.startContext();
        assertEquals(1, pair.left);
        assertEquals(2, pair.right);
        pair = contextController.getCurrentContext();
        assertEquals(1, pair.left);
        assertEquals(2, pair.right);
    }

    @Test
    void nestedLoop() {
        var startingPair = contextController.startContext();
        assertEquals(1, startingPair.left);
        assertEquals(2, startingPair.right);
    }

}
