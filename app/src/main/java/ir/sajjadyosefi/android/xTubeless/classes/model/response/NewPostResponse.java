package ir.sajjadyosefi.android.xTubeless.classes.model.response;


import java.util.List;

import ir.sajjadyosefi.accountauthenticator.model.ATransaction;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;

/**
 * Created by sajjad on 1/20/2018.
 */


public class NewPostResponse extends ServerResponseBase {
    public AWallet wallet;

    public List<ATransaction> transactionList;

    public AWallet getWallet() {
        return wallet;
    }

    public void setWallet(AWallet wallet) {
        this.wallet = wallet;
    }

    public List<ATransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<ATransaction> transactionList) {
        this.transactionList = transactionList;
    }
}
