package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;

import java.lang.reflect.Type;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.showConnectionLostDialog;
import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.showConnectionLostFullScreenDialog;


public abstract class TubelessRetrofitCallbackss implements Callback ,ICallback{

    private final Class<?> aClass;
    private Context mContext;
    public TubelessRetrofitCallbackss(Context context, Class<?> aClass) {
        this.mContext = context;
        this.aClass = aClass;
        t_beforeSendRequest();
    }

    public abstract void t_beforeSendRequest();

    public abstract void t_afterGetResponse();

    public abstract void t_complite();

    public abstract void t_responseNull();

    public abstract void t_retry(Call<Object> call);


    @Override
    public void onResponse(Call call, Response response) {
        t_afterGetResponse();

        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(response.body());
        ServerResponseBase responseX = null;

        try {
            RequestBody request;
            if(BuildConfig.DEBUG) {
                Buffer buffer = new Buffer();
                response.raw().request().body().writeTo(buffer);
                request = response.raw().request().body();
                Log.i( mContext.getString(R.string.Logger) + "SNO", response.raw().request().url().toString());
                Log.i( mContext.getString(R.string.Logger) + "REQUEST", buffer.readUtf8());
                Log.i( mContext.getString(R.string.Logger) + "RESPONSE", String.valueOf(jsonElement));
            }
        }catch (Exception exception){}

        try {
            if (response.body() == null){
                //throw new Exception();
                t_responseNull();
            }

            try {
                responseX = gson.fromJson(jsonElement.getAsString(), ServerResponseBase.class);
            }catch (Exception ex){
                responseX = gson.fromJson(jsonElement.toString(), ServerResponseBase.class);
            }

            if (response.body() != null ) {
//                if (responseX.getTubelessException().getCode() != 100) {
                    if ((responseX.getTubelessException().getCode() == 200) ||
                        (responseX.getTubelessException().getCode() == -10)) {

                        if (call != null && response != null) {

                            try {
                                Object object ;

                                try {
                                    object = gson.fromJson(jsonElement.getAsString(), (Type) aClass);
                                }catch (Exception e){
                                    object = gson.fromJson(jsonElement.toString(), (Type) aClass);
                                }
                                Object xxxxxxxx = Primitives.wrap(aClass).cast(object);
                                t_onSuccess(xxxxxxxx);
                            }catch (Exception ex){
                                throw responseX.getTubelessException();
                            }
                        }
                    }else {
                        throw responseX.getTubelessException();
                    }
//                }else {
//                    throw responseX.getTubelessException();
//                }
            }else {
                throw responseX.getTubelessException();
            }

            t_complite();
        } catch (TubelessException sException) {
            sException.printStackTrace();
//            if (showResult)
                sException.handleServerMessage(mContext,responseX);
        }catch (Exception sException) {
            sException.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        int a =0 ;
        a++;

        if (t.getMessage() != null && t.getMessage().contains("Unable to resolve host")){
            showConnectionLostFullScreenDialog(mContext, null, new Runnable() {
                @Override
                public void run() {
                    retry(call);
                }
            });
        }else {
            showConnectionLostDialog(mContext, null, new Runnable() {
                @Override
                public void run() {
                    retry(call);
                }
            });
        }
    }

    private void retry(Call<java.lang.Object> call) {
        t_retry(call);
        call.clone().enqueue(this);
    }
}
