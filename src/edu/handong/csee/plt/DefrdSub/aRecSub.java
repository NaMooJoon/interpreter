package edu.handong.csee.plt.DefrdSub;

public class aRecSub extends DefrdSub {

    String symbol = new String();
    // Box value = new Box();
    int address = -1;
    DefrdSub rest = new DefrdSub();

    public aRecSub(String symbol, int address, DefrdSub rest) {
        this.symbol = symbol;
        this.address = address;
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
        return "(aRecSub '" + symbol + " " + address + " " + rest.getDefrdSubCode() + ")";
    }

}
