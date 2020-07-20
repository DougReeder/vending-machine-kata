package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachine();
    }

    @AfterEach
    void tearDown() {
    }


    @ParameterizedTest
    @CsvSource({"2.000,5", "2.268,10", "5.670,25"})
    void shouldAllowValidCoinInsertion(double weight, int cents) {
        vendingMachine.insertCoin(weight);

        assertEquals(cents, vendingMachine.getTotalCents());
        assertEquals(0, vendingMachine.getCoinReturn().size());
    }

    @ParameterizedTest
    @CsvSource({"2.500,1", "3.11,1", "11.340,50", "8.1,100", "22.68,100"})
    void shouldRejectInvalidCoinInsertion(double weight, int cents) {
        vendingMachine.insertCoin(weight);

        assertEquals(0, vendingMachine.getTotalCents());

        List<Double> expected = new ArrayList<Double>();
        expected.add(weight);
        assertEquals(expected, vendingMachine.getCoinReturn());
    }

    @ParameterizedTest
    @CsvSource({"2.05,5, 2.51,5, 5.671,30, 2.200,40",
            "1.8,0, 8.1,0, 5.7,25, 5.58,50"})
    void shouldAcceptAndRejectMultipleCoins(double weight1, int centsTotal1, double weight2, int centsTotal2, double weight3, int centsTotal3, double weight4, int centsTotal4) {
        vendingMachine.insertCoin(weight1);
        assertEquals(centsTotal1, vendingMachine.getTotalCents());

        vendingMachine.insertCoin(weight2);
        assertEquals(centsTotal2, vendingMachine.getTotalCents());

        vendingMachine.insertCoin(weight3);
        assertEquals(centsTotal3, vendingMachine.getTotalCents());

        vendingMachine.insertCoin(weight4);
        assertEquals(centsTotal4, vendingMachine.getTotalCents());
    }
}
