package ir.sajjadyosefi.android.xTubeless.classes.model.wallet;


import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Global;

import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User2;

import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;

public class Wallet extends LitePalSupport {

    @Column(nullable = false)
    private long Amount;
    private int userCode;
    private User2 user;


    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }


    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }

    public Wallet() {
    }

    public long getAmount() {
        return this.Amount;
    }

    public void setAmount(long amount) {
        this.Amount = amount;
    }


    public static boolean savedToDataBase(User2 user) {
        try {
            deleteAllWallets();
            Wallet wallet = new Wallet();
            wallet.setAmount(user.getWallet().getAmount());
            wallet.setUser(user);
            wallet.setUserCode(user.getUserCode());

            wallet.save();
            return true;
        }catch (Exception ex) {

            //List<User2> user2s = LitePal.where("UserCode = ? ", Global.user2.getUserCodeAsString()).find(User2.class);

            return false;
        }
    }

    public static boolean updateWalletAmount(long newAmount) {
        try {
            Wallet wallet = new Wallet();
            wallet.setAmount(newAmount);
            wallet.updateAll("UserCode = ?", Global.user2.getUserCodeAsString());
            int a = 0;
            a++;
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public static boolean deleteAllWallets() {
        try {
            int bbbb = LitePal.deleteAll(Wallet.class);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }


    public AWallet loadWalletData() {
        try {
            List<Wallet> wallets = LitePal.where("UserCode = ? ", Global.user2.getUserCodeAsString()).find(Wallet.class);


            if (wallets.size() == 1) {
                AWallet aWallet = new AWallet();
                aWallet.setAmount(wallets.get(0).Amount);

                return aWallet;
            }
            int a = 0;
            a++;
        }catch (Exception ex){

        }
        return null;
    }

}
