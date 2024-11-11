package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;

//import org.litepal.LitePal;

import com.google.gson.Gson;


import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.accountauthenticator.classes.ITransactionsListRequest;
import ir.sajjadyosefi.accountauthenticator.model.AUMIC;
import ir.sajjadyosefi.accountauthenticator.model.request.ADeviceRegisterRequest;
import ir.sajjadyosefi.android.xTubeless.Global;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model.IRegisterDeviceModel;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.ISplashScreenView;
import ir.sajjadyosefi.android.xTubeless.classes.model.Device;

import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User2;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.Validator;

import static ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity.PARAM_USER_OBJECT;
import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;
import static ir.sajjadyosefi.android.xTubeless.classes.model.user.User2.deleteAllUsersData;

//import static org.litepal.LitePalApplication.getContext;

public class SplashScreenPresenterCompl implements ISplashScreenPeresenter {

    private final Context context;
    ISplashScreenView splashScreen;
    IRegisterDeviceModel iRegisterDeviceModel;
    IUser iUser;

    public SplashScreenPresenterCompl(Context context , ISplashScreenView splashScreen) {
        this.splashScreen = splashScreen;
        this.context = context ;
        iRegisterDeviceModel = new Device(context);
        iUser = new User2();
    }


    @Override
    public void registerDevice() {
        splashScreen.showProgressBar();

        ADeviceRegisterRequest aDeviceRegisterRequest = new ADeviceRegisterRequest();
        Device device = new Device(context);

//        aDeviceRegisterRequest.setAndroidVersion(device.getAndroidVersion()); //todo fix in v2 api
        aDeviceRegisterRequest.setAndroidAPI(((Device) device).getAndroidAPI());
        aDeviceRegisterRequest.setBoard(device.getBOARD());
        aDeviceRegisterRequest.setBrand(device.getBRAND());
        aDeviceRegisterRequest.setBuildId(device.getBuildID());
        aDeviceRegisterRequest.setDisplay(device.getDISPLAY());
        aDeviceRegisterRequest.setManufacturer(device.getMANUFACTURER());
        aDeviceRegisterRequest.setModel(device.getMODEL());
        aDeviceRegisterRequest.setSerial(device.getSERIAL());

        iRegisterDeviceModel.tryToRegisterDevice(this,aDeviceRegisterRequest);

    }

    @Override
    public boolean isFirstRun() {
        return iRegisterDeviceModel.checkDeviceIsRegistered();
    }

    @Override
    public void onSuccessDeviceRegister() {
        iRegisterDeviceModel.setRegisterDeviceIsDone();

        if (sAccountHelper.hasUserAccount()){

            //sAccounts get user and pass.
//            int accountId = sAccounts.getUserAccountID();
//            String accountName = sAccounts.getUserAccountName();
//            String userName = sAccountHelper.getUserName();
//            String password = sAccountHelper.getUserPassword();
//
//            sAccountHelper.getAccountX();
//            sAccountHelper.getUserAccount();
//            sAccountHelper.getUserAccountID();
//            sAccountHelper.getUserAccountName();
//            sAccountHelper.getUserAccountPassword();
//            sAccountHelper.getUserName();
//            sAccountHelper.getUserPassword();


            iUser.loadUserDatax(this,null);
            if (Global.user2 == null){
                //load from server
                //save to litepal


                SignInActivity signInActivity = new SignInActivity();
                //find wcrtgbwue
                //در لاگین های بعدی هر دوتاش فراخوانی میشه
                //110015 وقتی در یک اپ لاگین هستیم و اپ جدید نصب میکنیم
                signInActivity.getUserDirect("11",new ITransactionsListRequest<Boolean, Intent>() {
                    @Override
                    public void onResponse(Boolean isSuccess,Intent data) {

                        if (data.hasExtra(PARAM_USER_OBJECT)){
                            Gson gson = new Gson();
                            User2 user = gson.fromJson(data.getExtras().getString(PARAM_USER_OBJECT)+"",User2.class);
                            Global.user2 = user;
                            AccountGeneral.setUserCode(Global.user2.getUserCode() + "");

                            if(User2.savedToDataBase(Global.user2)){
                                Wallet.savedToDataBase(Global.user2);
                            }
                        }
                    }
                });
            }else {
                String accountName = sAccountHelper.getUserAccountName();
                for (AUMIC mic : Global.aConfig.getUmic()) {
                    if (mic.getMobile().equals(accountName)){
                        Global.user2.setMobileNumberConfirmed("true");
                    }
                }
            }

//            LoginRequest loginRequest;
////            Validator validator = new Validator();
//            if ((new Validator()).isIranianMobileNumber(userName.replace("user:","")))
//                loginRequest = new LoginRequest(context,userName.replace("user:",""), password , DeviceUtil.GetAndroidId(context));
//            else if (validator.emailFieldCheck(userName.replace("user:","")))
//                loginRequest = new LoginRequest(context,userName.replace("user:",""));
//            else
//                loginRequest = new LoginRequest(getContext(),userName.replace("user:",""));
//            if (iUser.loadUserDatax(this , loginRequest) != null)
                splashScreen.onSplashScreenDone();


        }else {
            deleteAllUsersData();
            goToMainPage();
        }
    }

    @Override
    public void onSuccessUserDataLoad() {
        goToMainPage();
    }

    @Override
    public void onFailUserDataLoad() {
        if (Global.user2 == null){
            deleteAllUsersData();

            //load from server
            //save to litepal
            SignInActivity signInActivity = new SignInActivity();

            //find wcrtgbwue
            //در لاگین های بعدی هر دوتاش فراخوانی میشه
            //در لاگین دفعات بعدی اکانت رو از سرور میخونه
            signInActivity.getUserDirect(sAccountHelper.getUserAccountID() + "", new ITransactionsListRequest<Boolean, Intent>() {
                @Override
                public void onResponse(Boolean isSuccess, Intent data) {

                    if (data.hasExtra(PARAM_USER_OBJECT)) {
                        Gson gson = new Gson();
                        User2 user = gson.fromJson(data.getExtras().getString(PARAM_USER_OBJECT) + "", User2.class);
                        Global.user2 = user;
                        AccountGeneral.setUserCode(Global.user2.getUserCode() + "");

                        if (User2.savedToDataBase(Global.user2)) {
                            Wallet.savedToDataBase(Global.user2);
                            goToMainPage();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void goToMainPage() {
        splashScreen.hideProgressBar();
        splashScreen.onSplashScreenDone();
    }

    @Override
    public void onThrowException(Throwable t) {
        splashScreen.onThrowException(t);
    }

}
