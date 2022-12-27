package edu.handong.csee.plt.ast;

import edu.handong.csee.plt.RBMRCFAE_Value.*;

public class Mul extends RBMRCFAE {
    
    RBMRCFAE lhs;
	RBMRCFAE rhs;

    public Mul(RBMRCFAE lhs, RBMRCFAE rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public RBMRCFAE getLhs() {
        return lhs;
    }

    public RBMRCFAE getRhs() {
        return rhs;
    }

    public String getASTCode() {
        return "(mul " + lhs.getASTCode() + " " + rhs.getASTCode() + ")";
    }

    public numV calc(RBMRCFAE_Value l, RBMRCFAE_Value r) {
		return new numV(((numV) l).getNum() * ((numV) r).getNum());
	}

}
