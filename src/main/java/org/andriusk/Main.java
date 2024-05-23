package org.andriusk;

import org.andriusk.dto.TransactionDetails;
import org.andriusk.service.CsvService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.andriusk.service.TransactionDetailsService.generateReport;

public class Main {
    public static final String SAMPLE_CSV = "src/main/resources/sample.csv";

    public static void main(String[] args) throws IOException {
        String file = Files.readString(Path.of(SAMPLE_CSV));
        CsvService csvService = new CsvService();
        List<TransactionDetails> transactionDetails = csvService.parseCsv(file, TransactionDetails.class);

        generateReport(transactionDetails);
    }

}