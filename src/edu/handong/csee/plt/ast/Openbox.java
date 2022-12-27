package edu.handong.csee.plt.ast;

public class Openbox extends RBMRCFAE {
    
        RBMRCFAE id;
    
        public Openbox(RBMRCFAE id) {
            this.id = id;
        }
    
        public RBMRCFAE getId() {
            return id;
        }
    
        public void setId(RBMRCFAE id) {
            this.id = id;
        }
    
        public String getASTCode() {
            return "(openbox " + id.getASTCode() + ")";
        }
        
}
