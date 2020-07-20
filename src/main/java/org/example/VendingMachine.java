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
    int totalCents = 0;
    final List<Double> coinReturn = new ArrayList<Double>();
    String display = INSERT_COIN;
    final Map<String,Slot> slotMap;
    final List<String> dispensed = new ArrayList<String>();

    static final String INSERT_COIN = "INSERT COIN";
    static final String ERROR = "ERROR";
    static final String THANK_YOU = "THANK YOU";
    static final String PRICE = "PRICE";
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
        for (CoinData coinData: CoinData.validCoins) {
            if (weight >= coinData.minWeight && weight <= coinData.maxWeight) {
                valid = true;
                cents = coinData.cents;
            }
        }

        if (valid) {
            totalCents += cents;
            setDisplayFromTotalCents();
        } else {
            coinReturn.add(weight);
        }
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

    public void buttonPushed(String slotId) {
        Slot slot = slotMap.get(slotId);
        if (null == slot) {
            display = ERROR;
            return;
        }
        if (totalCents >= slot.centsCost) {
            totalCents -= slot.centsCost;
            dispensed.add(slotId);
            --slot.quantity;
            display = THANK_YOU;
            // TODO: call setDisplayFromTotalCents() after delay
        } else {
            display = PRICE + " " + VendingMachine.DOLLARS_AND_CENTS.format(slot.centsCost/100.0);
            // TODO: call setDisplayFromTotalCents() after delay
        }
    }

    public List<String> getDispensed() {
        return Collections.unmodifiableList(dispensed);
    }

}
