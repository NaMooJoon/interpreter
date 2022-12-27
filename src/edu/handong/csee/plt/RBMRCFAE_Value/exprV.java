package edu.handong.csee.plt.RBMRCFAE_Value;

import edu.handong.csee.plt.DefrdSub.DefrdSub;
import edu.handong.csee.plt.Store.Store;
import edu.handong.csee.plt.ValueStore.VS;
import edu.handong.csee.plt.ast.RBMRCFAE;

public class exprV extends RBMRCFAE_Value {
        
        RBMRCFAE expr;
        DefrdSub ds;
        Store sto;
        VS val;

        public exprV(RBMRCFAE expr, DefrdSub ds, Store sto, VS val) {
                this.expr = expr;
                this.ds = ds;
                this.sto = sto;
                this.val = val;
        }

        public RBMRCFAE getExpr() {
                return expr;
        }

        public void setExpr(RBMRCFAE expr) {
                this.expr = expr;
        }

        public DefrdSub getDs() {
                return ds;
        }

        public void setDs(DefrdSub ds) {
                this.ds = ds;
        }

        public Store getSto() {
                return sto;
        }

        public void setSto(Store sto) {
                this.sto = sto;
        }

        public VS getVal() {
                return val;
        }

        public void setVal(VS val) {
                this.val = val;
        }

        public String getCode() {
                if (val == null)
                        return "(exprV " + expr.getASTCode() + " " + ds.getDefrdSubCode() + " " + sto.getStoreCode() + " <null>)";

                return "(exprV " + expr.getASTCode() + " " + ds.getDefrdSubCode() + " " + sto.getStoreCode() + " " + val.getVS() + ")";
        }

}
