package ir.sajjadyosefi.android.xTubeless.classes.model.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;


import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Global;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser_ThisFunctionMoveedToAaaLibrary;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI_ThisFunctionMoveedToAaaLibrary;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;

import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.utility.Validator;

import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;

import static android.content.Context.MODE_PRIVATE;
import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;
import static ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet.deleteAllWallets;

import java.util.ArrayList;
import java.util.List;


//public class User2 extends LitePalSupport implements IUser {
public class User2 extends RealmObject implements IUser_ThisFunctionMoveedToAaaLibrary {

	//private Context context;

//	@Column(nullable = false ,unique = true, defaultValue = "unknown")
	public int UserCode;

	private boolean IsDeleted;
	private boolean IsActive;
	private String MobileNumberConfirmed;
	private String TokenType;
	private String SimcardID;
	public int UserTypeCode;
	public String CreateDate;
	private Wallet wallet;

	public static boolean savedToDataBase(User2 user2) {
		try {
			List<User2> xxx = selectAllFromDb();
//			List<User2> user2s = LitePal.where("UserCode = ? ",Global.user2.getUserCodeAsString()).find(User2.class);
//			if (user2s.size() == 0) {
//				//noting
//			}else{
//				LitePal.deleteAll(Wallet.class, "UserCode = ?", Global.user2.getUserCodeAsString());
//				user2.delete();
//			}
//			user2.save();

			Realm realm = Realm.getDefaultInstance();
			realm.executeTransactionAsync(realmInstance -> {
				realmInstance.insert(user2);
			}, () -> {
				// عملیات موفق
			}, error -> {
				// عملیات خطا
			});
			realm.close(); // بستن دیتابیس

			return true;
		}catch (Exception ex) {
			return false;
		}
	}
	public boolean isDeleted() {
		return IsDeleted;
	}

	public void setDeleted(boolean deleted) {
		IsDeleted = deleted;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
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

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(RealmObject wallet) {
		this.wallet = (Wallet) wallet;
	}

	@Override
	public void CheckUserValidity(Context context , ILoginPresenterI_ThisFunctionMoveedToAaaLibrary presenter, LoginRequest request) {

	}

	public static boolean deleteAllUsersData() {
		try {
			Realm realm = Realm.getDefaultInstance();
			realm.executeTransaction(r -> {
				RealmResults<User2> wallets = r.where(User2.class).findAll();
				wallets.deleteAllFromRealm();
			});
			realm.close();
			deleteAllWallets();
			return true;
		}catch (Exception ex) {
			return false;
		}
	}

	//1
	@Override
	public IUser_ThisFunctionMoveedToAaaLibrary loadUserDatax(ISplashScreenPeresenter presenter, LoginRequest request) {
		try {
			//List<User2> user2s = LitePal.where("UserCode = ? ", sAccountHelper.getUserAccountID() + "").find(User2.class);
			List<User2> user2s = selectAllFromDb();

			//user2 = gson.fromJson(user2s.get(0), User2.class);
			//user2 = gson.fromJson(user2s.get(0), User2.class);

			if (user2s.size() == 1) {
				Global.user2 = user2s.get(0);

				Wallet wallet = new Wallet();
				Global.user2.setWallet(wallet.loadWalletFromdb(Global.user2.getUserCode()));
				AccountGeneral.setUserCode(Global.user2.getUserCodeAsString());

				presenter.onSuccessUserDataLoad();
				return Global.user2;
			}else {
				presenter.onFailUserDataLoad();
				return null;
			}
		}catch (Exception ex){
			presenter.onFailUserDataLoad();
			return null;
		}
	}


	public static User2 loadTubelessAccountData(Context context) {

		if (sAccountHelper.hasUserAccount()){
			int accountId = sAccountHelper.getUserAccountID();
//            Global.user = LitePal.where("userId like ?", accountId + "").findFirst(User.class);
//			Global.user2 = LoadLogedInUser(mContext);

			if (Global.user2 == null){
				String accountName = sAccountHelper.getUserAccountName();
				LoginRequest loginRequest = null;
				IUser_ThisFunctionMoveedToAaaLibrary iUser;
				iUser = new Userx();
				Validator validator = new Validator();
				if (validator.isIranianMobileNumber(accountName)){
					loginRequest = new LoginRequest(context,accountName, sAccountHelper.getUserAccountPassword(), DeviceUtil.GetAndroidId(context));
				}else if (validator.isIranianMobileNumber(accountName)) {
					loginRequest = new LoginRequest(context,accountName, "");
				}else {
					loginRequest = new LoginRequest(context,accountName);
				}
				iUser.CheckUserValidity(context ,null, loginRequest);

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





	//	public User2 insertToDb(User2 user2){
//		Realm realm = Realm.getDefaultInstance();
//		realm.executeTransactionAsync(realmInstance -> {
//			realmInstance.insert(user2);
//		}, () -> {
//			 عملیات موفق
//		}, error -> {
//			 عملیات خطا
//		});
//		realm.close(); // بستن دیتابیس
//	}
	public static List<User2> selectAllFromDb() {
		Realm realm = Realm.getDefaultInstance();
		RealmResults<User2> persons = realm.where(User2.class).findAll();
		List<User2> usersTmp = realm.copyFromRealm(persons);
		realm.close();
		return usersTmp;
	}
	public static User2 selectFromDb(User2 user2) {
		Realm realm = Realm.getDefaultInstance();
		RealmResults<User2> persons = realm.where(User2.class)
				.equalTo("UserCode", user2.getUserCode()) // اضافه کردن شرط age = 5
				.findAll();

		for (User2 person : persons) {
			Log.d("Realm", "UserCode: " + person.getUserCode());
		}

		realm.close(); // بستن دیتابیس
		if (persons.size() == 1)
			return persons.get(0);
		else
			return null;
	}

//	public User2 updateFromDb(){
//		Realm realm = Realm.getDefaultInstance();
//		realm.executeTransaction(r -> {
//			Person person = r.where(Person.class).equalTo("name", "John Doe").findFirst();
//			if (person != null) {
//				person.setAge(35);
//			}
//		});
//		realm.close(); // بستن دیتابیس
//	}
//	public User2 deleteFromDb(){
//		Realm realm = Realm.getDefaultInstance();
//		realm.executeTransaction(r -> {
//			RealmResults<Person> persons = r.where(Person.class).findAll();
//			persons.deleteAllFromRealm();
//		});
//		realm.close(); // بستن دیتابیس
//	}
}