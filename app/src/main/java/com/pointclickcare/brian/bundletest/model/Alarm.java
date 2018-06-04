package com.pointclickcare.brian.bundletest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Alarm {
    public String name;
    public Date time;
    public Boolean[] days;

    public String getFormattedTime() {
        return new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(time);
    }

    public String getDaysString(String[] daysStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.length; i++) {
            if (days[i]) sb.append(daysStr[i] + " ");
        }

        return sb.toString();
    }
}
