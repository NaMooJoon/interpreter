package edu.handong.csee.plt.ValueStore;

import edu.handong.csee.plt.RBMRCFAE_Value.RBMRCFAE_Value;
import edu.handong.csee.plt.Store.Store;

public class VS {
    RBMRCFAE_Value value;
    Store store;

    public VS(RBMRCFAE_Value value, Store store) {
        this.value = value;
        this.store = store;
    }

    public RBMRCFAE_Value getValue() {
        return value;
    }

    public Store getStore() {
        return store;
    }

    public void setValue(RBMRCFAE_Value value) {
        this.value = value;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getVS() {
        return "(v*s " + value.getCode() + " " + store.getStoreCode() + ")";
    }

}
