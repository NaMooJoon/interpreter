package edu.handong.csee.plt.DefrdSub;

import edu.handong.csee.plt.RBMRCFAE_Value.*;

public class aSub extends DefrdSub {

    String symbol = new String();
    int address = -1;
    DefrdSub rest = new DefrdSub();

    public aSub(String symbol, int value, DefrdSub rest) {
        this.symbol = symbol;
        this.address = value;
        this.rest = rest;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getAddress() {
        return address;
    }

    public DefrdSub getRest() {
        return rest;
    }

    public String getDefrdSubCode() {
        return "(aSub '" + symbol + " " + address + " " + rest.getDefrdSubCode() + ")";
    }
}
