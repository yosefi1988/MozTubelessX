package ir.sajjadyosefi.android.xTubeless.classes.model.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
//import org.litepal.crud.LitePalSupport;

import java.util.List;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Global;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;

import ir.sajjadyosefi.android.xTubeless.classes.Validator;

import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;

import static android.content.Context.MODE_PRIVATE;
import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;
import static ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet.deleteAllWallets;

public class User2 extends LitePalSupport implements IUser {

	private Context context;

	@Column(nullable = false ,unique = true, defaultValue = "unknown")
	public int UserCode;

	private boolean IsDeleted;
	private boolean IsActive;
	private String MobileNumberConfirmed;
	private String TokenType;
	private String SimcardID;
	public int UserTypeCode;
	public String CreateDate;
	private AWallet wallet;

	public static boolean savedToDataBase(User2 user2) {
		try {
			List<User2> user2s = LitePal.where("UserCode = ? ",Global.user2.getUserCodeAsString()).find(User2.class);
			if (user2s.size() == 0) {
				//noting
			}else{
				LitePal.deleteAll(Wallet.class, "UserCode = ?", Global.user2.getUserCodeAsString());
				user2.delete();
			}
			user2.save();
			return true;
		}catch (Exception ex) {

			List<User2> user2s = LitePal.where("UserCode = ? ",Global.user2.getUserCodeAsString()).find(User2.class);

			return false;
		}
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public boolean isDeleted() {
		return IsDeleted;
	}

	public void setDeleted(boolean deleted) {
		IsDeleted = deleted;
	}

	public boolean isActive() {
		return IsActive;
	}

	public void setActive(boolean active) {
		IsActive = active;
	}

	public String getMobileNumberConfirmed() {
		return MobileNumberConfirmed;
	}

	public void setMobileNumberConfirmed(String mobileNumberConfirmed) {
		MobileNumberConfirmed = mobileNumberConfirmed;
	}

	public String getTokenType() {
		return TokenType;
	}

	public void setTokenType(String tokenType) {
		TokenType = tokenType;
	}

	public String getSimcardID() {
		return SimcardID;
	}

	public void setSimcardID(String simcardID) {
		SimcardID = simcardID;
	}

	public int getUserCode() {
		return UserCode;
	}

	public String getUserCodeAsString() {
		return UserCode + "";
	}

	public void setUserCode(int userCode) {
		UserCode = userCode;
	}

	public int getUserTypeCode() {
		return UserTypeCode;
	}
	public boolean isUserAdmin() {
		if (UserTypeCode == StaticValue.USER_TYPE_ADMIN)
			return true;
		else
			return false;
	}
	public boolean isUserCreator() {
		if (UserTypeCode == StaticValue.USER_TYPE_CREATOR)
			return true;
		else
			return false;
	}

	public void setUserTypeCode(int userTypeCode) {
		UserTypeCode = userTypeCode;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public AWallet getWallet() {
		return wallet;
	}

	public void setWallet(AWallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public void CheckUserValidity(ILoginPresenterI presenter, LoginRequest request) {

	}

	public static boolean deleteAllUsersData() {
		try {
//			int aaa = LitePal.deleteAll(User.class, "userId = ?", String.valueOf(Global.IDUser));
//			int bbbb = LitePal.deleteAll(User.class);
//			List<User> dbUser = LitePal.where("userId like ?", String.valueOf(Global.IDUser) + "").find(User.class);
//			List<User> dbUser2 = LitePal.findAll(User.class);
			deleteAllWallets();
			int bbbb = LitePal.deleteAll(User2.class);
			return true;
		}catch (Exception ex) {
			return false;
		}
	}

	//1
	@Override
	public IUser loadUserDatax(ISplashScreenPeresenter presenter, LoginRequest request) {
		try {
			List<User2> user2s = LitePal.where("UserCode = ? ", sAccountHelper.getUserAccountID() + "").find(User2.class);
			//user2 = gson.fromJson(user2s.get(0).get, User2.class);
			Global.user2 = user2s.get(0);
			Wallet wallet = new Wallet();
			Global.user2.setWallet(wallet.loadWalletData());
			AccountGeneral.setUserCode(Global.user2.getUserCodeAsString());

			presenter.onSuccessUserDataLoad();
			//return Global.user2;
		}catch (Exception ex){
			presenter.onFailUserDataLoad();
			//return null;
		}
		return null;
	}


	public static User2 loadTubelessAccountData(Context context) {

		if (sAccountHelper.hasUserAccount()){
			int accountId = sAccountHelper.getUserAccountID();
//            Global.user = LitePal.where("userId like ?", accountId + "").findFirst(User.class);
//			Global.user2 = LoadLogedInUser(mContext);

			if (Global.user2 == null){
				String accountName = sAccountHelper.getUserAccountName();
				LoginRequest loginRequest = null;
				IUser iUser;
				iUser = new User(context);
				Validator validator = new Validator();
				if (validator.isIranianMobileNumber(accountName)){
					loginRequest = new LoginRequest(context,accountName, sAccountHelper.getUserAccountPassword(), DeviceUtil.GetAndroidId(context));
				}else if (validator.isIranianMobileNumber(accountName)) {
					loginRequest = new LoginRequest(context,accountName, "");
				}else {
					loginRequest = new LoginRequest(context,accountName);
				}
				iUser.CheckUserValidity(null, loginRequest);

			}
			return Global.user2;
		}else {
			return null;
		}
	}
	public static final User2 LoadLogedInUser(Context context){
		SharedPreferences prefs = null;
		prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
		if(prefs.getString("LogedInUser","").length()>10) {
			Gson gson = new Gson();
			String stringOfUser = prefs.getString("LogedInUser", "");
			return gson.fromJson(stringOfUser, User2.class);
		}else {
			return null;
		}
	}
}