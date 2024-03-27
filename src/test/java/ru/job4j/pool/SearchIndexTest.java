package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ForkJoinPool;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchIndexTest {
    @Test
            void whenSearchIntElement() {
        Integer[] array = {1, 5, 8, 7, 6, 0, 8, 6, 9, 10, 58, 4, 35};
        Integer element = 10;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int expected = (int) forkJoinPool.invoke(new SearchIndex(array, element, 0, array.length - 1));
        assertThat(expected).isEqualTo(9);
    }

    @Test
    void whenSearchIntElementMin() {
        Integer[] array = {1, 5, 8, 7};
        Integer element = 5;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int expected = (int) forkJoinPool.invoke(new SearchIndex(array, element, 0, array.length - 1));
        assertThat(expected).isEqualTo(1);
    }

    @Test
    void whenSearchIntElementIsNot() {
        Integer[] array = {1, 5, 8, 7, 7, 9, 40, 65, 8, 77, 30, 4, 0, 9};
        Integer element = 100;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int expected = (int) forkJoinPool.invoke(new SearchIndex(array, element, 0, array.length - 1));
        assertThat(expected).isEqualTo(-1);
    }

    @Test
    void whenSearchStringElement() {
        String[] array = {"petr", "oljga", "dog", "cat", "pool", "milk"};
        String element = "pool";
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int expected = (int) forkJoinPool.invoke(new SearchIndex(array, element, 0, array.length - 1));
        assertThat(expected).isEqualTo(4);
    }
}