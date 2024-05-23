package org.andriusk.service;

import org.andriusk.dto.TransactionDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        details1.setItemId("someItem1");
        details2.setItemId("someItem2");
        details3.setItemId("someItem1");

        assertEquals("someItem1", TransactionDetailsService.findMostPopularProduct(List.of(details1, details2, details3)));
    }

    @Test
    void findMostProfitableDate() {
        TransactionDetails details1 = new TransactionDetails();
        TransactionDetails details2 = new TransactionDetails();
        TransactionDetails details3 = new TransactionDetails();


        details1.setItemPrice(1.01d);
        details1.setTransactionDate(LocalDate.now());
        details2.setItemPrice(5.04d);
        details2.setTransactionDate(LocalDate.now().plus(1, ChronoUnit.DAYS));
        details3.setItemPrice(7.00d);
        details3.setTransactionDate(LocalDate.now());

        assertEquals(LocalDate.now(), TransactionDetailsService.findMostProfitableDate(List.of(details1, details2, details3)));

    }
}