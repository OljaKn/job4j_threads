package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int currentValue;
        int nextValue;
        do {
            currentValue = count.get();
            nextValue = currentValue + 1;
        } while (!count.compareAndSet(currentValue, nextValue));
    }

    public int get() {
        return count.get();
    }
}