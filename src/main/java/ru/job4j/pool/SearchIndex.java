package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T searchElement;
    private final int first;
    private final int last;

    public SearchIndex(T[] array, T searchElement, int first, int last) {
        this.array = array;
        this.searchElement = searchElement;
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        if (last - first < 10) {
            return search();
        }
        int middle = (first + last) / 2;
        SearchIndex left = new SearchIndex(array, searchElement, first, middle);
        SearchIndex right = new SearchIndex(array, searchElement, middle, last);
        left.fork();
        right.fork();
        return Math.max((Integer) left.join(), (Integer) right.join());
    }

     private int search() {
         for (int i = first; i <= last; i++) {
             if (searchElement.equals(array[i])) {
                 return i;
             }
         }
         return -1;
     }

     public static <T> int startSearchIndex(T[] array, T el) {
         ForkJoinPool fjp = new ForkJoinPool();
         return fjp.invoke(new SearchIndex<>(array, el, 0, array.length - 1));
    }
}
