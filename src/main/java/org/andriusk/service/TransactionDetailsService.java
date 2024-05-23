package org.andriusk.service;

import org.andriusk.dto.TransactionDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionDetailsService {
    public static void generateReport(List<TransactionDetails> transactionDetails) {
        System.out.println("Total Revenue: " + calculateTotalRevenue(transactionDetails));
        System.out.println("Unique Customers: " + calculateUniqueCustomers(transactionDetails));
        System.out.println("Most Popular Item: " + findMostPopularProduct(transactionDetails));
        System.out.println("Date with Highest Revenue: " + findMostProfitableDate(transactionDetails));
    }
    public static Double calculateTotalRevenue(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .mapToDouble(TransactionDetails::getItemPrice)
                .sum();
    }

    public static int calculateUniqueCustomers(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .map(TransactionDetails::getCustomerId)
                .collect(Collectors.toSet())
                .size();
    }

    public static String findMostPopularProduct(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .map(TransactionDetails::getItemId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                // This doesn't handle scenarios where there are multiple equally-popular products, but I'm a bit
                // short on time to properly implement it.
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElseThrow();
    }

    public static LocalDate findMostProfitableDate(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .collect(Collectors.groupingBy(TransactionDetails::getTransactionDate,
                        Collectors.summingDouble(TransactionDetails::getItemPrice)))
                .entrySet()
                .stream()
                // Ditto
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElseThrow();
    }
}
