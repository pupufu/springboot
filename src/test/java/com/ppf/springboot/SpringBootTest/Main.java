package com.ppf.springboot.SpringBootTest;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        String line3 = scanner.nextLine();

        int total = Integer.parseInt(line1);
        int[] prices = change(line2);
        int num = Integer.valueOf(line3);
        fun(prices, num);
    }

    private static void fun(int[] prices, int num) {
        if (num > prices.length) {
            new Exception();
        }
        Arrays.sort(prices);
        int total = 0;
        for (int i = 0; i < num; i++) {
            total = total + prices[i];
        }
        System.out.println(total);
    }

    private static int[] change(String line2) {
        String[] s = line2.split(" ");
        int[] prices = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            String str = s[i].trim();
            int price = Integer.valueOf(str);
            if (price < 128) {
                prices[i] = price;
            } else {
                new Exception();
            }

        }
        return prices;
    }


}
