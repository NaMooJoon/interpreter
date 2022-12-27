package edu.handong.csee.plt.ast;

public class if0 extends RBMRCFAE {
    RBMRCFAE cond;
    RBMRCFAE thenBody;
    RBMRCFAE elseBody;

    public if0(RBMRCFAE cond, RBMRCFAE thenBody, RBMRCFAE elseBody) {
        this.cond = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public RBMRCFAE getCond() {
        return cond;
    }

    public RBMRCFAE getThenBody() {
        return thenBody;
    }

    public RBMRCFAE getElseBody() {
        return elseBody;
    }

    public String getASTCode() {
        return "(if0 " + cond.getASTCode() + " " + thenBody.getASTCode() + " " + elseBody.getASTCode() + ")";
    }
    
}
