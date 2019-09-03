package com.mkaz.topgames.service;

public class ColorService {

    public static String getColor(double number) {
        if (number <= 5) {
            return "red";
        } else {
            if (number > 5 && number <= 8) return "#eaec00";
        }
        return "lawngreen";
    }
}
