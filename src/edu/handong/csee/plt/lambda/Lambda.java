package edu.handong.csee.plt.lambda;

import edu.handong.csee.plt.RBMRCFAE_Value.RBMRCFAE_Value;
import edu.handong.csee.plt.Store.Store;
import edu.handong.csee.plt.ValueStore.VS;

public interface Lambda {
    VS run(RBMRCFAE_Value expr1, RBMRCFAE_Value expr2, Store sto);
}
