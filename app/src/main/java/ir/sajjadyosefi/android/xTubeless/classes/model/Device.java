package ir.sajjadyosefi.android.xTubeless.classes.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import com.google.gson.Gson;

import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.classes.IDeviceRegisterRequest;
import ir.sajjadyosefi.accountauthenticator.model.response.AConfigResponse;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model.IRegisterDeviceModel;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;

import static android.content.Context.MODE_PRIVATE;
import static ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity.KEY_ERROR_MESSAGE;
import static ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity.PARAM_CONFIG;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.DEVICE_NOT_REGISTER;
import ir.sajjadyosefi.accountauthenticator.model.request.ADeviceRegisterRequest;


public class Device implements IRegisterDeviceModel {
	Context context;

	private String AndroidVersion;
	private int    AndroidAPI;
	private String BOARD;
	private String BRAND;
	private String BuildID;
	private String DISPLAY;
	private String MANUFACTURER;
	private String MODEL;
	private String SERIAL;

	public Device(Context context) {
		this.context = context;
		this.setBOARD(Build.BOARD);
		this.setBRAND(Build.BRAND);
		//this.setBuildID(Build.VERSION.SDK_INT + "-" + Build.VERSION.RELEASE  + "-" + Build.ID);
		this.setDISPLAY(Build.DISPLAY);
		this.setMANUFACTURER(Build.MANUFACTURER);
		this.setMODEL(Build.MODEL);
		this.setSERIAL(Build.SERIAL);

		this.setBuildID(Build.ID);
		this.setAndroidVersion(Build.VERSION.RELEASE);
		this.setAndroidAPI(Build.VERSION.SDK_INT);
	}


	public String getSERIAL() {
		return SERIAL;
	}

	public void setSERIAL(String SERIAL) {
		this.SERIAL = SERIAL;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String MODEL) {
		this.MODEL = MODEL;
	}

	public String getBuildID() {
		return BuildID;
	}

	public void setBuildID(String buildID) {
		BuildID = buildID;
	}

	public String getMANUFACTURER() {
		return MANUFACTURER;
	}

	public void setMANUFACTURER(String MANUFACTURER) {
		this.MANUFACTURER = MANUFACTURER;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String BRAND) {
		this.BRAND = BRAND;
	}

	public String getBOARD() {
		return BOARD;
	}

	public void setBOARD(String BOARD) {
		this.BOARD = BOARD;
	}

	public String getDISPLAY() {
		return DISPLAY;
	}

	public void setDISPLAY(String DISPLAY) {
		this.DISPLAY = DISPLAY;
	}


	public String getAndroidVersion() {
		return AndroidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		AndroidVersion = androidVersion;
	}

	public int getAndroidAPI() {
		return AndroidAPI;
	}

	public void setAndroidAPI(int androidAPI) {
		AndroidAPI = androidAPI;
	}

	@Override
	public void tryToRegisterDevice(ISplashScreenPeresenter peresenter, ADeviceRegisterRequest aDeviceRegisterRequest) {

//			new TubelessException(DEVICE_REGISTER_SUCCESSFULL));
//			new TubelessException(DEVICE_NOT_REGISTER));

		SignInActivity signInActivity = new SignInActivity();
		final boolean[] flag = new boolean[1];
		signInActivity.tryDeviceRegister(aDeviceRegisterRequest, new IDeviceRegisterRequest<Boolean, Intent>() {
			@Override
			public void onResponse(Boolean isSuccess,Intent intent) {
				flag[0] = isSuccess;

				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					String config = bundle.getString(PARAM_CONFIG);
					String error = bundle.getString(KEY_ERROR_MESSAGE);
					AConfigResponse responseX2 = new Gson().fromJson(config, AConfigResponse.class);

					if (responseX2.getTubelessException().getCode() == 200) {
						Global.aConfig = responseX2.getConfig();
						peresenter.onSuccessDeviceRegister();
					} else {
						peresenter.onThrowException(new TubelessException(DEVICE_NOT_REGISTER));
					}
				}else {
					peresenter.onThrowException(new TubelessException(DEVICE_NOT_REGISTER));
				}
			}
		});


	}

	@Override
	public boolean checkDeviceIsRegistered() {
		SharedPreferences prefs = null;
		prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
		return prefs.getBoolean("RegisterDevice", true);
	}

	@Override
	public boolean setRegisterDeviceIsDone() {
//		SharedPreferences prefs = null;
//		prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
//
//		try {
//			prefs.edit().putBoolean("RegisterDevice", false).commit();
			return true;
//		}catch (Exception e) {
//			return false;
//		}
	}

}
