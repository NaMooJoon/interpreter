package edu.handong.csee.plt.ast;

public class Newbox extends RBMRCFAE {

    RBMRCFAE expr;

    public Newbox(RBMRCFAE expr) {
        this.expr = expr;
    }

    public RBMRCFAE getExpr() {
        return expr;
    }

    public String getASTCode() {
        return "(newbox " + expr.getASTCode() + ")";
    }

}
