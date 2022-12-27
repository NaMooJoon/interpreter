package edu.handong.csee.plt.RBMRCFAE_Value;

public class RBMRCFAE_Value {

    public String getCode() {

        String astCode = "";
        if (this instanceof numV)
            astCode = ((numV)this).getCode();
        else if (this instanceof closureV)
            astCode = ((closureV)this).getCode();
        else if (this instanceof exprV)
            astCode = ((exprV)this).getCode();
        return astCode;

    }
}
