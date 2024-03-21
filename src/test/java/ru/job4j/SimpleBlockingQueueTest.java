package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SimpleBlockingQueueTest {
    Queue<Integer> queue = new LinkedList<>();
    @Test
    void whenOfferAndPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simple = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        simple.offer(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        });
        Thread consumer = new Thread(() -> {
                try {
                    simple.poll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue).isNotNull();

    }

}