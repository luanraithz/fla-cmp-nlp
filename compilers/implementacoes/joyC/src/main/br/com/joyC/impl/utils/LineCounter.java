package main.br.com.joyC.impl.utils;

public class LineCounter {
    public static Integer count(String str) {
        return str.split(System.getProperty("line.separator")).length;
    }

}
