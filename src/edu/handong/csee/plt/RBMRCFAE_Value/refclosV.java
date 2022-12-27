package edu.handong.csee.plt.RBMRCFAE_Value;

import edu.handong.csee.plt.DefrdSub.DefrdSub;
import edu.handong.csee.plt.ast.RBMRCFAE;

public class refclosV extends RBMRCFAE_Value {

    String symbol;
    RBMRCFAE body;
    DefrdSub defrdSub;

    public refclosV(String symbol, RBMRCFAE body, DefrdSub defrdSub) {
        this.symbol = symbol;
        this.body = body;
        this.defrdSub = defrdSub;
    }

    public String getSymbol() {
        return symbol;
    }

    public RBMRCFAE getBody() {
        return body;
    }

    public DefrdSub getDefrdSub() {
        return defrdSub;
    }

    public String getCode() {
        return "(refclosV " + symbol + " " + body.getASTCode() + " " + defrdSub.getDefrdSubCode() + ")";
    }

}
