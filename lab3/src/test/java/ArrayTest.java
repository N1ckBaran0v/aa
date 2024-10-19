import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {
    private void skeletonLinear(int elem, int answer) {
        var array = Array.generateArray(10, false);
        skeleton(elem, answer, array::search);
    }

    private void skeletonBinary(int elem, int answer) {
        var array = Array.generateArray(10, false);
        skeleton(elem, answer, array::binarySearch);
    }

    private void skeleton(int elem, int answer, UnaryOperator<Integer> operator) {
        assertEquals(answer, operator.apply(elem));
    }

    @Test
    void foundFirstLinear() {
        skeletonLinear(0, 0);
    }

    @Test
    void foundLastLinear() {
        skeletonLinear(9, 9);
    }

    @Test
    void notFoundLinear() {
        skeletonLinear(10, -1);
    }

    @Test
    void foundRandomLinear() {
        skeletonLinear(3, 3);
    }

    @Test
    void foundFirstBinary() {
        skeletonBinary(0, 0);
    }

    @Test
    void foundLastBinary() {
        skeletonBinary(9, 9);
    }

    @Test
    void notFoundBinary() {
        skeletonBinary(10, -1);
    }

    @Test
    void foundRandomBinary() {
        skeletonBinary(3, 3);
    }
}