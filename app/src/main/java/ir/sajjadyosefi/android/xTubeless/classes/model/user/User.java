package ir.sajjadyosefi.android.xTubeless.classes.model.user;

import android.accounts.Account;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;

//import org.litepal.LitePal;
//import org.litepal.annotation.Column;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Type;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;

import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.LoginResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_RESPONSE_BODY_IS_NULL;
import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.showConnectionLostDialog;

public class User extends LitePalSupport implements IUser {

	private Context context;
	//_____________ ok ________________
	@Column(unique = true, defaultValue = "unknown")
	private long userId;
	private String AvatarUrl;
	private String Name;
	private String Family;
	private String Balance;
	private int UserTypeCode;
	private String Email;
	private String SimCardId;
	private String PhoneNumber;
	public String Ui;
	public String Username;

	private String profileImage;
	private String Password;
	private String loginPhone = null;
	private String loginPassword = null;
	private boolean isAdmin = false;

	//_________________________________

//	@Column(ignore = true)
//	private String city_location = null;
//
//	@Column(ignore = true)
//	private List<Post> PostItems = null ;
//
//	@Column(ignore = true)
//	private List<Car> carList = null ;
//
//	@Column(ignore = true)
//	private String pushToken ;
//
//	@Column(ignore = true)
//	private String _cookie = "";


//	private Device device = null;

//	private int	ApplicationID;
//


//	private Boolean canSendPicture;

	public User(Context context) {
		this.context = context;
	}

	public User(User source, LoginRequest req) {
		if (source.getUserId() == 0)
			setUserId(1);
		else
			setUserId(source.getUserId());

		setUi(source.getUserName());
		setName(source.getName());
		setUserTypeCode(source.getUserTypeCode());
		setBalanse(source.getBalanse());
		setSimCardId(source.getSimCardId());
		setFamily(source.getFamily());
		setEmail(source.getEmail());
		setMobileNumber(source.getMobileNumber());
		setUserImage(source.getUserImage());
		setProfileImage(source.getProfileImage());

		if (req.getSimcard() != null && req.getSimcard().length() != 0) {
			setUsername(req.getSimcard());
		}
		if (req.getMobileNumber() != null && req.getMobileNumber().length() != 0) {
			setUsername(req.getMobileNumber());
		}
		if (req.getEmail() != null && req.getEmail().length() != 0) {
			setUsername(req.getEmail());
		}

		setBalanse(source.getBalanse());
		setPassword(source.getPassword());
		setLoginPassword(getLoginPassword());
		setLoginPhone(source.getLoginPhone());
		setAdmin(source.isAdmin());
	}


	public long getUserId() {
		return userId;
	}
	public int getUserIdAsInt() {
		return (int)userId;
	}
	public String getUserIdAsString() {
		return "" + userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return Ui;
	}

	public void setUi(String userName) {
		this.Ui = userName;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}



	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getMobileNumber() {
		return PhoneNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.PhoneNumber = mobileNumber;
	}

