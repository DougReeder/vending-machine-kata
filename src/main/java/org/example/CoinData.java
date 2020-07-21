package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

class CoinData {
    final double nominalWeight;
    final double minWeight;
    final double maxWeight;
    final int cents;

    public static final Map<Integer,CoinData> validCoins;
    static {
        Map<Integer,CoinData> validCoinsMutable = new HashMap<>();
        validCoinsMutable.put(5, new CoinData(2.000, 1.900, 2.1, 5));
        validCoinsMutable.put(10, new CoinData(2.268, 2.168, 2.368, 10));
        validCoinsMutable.put(25, new CoinData(5.670, 5.570, 5.770, 25));
        validCoins = Collections.unmodifiableMap(validCoinsMutable);
    }

    CoinData(double nominalWeight, double minWeight, double maxWeight, int cents) {
        if (minWeight <= 0) {
            throw new ModelException("minWeight must be > 0: " + minWeight);
        }

        if (nominalWeight < minWeight) {
            throw new ModelException("nominalWeight must be >= minWeight: " + nominalWeight);
        }

        if (maxWeight < nominalWeight) {
            throw new ModelException("maxWeight must be >= nominalWeight: " + maxWeight);
        }

        if (cents <= 0) {
            throw new ModelException("cents must be > 0: " + cents);
        }
        this.nominalWeight = nominalWeight;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.cents = cents;
    }
}
