package edu.handong.csee.plt.RBMRCFAE_Value;

public class boxV extends RBMRCFAE_Value {
    int address;

    public boxV(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public String getCode() {
        return "(boxV " + address + ")";
    }

}
