package ru.job4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class SimpleBlockingQueueTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }

    @Test
    public void whenFetchAllThenGetItTwo() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2);
        assertThat(buffer.size()).isNotNull();
        assertThat(buffer.contains(1));
    }

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