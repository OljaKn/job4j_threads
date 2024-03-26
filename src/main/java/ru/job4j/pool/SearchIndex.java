package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class SearchIndex extends RecursiveTask<Integer> {
    private final int[] array;
    private final int searchElement;
    private final int first;
    private final int last;

    public SearchIndex(int[] array, int searchElement, int first, int last) {
        this.array = array;
        this.searchElement = searchElement;
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        if (last - first < 10) {
            for (int i = first; i <= last; i++) {
                if (searchElement == array[i]) {
                    return i;
                }
            }
            return -1;
        }
        int middle = (first + last) / 2;
        SearchIndex left = new SearchIndex(array, searchElement, first, middle);
        SearchIndex right = new SearchIndex(array, searchElement, middle, last);
        left.fork();
        right.fork();
        int leftRsl = left.join();
        int rightRsl = right.join();
        if (leftRsl != -1) {
            return leftRsl;
        } else {
            return rightRsl;
        }
    }
}
