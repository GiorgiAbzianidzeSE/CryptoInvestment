package com.example.cryptoinvestment.utils;

import com.example.cryptoinvestment.model.CryptoData;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtility {

    public static List<CryptoData> getCSVData(String directoryPath) {
        List<CryptoData> allCSVData = new ArrayList<>();

        ClassLoader classLoader = CSVReaderUtility.class.getClassLoader();
        URL resource = classLoader.getResource(directoryPath);

        if (resource != null) {
            String fullPath = Paths.get(resource.getPath()).toString();
            File folder = new File(fullPath);
            File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        allCSVData.addAll(readCSVFile(file.getAbsolutePath()));
                    }
                }
            } else {
                System.out.println("The directory is empty or does not exist.");
            }
        } else {
            System.out.println("Directory not found in the classpath.");
        }

        return allCSVData;
    }

    private static List<CryptoData> readCSVFile(String filePath) {
        List<CryptoData> records = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            CsvToBean<CryptoData> csvToBean = new CsvToBeanBuilder<CryptoData>(reader)
                    .withType(CryptoData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            records = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}

