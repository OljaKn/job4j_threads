package ru.job4j.pools;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RolColSumTest {
    @Test
    public void testSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        Sums[] rsl = RolColSum.sum(matrix);
        assertEquals(expected.length, rsl.length);
        assertThat(expected[0]).isEqualTo(rsl[0]);
    }

    @Test
    public void testAsyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };
        Sums[] rsl = RolColSum.asyncSum(matrix);
        assertEquals(expected.length, rsl.length);
        assertThat(expected[1]).isEqualTo(rsl[1]);
        assertThat(expected[2]).isEqualTo(rsl[2]);
    }
}