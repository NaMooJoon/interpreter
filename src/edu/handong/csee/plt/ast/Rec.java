package edu.handong.csee.plt.ast;


public class Rec extends RBMRCFAE {

    String rfunName;
    RBMRCFAE rfunExpr;
    RBMRCFAE body;

    public Rec(String rfunName, RBMRCFAE rfunExpr, RBMRCFAE body) {
        this.rfunName = rfunName;
        this.rfunExpr = rfunExpr;
        this.body = body;
    }

    public String getRfunName() {
        return rfunName;
    }

    public RBMRCFAE getRfunExpr() {
        return rfunExpr;
    }

    public RBMRCFAE getBody() {
        return body;
    }

    public String getASTCode() {
        return "(rec '" + rfunName + " " + rfunExpr.getASTCode() + " " + body.getASTCode() + ")";
    }

}
