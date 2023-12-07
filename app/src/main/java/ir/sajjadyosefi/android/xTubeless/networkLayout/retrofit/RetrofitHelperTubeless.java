package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;


import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.PostMessagesRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineItemRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ChargeRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogCommentRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.FavRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.ImageRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.RemoteApi;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.REST_API_IP_ADDRESS_MAIN;

public class RetrofitHelperTubeless {
    private static ApiServiceTubeless service;
    private static RetrofitHelperTubeless apiManager;
    private int responceCountSize = 5;

    private RetrofitHelperTubeless() {

        HostSelectionInterceptor interceptor = new HostSelectionInterceptor();
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_IP_ADDRESS_MAIN)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        service = retrofit.create(ApiServiceTubeless.class);

    }

    public static RetrofitHelperTubeless getInstance() {
        if (apiManager == null) {
            apiManager = new RetrofitHelperTubeless();
        }
        return apiManager;
    }



    ///////////////////////////////////////////////////moz//////////////////////////////////////////////////
    public void config(LoginRequest request,Callback<Configuration> callback) {
        Call<Configuration> userCall = service.config(request);
        userCall.enqueue(callback);
    }

    public void loginOrRregister(LoginRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.login(request);
        userCall.enqueue(callback);
    }

    public void loginOrRregisterMVP(LoginRequest request, Callback callback) {
        Call<Object> userCall = service.login(request);
        userCall.enqueue(callback);
    }

    ///////////////////////////////////////////////////moz//////////////////////////////////////////////////




    public void getProfileImages(LoginRequest request, Callback callback) {
        Call<Object> userCall = service.profileImages(request);
        userCall.enqueue(callback);
    }

    private String TAG ="file";
    public void getImageFromRemoteServer(Context context , ImageRequest request, Callback callback) {
        RemoteApi api = RemoteApi.Factory.create();
        ImageRequest.DEFAULT_BODY.setAndroidId(DeviceUtil.GetAndroidId(context));
        api.getImage(ImageRequest.DEFAULT_BODY).enqueue(callback);

    }


    String countInPage = "15";
    public void getTimeline(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall;

//        if (Global.user == null)
            userCall = service.getTimeline(request);
//        else {
//            TimelineRequest requestnew = request;
////            requestnew.setUi(Global.user.getUserName());
//            userCall = service.getTimelineForuser(request);
//        }

        userCall.enqueue(callback);
    }

    public void getElectedUsers(int ApplicationId ,int pageindex ,TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall;
        userCall = service.getElectedUser(ApplicationId,pageindex,Integer.parseInt(countInPage),request);
        userCall.enqueue(callback);
    }

    public void getElectedUserByUserCode(TimelineItemRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall;
        userCall = service.getElectedUserByID(request);
        userCall.enqueue(callback);
    }

    public void getTimelineByUserIdMyPurches(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getTimelineByUserIdMyPurches(request);
        userCall.enqueue(callback);
    }

    public void getBlogMessages(PostMessagesRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getBlogMessages(request);
        userCall.enqueue(callback);
    }
    public void getPOSTUSERMessages(PostMessagesRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getPOSTUSERMessages(request);
        userCall.enqueue(callback);
    }
    public void getPOSTUSERMessages2(PostMessagesRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getPOSTUSERMessages2(request);
        userCall.enqueue(callback);
    }
    public void getMessagesUser(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getBlogUsersMessages(request);
        userCall.enqueue(callback);
    }

    public void getMyPOST(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getMyPOST(request);
        userCall.enqueue(callback);
    }

    public void getMyTRANSACTIONS(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getMyTRANSACTIONS(request);
        userCall.enqueue(callback);
    }

    public void getMyFavPost(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        request.setPageSize(countInPage);
        Call<Object> userCall = service.getMyFavPOST(request);
        userCall.enqueue(callback);
    }

    public void getTimeline(Context context , int index, TubelessRetrofitCallbackss callback,String Ids,String endDate) {
//        Call<Object> userCall = service.getTimeline(index,countInPage,Ids,endDate);
//        userCall.enqueue(callback);
    }


