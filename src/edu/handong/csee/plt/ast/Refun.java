package edu.handong.csee.plt.ast;

public class Refun extends RBMRCFAE {

    String id;
    RBMRCFAE expr;

    public Refun(String rfunName, RBMRCFAE rfunExpr) {
        this.id = rfunName;
        this.expr = rfunExpr;
    }

    public String getId() {
        return id;
    }

    public RBMRCFAE getExpr() {
        return expr;
    }

    public String getASTCode() {
        return "(refun '" + id + " " + expr.getASTCode() + ")";
    }
}
