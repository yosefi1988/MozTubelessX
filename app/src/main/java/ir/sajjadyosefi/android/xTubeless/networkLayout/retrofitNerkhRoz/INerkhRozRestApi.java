package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitNerkhRoz;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INerkhRozRestApi {

    @GET("/price/stockPrice.php")
    Call<String> reportBourseDetails();

//    Call<String> reportBourseDetails(@Query("status") String status,
//                                     @Query("xtype") String xtype,
//                                     @Query("plateusage") String plateusage,
//                                     @Query("platecode") String platecode);




}
