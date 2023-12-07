package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitLottery;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.SearchRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sajjad on 11/7/2018.
 */

public class RetrofitHelperLottery {

    private static APILotteryService service;
    private static RetrofitHelperLottery apiManager;

    private RetrofitHelperLottery() {

        Retrofit retrofit = new Retrofit.Builder()
                //https://www.gardune.com/api/search/store/بانک
                .baseUrl("https://www.gardune.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APILotteryService.class);
    }

    public static RetrofitHelperLottery getInstance() {
        if (apiManager == null) {
            apiManager = new RetrofitHelperLottery();
        }
        return apiManager;
    }

    public void searchLotteries(String searchRequest, LotteriesRetrofitCallback<Object> callback) {
        Call<Object> userCall = service.getLotteries(searchRequest);
        userCall.enqueue(callback);
    }
//    public void search(SearchRequest searchRequest, LotteriesRetrofitCallback<Object> callback) {
//        Call<Object> userCall = service.search(searchRequest);
//        userCall.enqueue(callback);
//    }
}
