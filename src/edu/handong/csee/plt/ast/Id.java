package edu.handong.csee.plt.ast;

public class Id extends RBMRCFAE {

    String id;
    
    public Id(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public String getASTCode() {
        return "(id '" + id + ")";
    }

}
