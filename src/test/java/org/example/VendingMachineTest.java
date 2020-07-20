package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;


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
    @CsvSource({"2.500,1", "3.11,1", "2.000,5", "2.268,10", "5.670,25", "11.340,50", "8.1,100", "22.68,100"})
    void shouldAllowCoinInsertion(double weight, int cents) {
        vendingMachine.insertCoin(weight);

    }

 }
