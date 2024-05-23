package org.andriusk.service;

import org.andriusk.dto.TransactionDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

public class TransactionDetailsService {
    public static void generateReport(List<TransactionDetails> transactionDetails) {
        System.out.println("Total Revenue: " + calculateTotalRevenue(transactionDetails));
        System.out.println("Unique Customers: " + calculateUniqueCustomers(transactionDetails));
        System.out.println("Most Popular Item(s): " + findMostPopularProduct(transactionDetails));
        System.out.println("Date(s) with Highest Revenue: " + findMostProfitableDate(transactionDetails));
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

    public static Set<String> findMostPopularProduct(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .map(TransactionDetails::getItemId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                // Messy, but in case there are multiple products with the same popularity
                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toSet())))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).orElseThrow();
    }

    public static Set<LocalDate> findMostProfitableDate(List<TransactionDetails> transactionDetails) {
        return transactionDetails.stream()
                .collect(Collectors.groupingBy(TransactionDetails::getTransactionDate,
                        Collectors.summingDouble(TransactionDetails::getItemPrice)))
                .entrySet()
                .stream()
                // Ditto
                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toSet())))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).orElseThrow();
    }
}
