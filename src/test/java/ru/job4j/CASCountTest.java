package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {
    @Test
        void whenIncrementIs2() {
        CASCount cas = new CASCount();
        cas.increment();
        cas.increment();
        int expected = cas.get();
        assertThat(expected).isEqualTo(2);
    }

    @Test
    void whenIncrementIs3() {
        CASCount cas = new CASCount();
        cas.increment();
        cas.increment();
        cas.increment();
        int expected = cas.get();
        cas.increment();
        assertThat(expected).isEqualTo(3);
    }
}