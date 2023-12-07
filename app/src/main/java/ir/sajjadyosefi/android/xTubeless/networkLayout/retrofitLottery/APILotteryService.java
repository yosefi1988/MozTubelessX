package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitLottery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sajjad on 11/7/2018.
 */

public interface APILotteryService {
//    @GET("questions")
//    Call<Question> getQuestionsService(@Query("page") int page,
//                                       @Query("pagesize") int pagesize,
//                                       @Query("order") String order,
//                                       @Query("sort") String sort,
//                                       @Query("tagged") String tagged,
//                                       @Query("site") String site);

//    @GET("movies.json")
//    Call<List<Movie>> getMoviesService();

//    @POST("/data/2.1")
//    Call < T > postMovieDetails(
//            @Field("userId") String userID,
//            @Field("token") String token);



//    @POST("SearchAllHandler.ashx")
//    Call<Object> search(@Body SearchRequest searchRequest);


    //https://www.gardune.com/api/search/store/بانک
    @GET("api/search/store/{name}")
    Call<Object> getLotteries(@Path(value="name") String name);
}
