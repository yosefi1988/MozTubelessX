package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitWithRxJava;

import io.reactivex.Observable;
import ir.sajjadyosefi.android.xTubeless.classes.model.crypto.Crypto;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Cryptocurrency_ApiService {

    //1
    String BASE_URL = "https://api.cryptonator.com/api/full/";

    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);



}
