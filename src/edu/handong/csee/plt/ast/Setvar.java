package edu.handong.csee.plt.ast;

public class Setvar extends RBMRCFAE {

    String id;
    RBMRCFAE val;

    public Setvar(String id, RBMRCFAE val) {
        this.id = id;
        this.val = val;
    }

    public String getId() {
        return id;
    }

    public RBMRCFAE getVal() {
        return val;
    }

    public String getASTCode() {
        return "(setvar '" + id + " " + val.getASTCode() + ")";
    }

}
