package ir.sajjadyosefi.android.xTubeless.classes.model.wallet;



import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Global;

import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User2;

import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;

import android.util.Log;

//import org.litepal.LitePal;
//import org.litepal.annotation.Column;
//import org.litepal.crud.LitePalSupport;


//public class Wallet extends LitePalSupport {
public class Wallet extends RealmObject {

//    @Column(nullable = false)
    private long Amount;
    private int userCode;

    public Wallet() {
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }



    public long getAmount() {
        return this.Amount;
    }

    public void setAmount(long amount) {
        this.Amount = amount;
    }


    public static boolean savedToDataBase(Wallet wallet) {
        try {
            deleteAllWallets();



            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(realmInstance -> {
                realmInstance.insert(wallet);
            }, () -> {
                // عملیات موفق
                int a = 5;a++;
            }, error -> {
                // عملیات خطا
                int a = 5;a++;

            });
            realm.close(); // بستن دیتابیس
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public static boolean updateWalletAmount(long newAmount) {
        try {
            Wallet wallet = new Wallet();
            wallet.setAmount(newAmount);
//            wallet.updateAll("UserCode = ?", Global.user2.getUserCodeAsString());
            int a = 0;
            a++;
            return true;
        }catch (Exception ex){
            return false;
        }
    }

//    public static boolean deleteAllWallets() {
//        try {
//            Realm realm = Realm.getDefaultInstance();
//            realm.executeTransaction(r -> {
//                RealmResults<Wallet> wallets = r.where(Wallet.class).findAll();
//                wallets.deleteAllFromRealm();
//            });
//            realm.close();
//            return true;
//        }catch (Exception ex) {
//            return false;
//        }
//    }
    public static boolean deleteAllWallets() {
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(r -> {
                RealmResults<Wallet> wallets = r.where(Wallet.class).findAll();
                if (wallets != null && !wallets.isEmpty()) {
                    wallets.deleteAllFromRealm();
                }
            }, () -> {
                // عملیات موفقیت‌آمیز انجام شد
                Log.d("Realm", "All wallets deleted successfully.");
            }, error -> {
                // مدیریت خطا
                Log.e("Realm", "Error deleting wallets: " + error.getMessage());
            });
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public AWallet loadWalletData( ) {
        return  null;
    }
    public Wallet loadWalletFromdb(int term) {
        try (Realm realm = Realm.getDefaultInstance()) {
            // جستجو در دیتابیس
            RealmResults<Wallet> walletsR = realm
                    .where(Wallet.class)
                    .equalTo("userCode", term)
                    .findAll();

            // کپی کردن داده‌ها از Realm به یک لیست جداگانه
            List<Wallet> wallets = realm.copyFromRealm(walletsR);

            // اگر دقیقا یک والت پیدا شد
            if (wallets.size() == 1) {
                Wallet aWallet = new Wallet();
                aWallet.setAmount(wallets.get(0).Amount);
                return aWallet;
            }

        } catch (Exception ex) {
            // ثبت یا مدیریت خطا
            Log.e("loadWalletFromdb", "Error loading wallet", ex);
        }

        return null;
    }


}
