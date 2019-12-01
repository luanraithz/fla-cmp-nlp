package tests.br.com.joyC.impl.semantic;

import main.br.com.joyC.impl.semantic.interfaces.IfContextController;
import main.br.com.joyC.impl.semantic.impl.IfContextControllerImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IfContextControllerTest {

    private IfContextController contextController;

    @BeforeEach
    void startController() {
        contextController = new IfContextControllerImpl();
    }

    @Test
    @DisplayName("Simple if statement")
    void ifStatement() {
        assertEquals(1, contextController.createIf());
        assertEquals(1, contextController.closeIf());
    }

    @Test
    @DisplayName("Nested if statement")
    void nestedIf() {
        assertEquals(1, contextController.createIf());
        assertEquals(2, contextController.createIf());
        assertEquals(2, contextController.closeIf());
        assertEquals(1, contextController.closeIf());
    }

    @Test
    @DisplayName("If/else statement")
    void ifElseStatement() {
        assertEquals(1, contextController.createIf());
        var pair = contextController.createElse();
        assertEquals(1, pair.left);
        assertEquals(2, pair.right);
        assertEquals(2, contextController.closeIf());
    }

    @Test
    @DisplayName("nested else inside if statement")
    void nestedElseInsideIfStatement() {
        assertEquals(1, contextController.createIf());
        assertEquals(2 , contextController.createIf());
        var pair = contextController.createElse();
        assertEquals(2, pair.left);
        assertEquals(3, pair.right);
        assertEquals(3, contextController.closeIf());
        assertEquals(1, contextController.closeIf());
    }

    @Test
    @DisplayName("2 nested if statement")
    void twoNestedIfStatement() {
        assertEquals(1, contextController.createIf());
        assertEquals(2 , contextController.createIf());
        var pair = contextController.createElse();
        assertEquals(2, pair.left);
        assertEquals(3, pair.right);
        assertEquals(3, contextController.closeIf());
        assertEquals(4, contextController.createIf());
        assertEquals(4, contextController.closeIf());
        assertEquals(1, contextController.closeIf());
    }

    @Test
    @DisplayName("nested context inside else statement")
    void nestedContextInsideElse() {
        assertEquals(1, contextController.createIf());
        var pair = contextController.createElse();
        assertEquals(1, pair.left);
        assertEquals(2, pair.right);
        assertEquals(3, contextController.createIf());
        pair = contextController.createElse();
        assertEquals(3, pair.left);
        assertEquals(4, pair.right);
        assertEquals(4, contextController.closeIf());
        assertEquals(2, contextController.closeIf());
    }

    @Test
    @DisplayName("nested context inside else statement")
    void multipleNestedContextInsideElse() {
        assertEquals(1, contextController.createIf());
        var pair = contextController.createElse();
        assertEquals(1, pair.left);
        assertEquals(2, pair.right);
        assertEquals(3, contextController.createIf());
        pair = contextController.createElse();
        assertEquals(3, pair.left);
        assertEquals(4, pair.right);
        assertEquals(5, contextController.createIf());
        assertEquals(5, contextController.closeIf());
        assertEquals(4, contextController.closeIf());
        assertEquals(2, contextController.closeIf());
    }

    @Test
    @DisplayName("nested context inside else statement")
    void multipleLevels() {
        assertEquals(1, contextController.createIf());
            assertEquals(2, contextController.createIf());

            var leve2Pair = contextController.createElse();
                assertEquals(2, leve2Pair.left);
                assertEquals(3, leve2Pair.right);
            assertEquals(3, contextController.closeIf());
        var level1Pair = contextController.createElse();
            assertEquals(1, level1Pair.left);
            assertEquals(4, level1Pair.right);
            assertEquals(5, contextController.createIf());

            leve2Pair = contextController.createElse();
                assertEquals(5, leve2Pair.left);
                assertEquals(6, leve2Pair.right);
            assertEquals(6, contextController.closeIf());
        assertEquals(4, contextController.closeIf());
    }
}
