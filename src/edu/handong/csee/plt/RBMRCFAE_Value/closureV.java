package edu.handong.csee.plt.RBMRCFAE_Value;
import edu.handong.csee.plt.DefrdSub.*;
import edu.handong.csee.plt.ast.RBMRCFAE;

public class closureV extends RBMRCFAE_Value {
    
    String symbol;
    RBMRCFAE body; // body is a RBMRCFAE_Value
    DefrdSub defrdSub;

    public closureV(String symbol, RBMRCFAE body, DefrdSub defrdSub) {
        this.symbol = symbol;
        this.body = body;
        this.defrdSub = defrdSub;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public RBMRCFAE getBody() {
        return body;
    }
    
    public void setBody(RBMRCFAE body) {
        this.body = body;
    }

    public DefrdSub getDefrdSub() {
        return defrdSub;
    }

    public void setDefrdSub(DefrdSub defrdSub) {
        this.defrdSub = defrdSub;
    }

    public String getCode() {
        return "(closureV " + symbol + " " + body.getASTCode() + " " + defrdSub.getDefrdSubCode() + ")";
    }
    
}
