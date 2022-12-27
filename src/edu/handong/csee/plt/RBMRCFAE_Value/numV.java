package edu.handong.csee.plt.RBMRCFAE_Value;

public class numV extends RBMRCFAE_Value {
    
        int num;
        
        public numV(int num) {
            this.num = num;
        }
        
        public int getNum() {
            return num;
        }
        
        public String getCode() {
            return "(numV " + num + ")";
        }

        public boolean equals(numV n) {
            return (num == n.getNum());
        }

}
