package com.example.cryptoinvestment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoData {
    private long timestamp;
    private String symbol;
    private double price;
}
