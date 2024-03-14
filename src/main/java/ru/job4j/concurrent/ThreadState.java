package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        System.out.println(first.getState());
        Thread second = new Thread(
                () -> { }
        );
        System.out.println(second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
            System.out.println(second.getState());
        }
        System.out.println(first.getState());
        System.out.println(second.getState());
    }
}
