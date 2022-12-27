package edu.handong.csee.plt.ast;

public class Setbox extends RBMRCFAE {

    RBMRCFAE id;
    RBMRCFAE expr;

    public Setbox(RBMRCFAE id, RBMRCFAE expr) {
        this.id = id;
        this.expr = expr;
    }

    public RBMRCFAE getId() {
        return id;
    }

    public void setId(RBMRCFAE id) {
        this.id = id;
    }

    public RBMRCFAE getExpr() {
        return expr;
    }

    public void setExpr(RBMRCFAE expr) {
        this.expr = expr;
    }

    public String getASTCode() {
        return "(setbox " + id.getASTCode() + " " + expr.getASTCode() + ")";
    }

}
