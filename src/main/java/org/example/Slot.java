package org.example;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

/**
 * Slots and buttons are labeled "A" through "Z" or "AA" through "JJ".
 */
 class Slot {
    final String slotId;
    int centsCost;
    int quantity;

    public Slot(String slotId, int centsCost, int quantity) {
        switch (slotId.length()) {
            case 1:
                if (slotId.charAt(0) < 'A' || slotId.charAt(0) > 'Z') {
                    throw new ModelException("not a valid slot:" + slotId);
                }
                break;
            case 2:
                if (slotId.charAt(0) < 'A' || slotId.charAt(0) > 'T' || slotId.charAt(0) != slotId.charAt(1)) {
                    throw new ModelException("not a valid slot:" + slotId);
                }
                break;
            default:
                throw new ModelException("not a valid slot:" + slotId);
        }
        if (centsCost <= 0) {
            throw new ModelException("cost must be greater than 0: " + centsCost);
        }
        if (quantity < 0) {
            throw new ModelException("quantity must be 0 or more: " + quantity);
        }
        this.slotId = slotId;
        this.centsCost = centsCost;
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Slot)) {
            return false;
        }
        String oId = ((Slot)o).slotId;
        return null == slotId ? null == oId : slotId.equals(oId);
    }

    public int hashCode() {
        return (int)slotId.charAt(0);
    }

    public String toString() {
        return slotId + ' ' + centsCost + "¢ " + quantity;
    }
}
