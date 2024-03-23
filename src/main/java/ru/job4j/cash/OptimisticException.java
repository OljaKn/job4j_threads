package ru.job4j.cash;

public class OptimisticException extends Exception {
    public OptimisticException(String message) {
        super(message);
    }
}