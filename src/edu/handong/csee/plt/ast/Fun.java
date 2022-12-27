package edu.handong.csee.plt.ast;

public class Fun extends RBMRCFAE {

    String id;
    RBMRCFAE exp;
    
    public Fun(String id, RBMRCFAE exp) {
        this.id = id;
        this.exp = exp;
    }
    
    public String getId() {
        return id;
    }
    
    public RBMRCFAE getExp() {
        return exp;
    }
    
    public String getASTCode() {
        return "(fun '" + id + " " + exp.getASTCode() + ")";
    }
    
}
