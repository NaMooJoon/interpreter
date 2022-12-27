package edu.handong.csee.plt.DefrdSub;

public class DefrdSub {

    public String getDefrdSubCode() {

        if (this instanceof aSub) {
            return ((aSub) this).getDefrdSubCode();
        } 
        else if (this instanceof mtSub) {
            return ((mtSub) this).getDefrdSubCode();
        } 
        else {
            return "Error: Unknown DefrdSub";
        }

    }

}
