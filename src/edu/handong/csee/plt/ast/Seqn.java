package edu.handong.csee.plt.ast;

public class Seqn extends RBMRCFAE {
        
            RBMRCFAE first;
            RBMRCFAE second;
        
            public Seqn(RBMRCFAE first, RBMRCFAE second) {
                this.first = first;
                this.second = second;
            }
        
            public RBMRCFAE getFirst() {
                return first;
            }
        
            public RBMRCFAE getSecond() {
                return second;
            }
        

            public String getASTCode() {
                return "(seqn " + first.getASTCode() + " " + second.getASTCode() + ")";
            }
}
