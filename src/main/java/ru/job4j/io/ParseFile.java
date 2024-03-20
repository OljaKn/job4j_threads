package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

   public synchronized String content(Predicate<Character> filter) throws IOException {
       StringBuilder sb = new StringBuilder();
       try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
           int data;
           while ((data = input.read()) != -1) {
               if (filter.test((char) data)) {
                   sb.append(data);
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return sb.toString();
   }

    public String getContent() throws IOException {
        return content(data -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(data -> data < 0x80);
    }
}
