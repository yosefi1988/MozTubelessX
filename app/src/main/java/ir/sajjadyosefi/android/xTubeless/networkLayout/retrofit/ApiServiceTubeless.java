package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;


//import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
//import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.PostMessagesRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineItemRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ChargeRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogCommentRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.FavRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.ImageRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
//import ir.sajjadyosefi.android.xTubeless.classes.modelY.request.common.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by sajjad on 11/7/2018.
 */

public interface ApiServiceTubeless {

    @POST("Category/GetCategoryNew2")
    Call<Object> categoryLookUp(@Body CategoriesLookUpRequest request);

//    @POST("api/Category/Category")
//    Call<Object> getCategory(@Query("id") int catId,
//                             @Query("index") int index,
//                             @Query("count") int count);

    @POST("Post/NewPost")
    Call<Object> newBlog(@Body NewBlogRequest request);

    @POST("Post/PostList")
    Call<Object> getTimeline(@Body TimelineRequest request);

    @POST("ElectedUsers/getElectedUserByApplicationId")
    Call<Object> getElectedUser(@Query("applicationId") int id,
                                @Query("pageIndex") int pageIndex,
                                @Query("pageSize") int pageSize,
                                @Body TimelineRequest request);

    @POST("ElectedUsers/getElectedUserById")
    Call<Object> getElectedUserByID(@Body TimelineItemRequest request);
    @POST("Post/PostDetailsNew")
    Call<Object> getTimelineItem(@Body TimelineItemRequest request);

    @POST("Post/MyPostList")
    Call<Object> getMyPOST(@Body TimelineRequest request);

    @POST("Post/toggleFavPost")
    Call<Object> favoritPost(@Body FavRequest request);

    @POST("Post/toggleAcceptPost")
    Call<Object> toggleAcceptPost(@Body FavRequest request);

    @POST("Post/toggleDeletePost")
    Call<Object> toggleDeletePost(@Body FavRequest request);

    @POST("Common/ContactUs")
    Call<Object> contactUs(@Body ContactUsRequest request);




    @POST("Wallet/WalletCharge")
    Call<Object> chargeAccount(@Body ChargeRequest request);

    @POST("Wallet/SearchTransaction")
    Call<Object> getMyTRANSACTIONS(@Body TimelineRequest request);

    /////////////////////////////////////////////////////moz///////////////////////////////////////////



    @POST("Account/Login")
    Call<Configuration> config(@Body LoginRequest request);

    @POST("Account/InsertMobileConfig")
    Call<Object> deviceRegister(@Body DeviceRequest request);

    @POST("Customer/Login")
    Call<Object> login(@Body LoginRequest request);


    @POST("Post/SearchWithUserId")
    Call<Object> getTimelineForuser(@Body TimelineRequest request);

    @POST("Post/GetByIdForCreator")
    Call<Object> getCreatorTimelineItem(@Body TimelineItemRequest request);


    @POST("Post/GetByUserId")
    Call<Object> getTimelineByUserIdMyPurches(@Body TimelineRequest request);

    @POST("PostMessage/GetMessageByUserId")
    Call<Object> getBlogMessages(@Body PostMessagesRequest request);

    @POST("Post/GetPostsMessageByUserId")
    Call<Object> getPOSTUSERMessages(@Body PostMessagesRequest request);

    @POST("Post/GetPostUsersMessages")
    Call<Object> getPOSTUSERMessages2(@Body PostMessagesRequest request);

    @POST("Post/GetUsersMessages")
    Call<Object> getBlogUsersMessages(@Body TimelineRequest request);



    @POST("Post/SearchFavPosts")
    Call<Object> getMyFavPOST(@Body TimelineRequest request);

    @POST("Post/GetByIdForCreator")
    Call<Object> getCreatorPost(@Body TimelineRequest request);



    //------------ comments ---------------
    @POST("PostMessage/Insert")
    Call<Object> newBlogComment(@Body NewBlogCommentRequest request);

    @GET("Api/Comment/deleteBlogComment")
    Call<Object> deleteBlogComment(@Query("id") int id,
                                   @Query("userId") String userId);

    @GET("Api/Comment/invisibleBlogComment")
    Call<Object> invisibleBlogComment(@Query("id") int id,
                                      @Query("userId") String userId);


    @GET("Api/Comment/getComments")
    Call<Object> getCommentsOfblog(@Query("blogId") int blogId,
                                   @Query("index") int index,
                                   @Query("count") int count);

    //------------ end comments ---------------
    /////////////////////////////////////////////////////moz///////////////////////////////////////////


//    @GET("Api/TimeLine/getTubelessProjectLastTimelineNews")
    @GET("Api/TimeLine/getTubelessProjectLastNews")
    Call<Object> getTubelessNews(@Query("id") int projectId,
                                 @Query("index") int index,
                                 @Query("count") int count);



    @GET("Api/TimeLine/getTrustedRealEstateAdvisors")
    Call<Object> getTrustedRealEstateAdvisors(@Query("id") int projectId,
                                 @Query("index") int index,
                                 @Query("count") int count);






    @GET("Api/Blog/deleteBlog")
    Call<Object> deleteTimelineItem(@Query("id") int id,
                                    @Query("userId") String userId);

    @GET("Api/Blog/invisibleBlog")
    Call<Object> invisibleTimelineItem(@Query("id") int id,
                                       @Query("userId") String userId);





    @POST("Api/User/userImageProfileAndAvatar")
    Call<Object> profileImages(@Body LoginRequest request);




    //////////////////////// image ///////////////////////
    @Streaming  // Important
    @POST("/api/DownloadFileForAndroid")
    Call<ResponseBody> getImage(@Body ImageRequest body);
    //////////////////////// image ///////////////////////



    @Multipart
    @POST("api/UploadFileFormAndroid")
    Call<Object> upload2(
            @Part MultipartBody.Part file,
            @Part("userName") RequestBody userName,
            @Part("password") RequestBody password,
            @Part("androidId") RequestBody androidId,
            @Part("serialRequestCode") RequestBody serialRequestCode,
            @Part("fileType") RequestBody fileType,
            @Part("senderType") RequestBody senderType);

    @Multipart
    @POST("api/UploadFileFormAndroid")
//    @Headers("Content-Type: application/json")
    @Headers({"Content-Type: multipart/form-data",
            "Accept: application/json",
            "Accept-Encoding: gzip, deflate"})

    Call<Object> upload(
            @Part MultipartBody.Part file,
            @Part("userName") RequestBody userName,
            @Part("password") RequestBody password,
            @Part("androidId") RequestBody androidId,
            @Part("serialRequestCode") RequestBody serialRequestCode,
            @Part("fileType") RequestBody fileType,
            @Part("senderType") RequestBody senderType);



}
