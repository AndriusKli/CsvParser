package org.andriusk.service;

import org.andriusk.dto.TransactionDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionDetailsServiceTest {

    @Test
    void calculateTotalRevenue() {
        TransactionDetails details1 = new TransactionDetails();
        TransactionDetails details2 = new TransactionDetails();

        details1.setItemPrice(1.01d);
        details2.setItemPrice(5.04);

        Double result = TransactionDetailsService.calculateTotalRevenue(List.of(details1, details2));
        assertEquals(6.05d, result);
    }

    @Test
    void calculateUniqueCustomers() {
        TransactionDetails details1 = new TransactionDetails();
        TransactionDetails details2 = new TransactionDetails();
        TransactionDetails details3 = new TransactionDetails();

        details1.setCustomerId("customer1");
        details2.setCustomerId("customer3");
        details3.setCustomerId("customer3");

        assertEquals(2, TransactionDetailsService.calculateUniqueCustomers(List.of(details1, details2, details3)));
    }

    @Test
    void findMostPopularProduct() {
        TransactionDetails details1 = new TransactionDetails();
        TransactionDetails details2 = new TransactionDetails();
        TransactionDetails details3 = new TransactionDetails();
        TransactionDetails details4 = new TransactionDetails();
        TransactionDetails details5 = new TransactionDetails();

        details1.setItemId("someItem1");
        details2.setItemId("someItem2");
        details3.setItemId("someItem1");
        details4.setItemId("someItem2");
        details5.setItemId("someItem55");

        List<String> expectedValues = List.of("someItem1", "someItem2");
        assertTrue(TransactionDetailsService.findMostPopularProduct(List.of(details1, details2, details3, details4, details5)).containsAll(expectedValues));
    }

    @Test
    void findMostProfitableDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS);
        TransactionDetails details1 = new TransactionDetails();
        TransactionDetails details2 = new TransactionDetails();
        TransactionDetails details3 = new TransactionDetails();
        TransactionDetails details4 = new TransactionDetails();


        details1.setItemPrice(1.01d);
        details1.setTransactionDate(today);
        details2.setItemPrice(5.04d);
        details2.setTransactionDate(tomorrow);
        details3.setItemPrice(7.00d);
        details3.setTransactionDate(today);
        details4.setItemPrice(8.01d);
        details4.setTransactionDate(yesterday);

        List<LocalDate> expectedDates = List.of(today, yesterday);
        assertTrue(TransactionDetailsService.findMostProfitableDate(List.of(details1, details2, details3, details4)).containsAll(expectedDates));
    }
}