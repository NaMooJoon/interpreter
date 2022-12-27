package edu.handong.csee.plt.ast;

public class App extends RBMRCFAE {

    RBMRCFAE funName;
    RBMRCFAE argument;

    public App(RBMRCFAE funName, RBMRCFAE argument) {
        this.funName = funName;
        this.argument = argument;
    }

    public RBMRCFAE getFunName() {
        return funName;
    }

    public RBMRCFAE getArgument() {
        return argument;
    }

    public String getASTCode() {
        return "(app " + funName.getASTCode() + " " + argument.getASTCode() + ")";
    }
    
}
