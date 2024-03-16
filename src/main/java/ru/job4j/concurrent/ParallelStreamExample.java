package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.List;


public class ParallelStreamExample {
   /* public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());
        Optional<Integer> multiplication = stream.reduce((left, right) -> left * right);
        System.out.println(multiplication.get());
    }
   public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().parallel().peek(System.out::println).toList();
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().parallel().forEach(System.out::println);
    }*/
   public static void main(String[] args) {
       List<Integer> list = Arrays.asList(1, 3, 4, 5, 2);
       list.stream().parallel().forEachOrdered(System.out::println);
   }
}