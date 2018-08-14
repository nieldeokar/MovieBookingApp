package com.tentwenty.movieticket.utils.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {

    public static String convertDate(String inputString){

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

        try {
           return sdf.format(new SimpleDateFormat("yyyy-M-dd", Locale.getDefault()).parse(inputString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return inputString;

    }
}
