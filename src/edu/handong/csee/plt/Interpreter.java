package edu.handong.csee.plt;

import edu.handong.csee.plt.DefrdSub.*;
import edu.handong.csee.plt.RBMRCFAE_Value.*;
import edu.handong.csee.plt.Store.*;
import edu.handong.csee.plt.ValueStore.VS;
import edu.handong.csee.plt.ast.*;
import edu.handong.csee.plt.ast.App;
import edu.handong.csee.plt.lambda.Lambda;

public class Interpreter {

	public boolean withLaziness = false;

	public Interpreter(boolean withLaziness) {
		this.withLaziness = withLaziness;
	}

	public VS interp(RBMRCFAE ast, DefrdSub ds, Store sto) {
		
		if (ast instanceof Num) {
			Num num = (Num) ast;

			return new VS(num.getNum(), sto);
		}
		
		if (ast instanceof Add) {
			Add add = (Add) ast;
			VS lvs = strict(interp(add.getLhs(), ds, sto));
			VS rvs = strict(interp(add.getRhs(), ds, sto));

			return new VS(add.calc(lvs.getValue(), rvs.getValue()), sto);
		}

		if (ast instanceof Sub) {
			Sub sub = (Sub) ast;
			VS lvs = strict(interp(sub.getLhs(), ds, sto));
			VS rvs = strict(interp(sub.getRhs(), ds, sto));

			return new VS(sub.calc(lvs.getValue(), rvs.getValue()), sto);
		}

		if (ast instanceof Mul) {
			Mul mul = (Mul) ast;
			VS lvs = strict(interp(mul.getLhs(), ds, sto));
			VS rvs = strict(interp(mul.getRhs(), ds, sto));

			return new VS(mul.calc(lvs.getValue(), rvs.getValue()), sto);
		}

		if (ast instanceof Id) {
			Id id = (Id) ast;
			int address = lookup(id.getId(), ds);

			return new VS(lookupStore(address, sto).getValue(), sto);
		}

		if (ast instanceof Fun) {
			Fun fun = (Fun) ast;
			closureV clos = new closureV(fun.getId(), fun.getExp(), ds);
			
			return new VS(clos, sto);
		}

		if (ast instanceof Refun) {
			Refun rfun = (Refun) ast;
			refclosV clos = new refclosV(rfun.getId(), rfun.getExpr(), ds);

			return new VS(clos, sto);
		}

		if (ast instanceof App) {
			App app = (App) ast;

			Exception e = new Exception("trying to apply a number");
			VS fun = strict(interp(app.getFunName(), ds, sto));
			
			RBMRCFAE_Value funExpr = fun.getValue();
			
			if (funExpr instanceof refclosV) {
				refclosV refclos = (refclosV) funExpr;
				
				Id arg = (Id) app.getArgument();
				int address = lookup(arg.getId(), ds);
				aSub newDs = new aSub(refclos.getSymbol(), address, refclos.getDefrdSub());

				return interp(refclos.getBody(), newDs, fun.getStore());
			}

			if (funExpr instanceof closureV) {
				closureV clos = (closureV) funExpr;

				if (withLaziness) { /* with laziness */
					int address = sto.malloc();
					exprV argVal = new exprV(app.getArgument(), ds, sto, null);
					Store newSto = new aSto(address, argVal, sto);
					aSub newDs = new aSub(clos.getSymbol(), address, clos.getDefrdSub());
					
					return interp(clos.getBody(), newDs, newSto);
			    } else { /* without laziness */

					VS arg = interp(app.getArgument(), ds, fun.getStore());
					
					int address = arg.getStore().malloc();
					aSub newDs = new aSub(clos.getSymbol(), address, clos.getDefrdSub());
					Store newSto = new aSto(address, arg.getValue(), arg.getStore());


					return interp(clos.getBody(), newDs, newSto);
				}
		
			}

			System.out.println("error app: " + e.getMessage());
			e.printStackTrace();

			return null;
		}

		if (ast instanceof Rec) {

			Rec rec = (Rec) ast;
			RBMRCFAE_Value value = new numV(21800179);

			int address = sto.malloc();
			aRecSub newDs = new aRecSub(rec.getRfunName(), address, ds);
			aSto newSto = new aSto(address, value, sto);

			closureV newV = (closureV) interp(rec.getRfunExpr(), newDs, sto).getValue();
			newSto.setValue(newV);

			return interp(rec.getBody(), newDs, newSto);
		}

		if (ast instanceof if0) {

			if0 statement = (if0) ast;
			numV cond = (numV) strict(interp(statement.getCond(), ds, sto)).getValue();
			numV zero = new numV(0);

			if (cond.equals(zero))
				return interp(statement.getThenBody(), ds, sto);
			else
				return interp(statement.getElseBody(), ds, sto);
		}

		if (ast instanceof Seqn) {
			Seqn seqn = (Seqn) ast;

			Lambda handle = (v1, v2, sto1) -> {
				return new VS(v2, sto1);
			};
			return interpTwo(seqn.getFirst(), seqn.getSecond(), ds, sto, handle);
		}

		if (ast instanceof Setvar) {
			Setvar setvar = (Setvar) ast;

			int address = lookup(setvar.getId(), ds);
			VS expr = interp(setvar.getVal(), ds, sto);
			aSto newSto = new aSto(address, expr.getValue(), expr.getStore());

			return new VS(expr.getValue(), newSto);
		}

		if (ast instanceof Newbox) {
			Newbox newbox = (Newbox) ast;
			
			VS expr = interp(newbox.getExpr(), ds, sto);
			int address = expr.getStore().malloc();
			aSto newSto = new aSto(address, expr.getValue(), expr.getStore());


			return new VS(new boxV(address), newSto);
		}

		if (ast instanceof Setbox) {
			Setbox setbox = (Setbox) ast;


			Lambda handle = (boxVal, val, sto1) -> {
				boxV box = (boxV) boxVal;
				int address = box.getAddress();
				aSto newSto = new aSto(address, val, sto1);

				return new VS(val, newSto);
			};

			return interpTwo(setbox.getId(), setbox.getExpr(), ds, sto, handle);
		}

		if (ast instanceof Openbox) {
			Openbox openbox = (Openbox) ast;

			VS vs = strict(interp(openbox.getId(), ds, sto));

			System.out.println("openbox: " + vs.getValue().getCode());
			if (vs.getValue() instanceof numV) {
				return vs;
			}
			if (vs.getValue() instanceof boxV) {
				boxV box = (boxV) vs.getValue();
				int address = box.getAddress();

				return lookupStore(address, vs.getStore());
			}

			return null;
		}

		return null;
	}

