package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.example.CoinData;


public class VendingMachine {
    int totalCents = 0;
    List<Double> coinReturn = new ArrayList<Double>();

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
}
