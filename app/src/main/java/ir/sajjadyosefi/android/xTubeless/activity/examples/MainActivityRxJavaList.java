package ir.sajjadyosefi.android.xTubeless.activity.examples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ir.sajjadyosefi.android.xTubeless.Adapter.crypto.RecyclerViewAdapter;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.crypto.Crypto;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitWithRxJava.Cryptocurrency_ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitWithRxJava.Cryptocurrency_ApiService.BASE_URL;


public class MainActivityRxJavaList extends AppCompatActivity {

    RecyclerView recyclerView;
    Retrofit retrofit;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        callEndpoints();
    }


    private void callEndpoints() {

        Cryptocurrency_ApiService apiService = retrofit.create(Cryptocurrency_ApiService.class);

//        //Single call
//        Observable<Crypto> cryptoObservable = apiService.getCoinData("btc");
//        cryptoObservable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(result -> result.ticker)
//                .subscribe(this::handleResults, this::handleError);


        //multi Call
        Observable<List<Crypto.Market>> btcObservable = apiService
                .getCoinData("btc")
                .map(result -> Observable.fromIterable(result.ticker.markets))
                .flatMap(x -> x)
                .filter(y -> {
                    y.coinName = "btc";
                    return true;
                }).toList()
                .toObservable()
                ;

        Observable<List<Crypto.Market>> ethObservable = apiService.getCoinData("eth")
                .map(result -> Observable.fromIterable(result.ticker.markets))
                .flatMap(x -> x).filter(y -> {
                    y.coinName = "eth";
                    return true;
                }).toList().toObservable();

        Observable.merge(btcObservable, ethObservable)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);



    }

    private void handleResults(Crypto.Ticker ticker) {

        int a = 6 ;
        a++;
    }


    private void handleResults(List<Crypto.Market> marketList) {
        if (marketList != null && marketList.size() != 0) {
            recyclerViewAdapter.setData(marketList);


        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

}