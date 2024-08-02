package com.example.cryptoinvestment.service;

import com.example.cryptoinvestment.model.CryptoDataResponse;

import java.util.List;

public interface RecommendationService {
    List<List<CryptoDataResponse>> getAllCryproData();

    List<CryptoDataResponse> getMinCryproData();

    List<CryptoDataResponse> getMaxCryproData();

    List<CryptoDataResponse> getSortedAllCryptoData();

    CryptoDataResponse getOldestCryptoByName(String crypto);

    CryptoDataResponse getNewestCryptoByName(String crypto);

    CryptoDataResponse getMinCryptoByName(String crypto);

    CryptoDataResponse getMaxCryptoByName(String crypto);
}