//    public void getCategory(int catId, int index, TubelessRetrofitCallbackss callback) {
//        Call<Object> userCall = service.getCategory(catId, index, responceCountSize * 1);
//        userCall.enqueue(callback);
//    }

    public void getCategoryLookUp(CategoriesLookUpRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.categoryLookUp(request);
        userCall.enqueue(callback);
    }





    public void chargeAccount(ChargeRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.chargeAccount(request);
        userCall.enqueue(callback);
    }

    public void registerNewBlog(NewBlogRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.newBlog(request);
        userCall.enqueue(callback);
    }

    //------------ comments ---------------
    public void getCommentTimeline(int blogId,int index, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getCommentsOfblog(blogId,index,15);
        userCall.enqueue(callback);
    }

    public void registerNewMessage(NewBlogCommentRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.newBlogComment(request);
        userCall.enqueue(callback);
    }

    public void favoritPost(FavRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.favoritPost(request);
        userCall.enqueue(callback);
    }
    public void invisiblePost(FavRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.toggleDeletePost(request);
        userCall.enqueue(callback);
    }
    public void acceptPost(FavRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.toggleAcceptPost(request);
        userCall.enqueue(callback);
    }
    public void deleteBlogComment(int id,String userId, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.deleteBlogComment(id,userId);

//        Gson gson = new Gson();
//        String aaaaaaaaaaaaaaaaa = gson.toJson(request);

        userCall.enqueue(callback);
    }
    public void invisibleBlogComment(int id,String userId,  TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.invisibleBlogComment(id,userId);
        userCall.enqueue(callback);
    }

    //------------ end comments ---------------

    public void getTimelineItem(TimelineItemRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getTimelineItem(request);
        userCall.enqueue(callback);
    }

    public void getCreatorTimelineItem(TimelineItemRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getCreatorTimelineItem(request);
        userCall.enqueue(callback);
    }


    public void getTubelessNews(TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getTubelessNews(AccountGeneral.getIDApplication(),0,1);
        userCall.enqueue(callback);
    }

    public void getAmlakList1(TimelineRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getTrustedRealEstateAdvisors(AccountGeneral.getIDApplication(),0,1);
        userCall.enqueue(callback);
    }

    public void deviceRregister(DeviceRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.deviceRegister(request);
        userCall.enqueue(callback);
    }

    public void deleteTimelineItem(int id, String userId, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.deleteTimelineItem(id,userId);


        Log.d("tubeless log", "id :" + id + " userId : " + userId);


        userCall.enqueue(callback);
    }

    public void invisibleTimelineItem(int id, String userId, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.invisibleTimelineItem(id,userId);
        userCall.enqueue(callback);
    }

    public void newContactUs(ContactUsRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.contactUs(request);
        userCall.enqueue(callback);
    }

//    public void createUser(SearchRequest searchRequest, Callback<ServerResponse> callback) {
//        Call<ServerResponse> userCall = service.search(searchRequest);
//        userCall.enqueue(callback);
//    }.



//    public void requestUpload2(MultipartBody.Part file, RequestBody userName, RequestBody password, RequestBody androidId , RequestBody serialRequestCode , RequestBody fileType , RequestBody senderType, TubelessRetrofitCallback<Object> callback) {
//        Call<Object> userCall = service.upload2(file , userName,password,androidId, serialRequestCode , fileType , senderType);
//        userCall.enqueue(callback);
//    }




    public static final class HostSelectionInterceptor implements Interceptor {
        private volatile String host;


        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            //add header1
//            if (StaticValue.configuration != null && StaticValue.configuration.getData().Token != null) {
                Request request2 = chain.request().newBuilder().addHeader("Token", "StaticValue.configuration.getData().Token").build();
                return chain.proceed(request2);
//            }else {
//                Request request2 = chain.request().newBuilder().addHeader("Token","x").build();
//                return chain.proceed(request2);
//            }
//

            //add header 2
//            Request request = chain.request();
//            request.newBuilder().addHeader("parameter", "value").build();
//            if (StaticValue.configuration != null)
//                request.newBuilder().addHeader("Token", StaticValue.configuration.getData().Token).build();
//
//
//            String host = this.host;
//            if (host != null) {
//                //HttpUrl newUrl = request.url().newBuilder()
//                //    .host(host)
//                //    .build();
//                HttpUrl newUrl = HttpUrl.parse(host);
//                request = request.newBuilder()
//                        .url(newUrl)
//                        .build();
//            }
//
////            SystemClock.sleep(1000 * 10);
//
//            return chain.proceed(request);
        }
    }
}
