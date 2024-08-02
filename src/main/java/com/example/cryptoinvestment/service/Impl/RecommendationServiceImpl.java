package com.example.cryptoinvestment.service.Impl;

import com.example.cryptoinvestment.model.CryptoData;
import com.example.cryptoinvestment.model.CryptoDataResponse;
import com.example.cryptoinvestment.service.RecommendationService;
import com.example.cryptoinvestment.utils.CSVReaderUtility;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @SneakyThrows
    @Override
    public List<List<CryptoDataResponse>> getAllCryproData() {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        List<CryptoDataResponse> cryptoDataResponseList = cryptoDataList.stream().map(this::mapper).toList();
        return List.of(cryptoDataResponseList);
    }

    @SneakyThrows
    @Override
    public List<CryptoDataResponse> getMinCryproData() {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        Optional<CryptoDataResponse> cryptoDataResponseOptional = cryptoDataList.stream().min(Comparator.comparingDouble(CryptoData::getPrice)).map(this::mapper);
        return cryptoDataResponseOptional.stream().toList();
    }

    @SneakyThrows
    @Override
    public List<CryptoDataResponse> getMaxCryproData() {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        Optional<CryptoDataResponse> cryptoDataResponseOptional = cryptoDataList.stream().max(Comparator.comparingDouble(CryptoData::getPrice)).map(this::mapper);
        return cryptoDataResponseOptional.stream().toList();
    }

    @SneakyThrows
    @Override
    public List<CryptoDataResponse> getSortedAllCryptoData() {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        return cryptoDataList.stream().sorted().map(this::mapper).toList();
    }

    @SneakyThrows
    @Override
    public CryptoDataResponse getOldestCryptoByName(String crypto) {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        return cryptoDataList.stream()
                .filter(cryptoData -> cryptoData.getSymbol().equalsIgnoreCase(crypto))
                .min(Comparator.comparingLong(CryptoData::getTimestamp)).map(this::mapper).orElse(null);
    }

    @SneakyThrows
    @Override
    public CryptoDataResponse getNewestCryptoByName(String crypto) {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        return cryptoDataList.stream()
                .filter(cryptoData -> cryptoData.getSymbol().equalsIgnoreCase(crypto))
                .max(Comparator.comparingLong(CryptoData::getTimestamp)).map(this::mapper).orElse(null);
    }

    @SneakyThrows
    @Override
    public CryptoDataResponse getMinCryptoByName(String crypto) {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        return cryptoDataList.stream()
                .filter(cryptoData -> cryptoData.getSymbol().equalsIgnoreCase(crypto))
                .min(Comparator.comparingDouble(CryptoData::getPrice)).map(this::mapper).orElse(null);
    }

    @SneakyThrows
    @Override
    public CryptoDataResponse getMaxCryptoByName(String crypto) {
        List<CryptoData> cryptoDataList = CSVReaderUtility.getCSVData(getDirectory());
        return cryptoDataList.stream()
                .filter(cryptoData -> cryptoData.getSymbol().equalsIgnoreCase(crypto))
                .max(Comparator.comparingDouble(CryptoData::getPrice)).map(this::mapper).orElse(null);
    }
    private CryptoDataResponse mapper(CryptoData cryptoData){
        CryptoDataResponse mappedCryptoDataResponse = new CryptoDataResponse();
        mappedCryptoDataResponse.setTimestamp(String.valueOf(cryptoData.getTimestamp()));
        mappedCryptoDataResponse.setPrice(cryptoData.getPrice());
        mappedCryptoDataResponse.setSymbol(cryptoData.getSymbol());
        return mappedCryptoDataResponse;
    }
    private String getDirectory() throws IOException {
        Resource resource = new ClassPathResource("data");
        File directory = resource.getFile();
        return directory.getAbsolutePath();
    }
}
