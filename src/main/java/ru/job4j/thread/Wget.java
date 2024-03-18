package ru.job4j.thread;

import java.io.*;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var startAt = System.currentTimeMillis();
        String filename = url.substring(url.lastIndexOf('/') + 1);
        try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream output = new FileOutputStream(filename)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int countDownload = 0;
            while ((bytesRead = input.read(dataBuffer, 0, 1024)) != -1) {
                countDownload += bytesRead;
                if (countDownload >= speed) {
                    long time = System.currentTimeMillis() - startAt;
                    if (time < 1000) {
                        Thread.sleep(time);
                    }
                    countDownload = 0;
                    startAt = System.currentTimeMillis();
                }
                output.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            throw new IllegalArgumentException("No all arguments.");
        }
        String url = args[0];
        if (url == null) {
            throw new IllegalArgumentException("Url is incorrect");
        }
        int speed = Integer.parseInt(args[1]);
        if (speed == 0) {
            throw new IllegalArgumentException("Parametr is not.");
        }
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