	public VS interpTwo(RBMRCFAE expr1, RBMRCFAE expr2, DefrdSub ds, Store sto, Lambda handle) {
		VS vs1 = strict(interp(expr1, ds, sto));
		VS vs2 = strict(interp(expr2, ds, vs1.getStore()));

		return handle.run(vs1.getValue(), vs2.getValue(), vs2.getStore());
	}

	public VS strict(VS val) {
		RBMRCFAE_Value value  = val.getValue();

		if (value instanceof exprV) {
			exprV expr = (exprV) value;

			if (expr.getVal() == null) {
				return strict(interp(expr.getExpr(), expr.getDs(), expr.getSto()));
			}
			return expr.getVal();
		}

		return val;
	}

	public int lookup(String name, DefrdSub ds) {

        Exception e = new Exception("no binding for identifier");

        if (ds instanceof mtSub) {

            System.out.println("error lookup: " + e.getMessage());
            e.printStackTrace();

            System.exit(1);

        }

        if (ds instanceof aSub) {
			aSub sub = (aSub) ds;

			if (name.equals(sub.getSymbol())) {

				return sub.getAddress();
			}
			return lookup(name, ((aSub) ds).getRest());
        }

        if (ds instanceof aRecSub) {
			aRecSub recSub = (aRecSub) ds;

            if (name.equals(recSub.getSymbol())) {
				
                return recSub.getAddress();
            }

            return lookup(name, ((aRecSub) ds).getRest());
        }
		System.out.println("error lookup: " + e.getMessage());
		e.printStackTrace();

        return -1;
    }

	public VS lookupStore(int address, Store store) {

		Exception e = new Exception("no binding for address");

		if (store instanceof mtSto) {
			System.out.println("error lookupStore: " + e.getMessage());
			e.printStackTrace();

			System.exit(1);

		}

		if (store instanceof aSto) {
			aSto st = (aSto) store;

			if (address == st.getAddress()) {
				if (st.getValue() instanceof exprV) {
					VS val = strict(new VS(st.getValue(), store));
					return val;
				}
				return new VS(st.getValue(), store);
			}

			return lookupStore(address, ((aSto) store).getRest());
		}

		return null;
	}

	

}
