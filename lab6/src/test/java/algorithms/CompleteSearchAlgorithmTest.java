package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CompleteSearchAlgorithmTest {
    private CompleteSearchAlgorithm completeSearchAlgorithm = new CompleteSearchAlgorithm();
    private double[][] matrix;

    @BeforeEach
    void setUp() {
        matrix = new double[5][5];
        for (var doubles : matrix) {
            Arrays.fill(doubles, -1);
        }
    }

    @Test
    void oneWay() {
        matrix[0][1] = matrix[1][0] = 1;
        matrix[1][2] = matrix[2][1] = 2;
        matrix[2][3] = matrix[3][2] = 3;
        matrix[3][4] = matrix[4][3] = 4;
        matrix[4][0] = matrix[0][4] = 5;
        assertEquals(15, completeSearchAlgorithm.solve(matrix));
    }

    @Test
    void noWay() {
        matrix[0][1] = matrix[1][0] = 1;
        matrix[1][2] = matrix[2][1] = 2;
        matrix[2][0] = matrix[0][2] = 3;
        matrix[3][4] = matrix[4][3] = 4;
        assertEquals(-1, completeSearchAlgorithm.solve(matrix));
    }

    @Test
    void bestWay() {
        matrix[0][1] = matrix[1][0] = 10;
        matrix[1][2] = matrix[2][1] = 10;
        matrix[2][3] = matrix[3][2] = 15;
        matrix[3][4] = matrix[4][3] = 35;
        matrix[0][4] = matrix[4][0] = 10;
        matrix[4][1] = matrix[1][4] = 20;
        matrix[3][0] = matrix[0][3] = 20;
        assertEquals(75, completeSearchAlgorithm.solve(matrix));
    }

    @Test
    void overtimeWay() {
        matrix[0][1] = matrix[1][0] = 10;
        matrix[1][2] = matrix[2][1] = 10;
        matrix[2][3] = matrix[3][2] = 15;
        matrix[3][4] = matrix[4][3] = 35;
        matrix[0][4] = matrix[4][0] = 20;
        matrix[4][1] = matrix[1][4] = 20;
        matrix[3][0] = matrix[0][3] = 20;
        assertEquals(-1, completeSearchAlgorithm.solve(matrix));
    }
}