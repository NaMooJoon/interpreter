package edu.handong.csee.plt;

import edu.handong.csee.plt.DefrdSub.mtSub;
import edu.handong.csee.plt.Store.mtSto;
import edu.handong.csee.plt.ValueStore.VS;
import edu.handong.csee.plt.ast.RBMRCFAE;

public class App {
    private boolean onlyParser;
    private boolean withLaziness;
    private String code;

    public App(boolean onlyParser, boolean withLaziness, String code) {
        this.onlyParser = onlyParser;
        this.withLaziness = withLaziness;
        this.code = code;
    }

    public void start() {

        System.out.println("App running start!!");

        // Parser
        Parser parser = new Parser();
        RBMRCFAE ast = parser.parse(code);

        if (ast == null)
            System.out.println("Syntax Error!");

        // onlyParser is true case.
        if (onlyParser) {
            System.out.println(ast.getASTCode());
            return;
        }

        // interpreter
        Interpreter interpreter = new Interpreter(withLaziness);

        VS result = interpreter.interp(ast, new mtSub(), new mtSto());

        System.out.println(result.getVS());
    }
}
