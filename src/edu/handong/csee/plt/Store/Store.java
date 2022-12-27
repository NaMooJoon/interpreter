package edu.handong.csee.plt.Store;


public class Store {

    public String getStoreCode() {

        if (this instanceof aSto) {
            return ((aSto) this).getStoreCode();
        }
        else if (this instanceof mtSto) {
            return ((mtSto) this).getStoreCode();
        }
        else {
            return "Error: Unknown Store";
        }
    }

    public int malloc() {

        return 1 + maxAddress();
	}

    private int maxAddress() {

        if (this instanceof mtSto) {
            return 0;
        }

        if (this instanceof aSto) {
            aSto st = (aSto) this;
            return max(st.getAddress(), st.getRest().maxAddress());
        }
        return -1;
    }

    private int max(int a, int  b) {
        return (a > b)? a : b;
    }
    
}
