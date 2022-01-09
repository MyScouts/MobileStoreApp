package com.example.handler;

import java.util.ArrayList;

public class MyHander {

    public static String formatPrice(String price) {
        String strPrice = String.valueOf(price);
        String result = "";

        ArrayList<Character> listChar = new ArrayList<Character>();
        int point = 1;

        for (int i = strPrice.length() - 1; i >= 0; i--) {
            if (point == 4) {
                point = 1;
                listChar.add('.');
            }
            listChar.add(strPrice.charAt(i));
            point++;
        }
        for (int i = listChar.size() - 1; i >= 0; i--) {
            result += listChar.get(i);
        }

        return result;
    }
}
