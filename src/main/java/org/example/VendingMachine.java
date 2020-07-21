package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

import org.example.CoinData;

// TODO: implement restocking method


/**
 * Hardware model: For each of 36 product slots, there's an associated button.
 * Slots and buttons are labeled "A" through "Z" or "AA" through "JJ".
 * Vending machines are simple creatures; products don't exist apart from their slot.
 */
public class VendingMachine {
    // members are package-private to allow administratively adding credit, technician test mode, etc.
    // The requirements don't include anything that would make setters and getters of value.
    boolean exactChangeOnly = false;   // TODO: model coins available for change
    final List<Double> activeCoins = new ArrayList<Double>();
    int totalCents = 0;
    final List<Double> coinReturn = new ArrayList<Double>();
    String display = INSERT_COIN;
    final Map<String,Slot> slotMap;
    final List<String> dispensed = new ArrayList<String>();

    static final String INSERT_COIN = "INSERT COIN";
    static final String ERROR = "ERROR";
    static final String THANK_YOU = "THANK YOU";
    static final String PRICE = "PRICE";
    static final String SOLD_OUT = "SOLD OUT";
    static final String EXACT_CHANGE_ONLY = "EXACT CHANGE ONLY";
    static final DecimalFormat DOLLARS_AND_CENTS = new DecimalFormat("$0.00");

    public VendingMachine() {
        slotMap = new HashMap<>();
        slotMap.put("A", new Slot("A", 100, 3));   // cola
        slotMap.put("B", new Slot("B", 50, 5));   // chips
        slotMap.put("DD", new Slot("DD", 65, 0));   // candy
    }

    public void insertCoin(double weight) {
        boolean valid = false;
        int cents = 0;
        for (CoinData coinData: CoinData.validCoins.values()) {
            if (weight >= coinData.minWeight && weight <= coinData.maxWeight) {
                valid = true;
                cents = coinData.cents;
            }
        }

        if (valid) {
            activeCoins.add(weight);
            totalCents += cents;
            setDisplayFromTotalCents();
        } else {
            coinReturn.add(weight);
        }
    }

    public void returnCoins() {
        for (double weight: activeCoins) {
            coinReturn.add(weight);
        }
        activeCoins.clear();
        totalCents = 0;
        setDisplayFromTotalCents();
    }

    /** Given that totalCents is package-private, this method is perhaps redundant */
    int getTotalCents() {
        return totalCents;
    }

    public List<Double> getCoinReturn() {
        return Collections.unmodifiableList(coinReturn);
    }

    public String getDisplay() {
        return display;
    }

    void setDisplayFromTotalCents() {
        if (totalCents > 0) {
            display = DOLLARS_AND_CENTS.format(totalCents/100.0);
        } else if (exactChangeOnly) {
            display = EXACT_CHANGE_ONLY;
        } else {
            display = INSERT_COIN;
        }
    }

    int getStockAvailable(String slotId) {
        Slot slot = slotMap.get(slotId);
        if (null == slot) {
            throw new ModelException("Nothing configured for slot " + slotId);
        }
        return slot.quantity;
    }

    /** TODO: derive from model of coins available */
    boolean getExactChangeOnly() {
        return exactChangeOnly;
    }

    public void buttonPushed(String slotId) {
        Slot slot = slotMap.get(slotId);
        if (null == slot) {
            display = ERROR;
            return;
        }
        if (slot.quantity < 1) {
            display = SOLD_OUT;
        } else if (totalCents >= slot.centsCost) {
            activeCoins.clear();
            totalCents -= slot.centsCost;
            dispensed.add(slotId);
            --slot.quantity;
            display = THANK_YOU;
            // TODO: call setDisplayFromTotalCents() after delay
            // TODO: track actual coins on hand
            while (totalCents >= 25) {
                totalCents -= 25;
                coinReturn.add(CoinData.validCoins.get(25).nominalWeight);
            }
            while (totalCents >= 10) {
                totalCents -= 10;
                coinReturn.add(CoinData.validCoins.get(10).nominalWeight);
            }
            while (totalCents >= 5) {
                totalCents -= 5;
                coinReturn.add(CoinData.validCoins.get(5).nominalWeight);
            }
        } else {
            display = PRICE + " " + VendingMachine.DOLLARS_AND_CENTS.format(slot.centsCost/100.0);
            // TODO: call setDisplayFromTotalCents() after delay
        }
    }

    public List<String> getDispensed() {
        return Collections.unmodifiableList(dispensed);
    }

}
