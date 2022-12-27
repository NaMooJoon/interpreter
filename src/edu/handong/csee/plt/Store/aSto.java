package edu.handong.csee.plt.Store;

import edu.handong.csee.plt.RBMRCFAE_Value.RBMRCFAE_Value;

public class aSto extends Store {

    int address;
    RBMRCFAE_Value value;
    Store rest;

    public aSto(int address, RBMRCFAE_Value value, Store rest) {
        this.address = address;
        this.value = value;
        this.rest = rest;
    }

    public int getAddress() {
        return address;
    }
    
    public void setAddress(int address) {
        this.address = address;
    }

    public RBMRCFAE_Value getValue() {
        return value;
    }

    public void setValue(RBMRCFAE_Value value) {
        this.value = value;
    }

    public Store getRest() {
        return rest;
    }

    public String getStoreCode() {

        return "(aSto " + address + " " + value.getCode() + " " + rest.getStoreCode() + ")";
    }

}