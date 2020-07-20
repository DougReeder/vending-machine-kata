package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;

import org.example.CoinData;


public class VendingMachine {
    int totalCents = 0;
    List<Double> coinReturn = new ArrayList<Double>();
    String display = INSERT_COIN;

    static final String INSERT_COIN = "INSERT COIN";
    static final DecimalFormat DOLLARS_AND_CENTS = new DecimalFormat("$0.00");

    public void insertCoin(double weight) {
        boolean valid = false;
        int cents = 0;
        for (CoinData coinData: CoinData.validCoins) {
            if (weight >= coinData.minWeight && weight <= coinData.maxWeight) {
                valid = true;
                cents = coinData.cents;
            }
        }

        if (valid) {
            totalCents += cents;
            display = DOLLARS_AND_CENTS.format(totalCents/100.0);
        } else {
            coinReturn.add(weight);
        }
    }

    public int getTotalCents() {
        return totalCents;
    }

    public List<Double> getCoinReturn() {
        return Collections.unmodifiableList(coinReturn);
    }

    public String getDisplay() {
        return display;
    }
}
