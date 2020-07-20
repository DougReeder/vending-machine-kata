package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class CoinData {
    final double minWeight;
    final double maxWeight;
    final int cents;

    public static final List<CoinData> validCoins;
    static {
        List<CoinData> validCoinsMutable = Arrays.asList(
            new CoinData(1.900, 2.1, 5),
            new CoinData(2.168, 2.368, 10),
            new CoinData(5.570, 5.770, 25)
        );
        validCoins = Collections.unmodifiableList(validCoinsMutable);
    }

    CoinData(double minWeight, double maxWeight, int cents) {
        if (minWeight <= 0) {
            throw new ModelException("minWeight must be > 0: " + minWeight);
        }
        if (maxWeight <= 0) {
            throw new ModelException("maxWeight must be > 0: " + maxWeight);
        }
        if (maxWeight < minWeight) {
            throw new ModelException("maxWeight must be >= minWeight: " + maxWeight);
        }
        if (cents <= 0) {
            throw new ModelException("cents must be > 0: " + cents);
        }
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.cents = cents;
    }
}
