package com.example.cryptoinvestment.controller;

import com.example.cryptoinvestment.model.CryptoDataResponse;
import com.example.cryptoinvestment.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/all")
    public List<List<CryptoDataResponse>> getAllCryptoData(){
        return recommendationService.getAllCryproData();
    }
    @GetMapping("/min")
    public List<CryptoDataResponse> getMinCryproData(){
        return recommendationService.getMinCryproData();
    }
    @GetMapping("/max")
    public List<CryptoDataResponse> getMaxCryproData(){
        return recommendationService.getMaxCryproData();
    }
    @GetMapping("/sorted")
    public List<CryptoDataResponse> getSortedAllCryptoData(){
        return recommendationService.getSortedAllCryptoData();
    }
    @GetMapping("oldest/{crypto}")
    public CryptoDataResponse getOldestCryptoByName(@PathVariable String crypto){
        return recommendationService.getOldestCryptoByName(crypto);
    }
    @GetMapping("newest/{crypto}")
    public CryptoDataResponse getNewestCryptoByName(@PathVariable String crypto){
        return recommendationService.getNewestCryptoByName(crypto);
    }
    @GetMapping("min/{crypto}")
    public CryptoDataResponse getMinCryptoByName(@PathVariable String crypto){
        return recommendationService.getMinCryptoByName(crypto);
    }
    @GetMapping("max/{crypto}")
    public CryptoDataResponse getMaxCryptoByName(@PathVariable String crypto){
        return recommendationService.getMaxCryptoByName(crypto);
    }
}