	public String getUserImage() {
		return AvatarUrl;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFamily() {
		return Family;
	}

	public void setFamily(String family) {
		Family = family;
	}

	public int getUserTypeCode() {
		return UserTypeCode;
	}

	public void setUserTypeCode(int userTypeCode) {
		UserTypeCode = userTypeCode;
	}

	public String getSimCardId() {
		return SimCardId;
	}

	public void setSimCardId(String simCardId) {
		SimCardId = simCardId;
	}

	public void setUserImage(String userImage) {
		this.AvatarUrl = userImage;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getBalanse() {
		return Balance;
	}

	public void setBalanse(String balanse) {
		this.Balance = balanse;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void CheckUserValidity(ILoginPresenterI presenter , LoginRequest request) {
		Callback callback = new Callback() {
			@Override
			public void onResponse(Call call, Response response) {
//				t_afterGetResponse();

				Gson gson = new Gson();
				JsonElement jsonElement = gson.toJsonTree(response.body());
				ServerResponseBase responseX = null;

				try {
					if (response.body() == null){
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}

					responseX = gson.fromJson(jsonElement.getAsString(), ServerResponseBase.class);
					if (response.body() != null ) {
						if (responseX.getTubelessException().getCode() != 0) {
							if (responseX.getTubelessException().getCode() > 0) {
								if (call != null && response != null) {
									Object object = gson.fromJson(jsonElement.getAsString(), (Type) User.class);
									User tmpUser = Primitives.wrap(User.class).cast(object);
									save(tmpUser,null);
								}
							} else {
								if (presenter != null)
									presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
							}
						}else {
							presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
						}
					}else {
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}
				}catch (Exception sException) {
					if (presenter != null)
						presenter.onThrowException(sException);
				}
			}

			@Override
			public void onFailure(Call call, Throwable t) {
//				presenter.onThrowException(t);


				try {
					showConnectionLostDialog(context, null , new Runnable() {
						@Override
						public void run() {
							retry(call);
						}
					});
				}catch (Exception ex){
					int aX =0 ;
					aX  ++;

				}

			}

			private void retry(Call call) {
				call.clone().enqueue(this);
			}
		};
		Global.apiManagerTubeless.loginOrRregisterMVP(request, callback);
	}

	@Override
	public IUser loadUserDatax(ISplashScreenPeresenter presenter, LoginRequest request) {
		return null;
	}

	public static boolean save(User tmpUser, LoginRequest req) {
//		tmpUser.setAdmin(CheckUserIsAdmin(tmpUser));
		tmpUser.setUserId(1);

		//save to db
//		if (Global.user == null){
//			if ((new User(tmpUser,req)).save()){
//			if (Global.SaveLogedInUser(getContext(),new User(tmpUser,req))){
//				Global.user = tmpUser;


//				if ((new Validator()).isIranianMobileNumber(tmpUser.getMobileNumber()))
//					tmpUser.setPassword(tmpUser.getPassword());

				return true;
//			}else {
//				User dbUser = LitePal.where("userId like ?", tmpUser.getUserId() + "").findFirst(User.class);
//				User dbUser = Global.LoadLogedInUser(getContext());

//				List<User> gggt = LitePal.findAll(User.class);
//				if (dbUser != null) {
//					Global.user = dbUser;

//					if ((new Validator()).isIranianMobileNumber(tmpUser.getMobileNumber()))
//						tmpUser.setPassword(tmpUser.getPassword());

//					return true;
//				}else {
//					Global.user = null;
//					return false;
//				}
//			}
//		}else {
//			Global.user = tmpUser;

//			if ((new Validator()).isIranianMobileNumber(tmpUser.getMobileNumber()))
//				tmpUser.setPassword(tmpUser.getPassword());
//			return false;
//		}
	}

	public void CheckUserValidity2(ISplashScreenPeresenter presenter , LoginRequest request) {
		Callback callback = new Callback() {
			@Override
			public void onResponse(Call call, Response response) {
//				t_afterGetResponse();

				Gson gson = new Gson();
				JsonElement jsonElement = gson.toJsonTree(response.body());
				ServerResponseBase responseX = null;

				try {
					if (response.body() == null){
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}

					try {
						responseX = gson.fromJson(jsonElement.getAsString(), ServerResponseBase.class);
					}catch (Exception ex){
						responseX = gson.fromJson(jsonElement.toString(), ServerResponseBase.class);
					}
					if (response.body() != null ) {
						if (responseX.getTubelessException().getCode() == 100) {
							if (call != null && response != null) {
								Object object;
								try {
									object = gson.fromJson(jsonElement.getAsString(), (Type) LoginResponse.class);
								}catch (Exception ex){
									object = gson.fromJson(jsonElement.toString(), (Type) LoginResponse.class);
								}


								LoginResponse tmp = Primitives.wrap(LoginResponse.class).cast(object);
								User tmpUser = tmp.getResponse();
								tmpUser.setAdmin(CheckUserIsAdmin(tmpUser));

								//save to db
//								if (Global.user == null){
//									if ((new User(tmpUser,request)).save()){
									if (Global.SaveLogedInUser(context,new User(tmpUser,request))){
//										Global.user = tmpUser;


//											if ((new Validator()).isIranianMobileNumber(request.getUserName()))
//												tmpUser.setPassword(request.getPassword());
//
//											presenter.goToMainPage();
//									}else if (update(tmpUser)){
//										Global.user = tmpUser;

									}else {
//											User dbUser = LitePal.where("userId like ?", tmpUser.getUserId() + "").findFirst(User.class);
//										List<User> xxxxx = LitePal.findAll(User.class);
//										User dbUser = LitePal.where("Username like ?", tmpUser.getUserName() + "").findFirst(User.class);
//										User dbUser = LitePal.where("Username like ?", tmpUser.getSimCardId() + "").findFirst(User.class);
//										User dbUser = Global.LoadLogedInUser(getContext());

//										if (dbUser != null) {
//											Global.user = dbUser;

//												if ((new Validator()).isIranianMobileNumber(request.getUserName()))
//													tmpUser.setPassword(request.getPassword());
//												presenter.goToMainPage();
//										}else {
//											Global.user = null;
//										}
									}
//								}else {
//									Global.user = tmpUser;

//										if ((new Validator()).isIranianMobileNumber(request.getUserName()))
//											tmpUser.setPassword(request.getPassword());
//										presenter.goToMainPage();
//								}
							}
						}else {
							presenter.onThrowException(new TubelessException(responseX.getTubelessException().getCode()));
						}
					}else {
						presenter.onThrowException(new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL));
					}
				}catch (Exception sException) {
					if (presenter != null)
						presenter.onThrowException(sException);
				}
			}

			@Override
			public void onFailure(Call call, Throwable t) {
//				presenter.onThrowException(t);
				try {
					showConnectionLostDialog(context, null , new Runnable() {
						@Override
						public void run() {
							retry(call);
						}
					});
				}catch (Exception ex){
					int aX =0 ;
					aX  ++;
				}
			}

			private void retry(Call call) {
				call.clone().enqueue(this);
			}
		};
		Global.apiManagerTubeless.loginOrRregisterMVP(request, callback);
	}



	public IUser loadUserData(ISplashScreenPeresenter presenter , LoginRequest request) {
		Account account = sAccountHelper.getUserAccount();

//		Global.user = LitePal.where("userId like ?", account.name + "").findFirst(User.class);
//		Global.user = LitePal.where("username = ?", account.name + "").findFirst(User.class);
//		List<User> songs = LitePal.where("username like ? ", account.name +"" ).order("userId").find(User.class);
//		Global.user = songs.get(0);
//		List<User> dbUser2 = LitePal.findAll(User.class);


//		Global.user = Global.LoadLogedInUser(context);

//		if (Global.user == null){

//			String accountName = sAccounts.getUserAccountName();
//			LoginRequest loginRequest = null;
//			Validator validator = new Validator();
//
//			if (validator.isIranianMobileNumber(accountName)){
//				loginRequest = new LoginRequest(accountName, sAccounts.getUserAccountPassword(), DeviceUtil.GetAndroidId(context));
//			}else if (validator.isIranianMobileNumber(accountName)) {
//				loginRequest = new LoginRequest(accountName, "");
//			}else {
//				loginRequest = new LoginRequest(accountName);
//			}
//			iUser.CheckUserValidity(null, loginRequest);

			CheckUserValidity2(presenter , request);
//		}
//		return Global.user;
		return null;
	}


//	private void retry(Call<java.lang.Object> call) {
//		call.clone().enqueue(this);
//	}

	public static boolean CheckUserIsAdmin(User user) {
		try {
			if (user.getUserId() == StaticValue.AdminUserID1 ||
					user.getUserId() == StaticValue.AdminUserID2 ||
					user.getUserId() == StaticValue.AdminUserID3 ||

					user.getUserName().equals(StaticValue.AdminMail1) ||
					user.getUserName().equals(StaticValue.AdminMail2) ||
					user.getUserName().equals(StaticValue.AdminMail3) ||

					user.getUserName().equals(StaticValue.AdminMobile1) ||
					user.getUserName().equals(StaticValue.AdminMobile2) ||
					user.getUserName().equals(StaticValue.AdminMobile3))
				return true;
			else
				return false;
		}catch (Exception ex){
			return false;
		}
	}
}
