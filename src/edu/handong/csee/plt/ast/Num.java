package edu.handong.csee.plt.ast;

import edu.handong.csee.plt.RBMRCFAE_Value.numV;

public class Num extends RBMRCFAE {
	int num;
	
	public Num(int num){
		this.num = num;
	}

	public numV getNum() {
		return new numV(num);
	}
	
	public String getStrNum() {
		return "" + num;
	}
	
	public String getASTCode() {
		return "(num " + num +")";
	}
}
