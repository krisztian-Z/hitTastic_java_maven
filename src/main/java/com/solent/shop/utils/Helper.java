package com.solent.shop.utils;

import java.util.Formatter;

public class Helper {

    public static String reduceDescriptionToGivenSize(String desc, int number) {
        String[] words = desc.split(" ");
        if (words.length > number) {
            String txtLine = "";
            for (int i = 0; i < 10; i++) {
                txtLine = txtLine + words[i] + " ";
            }
            return (txtLine + "....");
        } else {
            return (desc + " ....");
        }
    }

    public static String priceFormaterTwoDecimalPlaces(double amount) {
        Formatter fm = new Formatter();
        String price = fm.format("%.2f", amount).toString();
        return price;
    }
}
