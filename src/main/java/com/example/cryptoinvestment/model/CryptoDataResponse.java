package com.example.cryptoinvestment.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CryptoDataResponse {
    private String timestamp;
    private String symbol;
    private double price;

    public static String convertTimestampToString(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}

