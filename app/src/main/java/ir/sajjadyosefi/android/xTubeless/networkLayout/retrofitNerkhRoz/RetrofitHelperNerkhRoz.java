package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitNerkhRoz;

import android.content.Context;

import java.io.IOException;
import java.net.URL;

import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.REST_API_IP_ADDRESS;


/**
 * Created by sajjad on 11/7/2018.
 */

public class RetrofitHelperNerkhRoz {

    private static INerkhRozRestApi service;
    private static RetrofitHelperNerkhRoz retrofitHelper;

    private RetrofitHelperNerkhRoz() {
        try {
            URL url = new URL(REST_API_IP_ADDRESS);
            String serverHostname = url.getHost();
            HostSelectionInterceptor interceptor = new HostSelectionInterceptor();

            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);

            // OkHttp 3
            OkHttpClient client =
                    new OkHttpClient().newBuilder()
                            .addInterceptor(interceptor)
                            .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(REST_API_IP_ADDRESS)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();

            service = retrofit.create(INerkhRozRestApi.class);

            int a = 4 ;
            a++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static RetrofitHelperNerkhRoz getInstance(Context mContext) {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHelperNerkhRoz();
        }
        return retrofitHelper;
    }

    public static final class HostSelectionInterceptor implements Interceptor {
        private volatile String host;

        public void setHost(String host) {
            this.host = host;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String host = this.host;
            if (host != null) {
                //HttpUrl newUrl = request.url().newBuilder()
                //    .host(host)
                //    .build();
                HttpUrl newUrl = HttpUrl.parse(host);
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }

//            SystemClock.sleep(1000 * 10);

            return chain.proceed(request);
        }
    }

    public void getBourseDetailsService(Callback<String> callback) {
        Call<String> userCall = service.reportBourseDetails();
        userCall.enqueue(callback);
    }



}
