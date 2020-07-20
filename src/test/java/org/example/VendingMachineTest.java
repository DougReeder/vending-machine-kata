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

 }
