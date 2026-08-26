package com.rajkumar;

import java.awt.*;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now);
        Point point1 = new Point(1,1);
        Point point2 = point1;
        point1.x = 4;
        System.out.println(point2.x);
        }
    }
