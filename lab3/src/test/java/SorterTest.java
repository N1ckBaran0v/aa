import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    private void skeleton(int[] array) {
        var answer = Arrays.copyOf(array, array.length);
        Sorter.sort(array);
        Arrays.sort(answer);
        assertArrayEquals(answer, array);
    }

    @Test
    void sorted() {
        skeleton(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    void reversed() {
        skeleton(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    }

    @Test
    void random() {
        skeleton(new int[]{9, 3, 10, 2, 5, 4, 8, 1, 6, 7});
    }
}