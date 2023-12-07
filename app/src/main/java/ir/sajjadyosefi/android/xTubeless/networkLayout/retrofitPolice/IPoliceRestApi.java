package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitPolice;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPoliceRestApi {


//    @GET("/addresssrv?status=platesearch&xtype=1&plateusage=1&platecode=220634456")
    @GET("/addresssrv")
    Call<String> reportGetUserCount(@Query("status") String status,
                                    @Query("xtype") String xtype,
                                    @Query("plateusage") String plateusage,
                                    @Query("platecode") String platecode);




//    @POST(level2 + "43_73ee9503-0b9a-45b1-bd3d-96afebd70cba")
//    Call<ConnectionCheckResponse> databaseKey(@Body ConfigRequest user);
//
//    @POST(level2 + "22_27df0782-a8e3-41c2-a116-cea7abb762ab")
//    Call<ConfigResponse> config(@Body ConfigRequest user);


}
