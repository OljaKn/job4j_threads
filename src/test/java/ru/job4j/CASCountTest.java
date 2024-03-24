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

    @Test
    void whenIncrementIs1() throws InterruptedException {
        CASCount cas = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        cas.increment();
                    }
                });
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        cas.increment();
                    }
                });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(cas.get()).isEqualTo(202);
    }
}