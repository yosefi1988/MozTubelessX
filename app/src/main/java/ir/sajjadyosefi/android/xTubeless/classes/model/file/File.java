package ir.sajjadyosefi.android.xTubeless.classes.model.file;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.ImageRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.RemoteApi;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.RetrofitImageLoader;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.file.FileUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class File extends TubelessObject {

    public static int MAP_K = 1000 ;
    public static int MAP_1 = 1001 ;
    public static int SIGNATURE = 1 ;
    public static int MAP_3 = 1003 ;

    private String uri;
    private String title;
    private int fileType;

    private EndlessList_AdapterFile.ListItemType listItemType;
    private int requestContentId;
    private int frame;
    private boolean sended = false;
    private String TAG ="file";


    private boolean isHeaderPic = false;
    private boolean isContentPic = false;



    public int getRequestContentId() {
        return requestContentId;
    }

    public void setRequestContentId(int requestContentId) {
        this.requestContentId = requestContentId;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isHeaderPic() {
        return isHeaderPic;
    }

    public void setHeaderPic(boolean headerPic) {
        isHeaderPic = headerPic;
    }

    public boolean isContentPic() {
        return isContentPic;
    }

    public void setContentPic(boolean contentPic) {
        isContentPic = contentPic;
    }

    public EndlessList_AdapterFile.ListItemType getListItemType() {
        return listItemType;
    }

    public void setListItemType(EndlessList_AdapterFile.ListItemType listItemType) {
        this.listItemType = listItemType;
    }


    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }




    public void prepareFileItem(Context mContext,
                                EndlessList_AdapterFile.FileViewHolder holder,
                                List<File> mTimelineItemList,
                                int position,
                                EndlessList_AdapterFile adapter,
                                boolean deletable) {

        File file = (File) mTimelineItemList.get(position);

        StringBuilder text = new StringBuilder();
        text.append(file.getTitle());


        Picasso.get()
                .load(file.getUri())
                //.placeholder(R.drawable.bg_search)
                //.centerInside()
                //.transform(transformation)
                .into(holder.imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(R.drawable.png_image)
                                //.transform(transformation)
                                .into(holder.imageView);
                    }
                } );
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileUtils.openFile((Activity)(mContext),file.getUri().replace("\\","/"),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        holder.textView.setText(text.substring(text.length()-15,text.length()));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageView.performClick();
            }
        });

        holder.radioButton.setChecked(position == EndlessList_AdapterFile.lastCheckedPosition);
        holder.radioButton2.setChecked(position == EndlessList_AdapterFile.lastCheckedPosition2);



        if (deletable){
            holder.buttonDelete.setEnabled(deletable);
            holder.buttonDelete.setVisibility(View.VISIBLE);
        }else {
            holder.buttonDelete.setEnabled(deletable);
            holder.buttonDelete.setVisibility(View.GONE);
        }

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimelineItemList.remove(position);

                if (mTimelineItemList.size() == 0 )
                    holder.textView.setText(R.string.someText);

                adapter.notifyDataSetChanged();


//                ((FileListActivity) mContext).fileCount = ((FileListActivity) mContext).fileCount - 1;
                ((FileListActivity)mContext).refreshButtons();
            }
        });

        holder.buttonShow.setEnabled(true);
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textView.performClick();
            }
        });

    }

    // download image using Retrofit or Picasso via HTTP POST method
    public RequestCreator createPicassoLoader(Picasso.Builder builder, String body, String url) {
        return builder.downloader(new OkHttp3Downloader(createPicassoCallFactory(body)))
                .build()
                .load(url);
    }

    private okhttp3.Call.Factory createPicassoCallFactory(String jsonBody) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        return new okhttp3.Call.Factory() {
            @Override
            public okhttp3.Call newCall(Request request) {
                Request.Builder builder = request.newBuilder();
                builder.post(requestBody);
                builder.addHeader("Content-Type", "application/json");
                return okHttpClient.newCall(builder.build());
            }
        };
    }

    public void writeToxFile(byte[] array) {
        try {
            String path = "...";
            FileOutputStream stream = new FileOutputStream(path);
            try {
                stream.write(array);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void loadImageWithRetrofit(Context context, File file, ImageView imageView) {
        final RetrofitImageLoader imageLoader = new RetrofitImageLoader(imageView);
        RemoteApi api = RemoteApi.Factory.create();
        ImageRequest.DEFAULT_BODY.setAndroidId(DeviceUtil.GetAndroidId(context));
        ImageRequest.DEFAULT_BODY.setContentId(file.getRequestContentId() + "");
        ImageRequest.DEFAULT_BODY.setFrame(file.getFrame() + "");
        api.getImage(ImageRequest.DEFAULT_BODY).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    try {
                        imageLoader.execute(file , body.byteStream());




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "Retrofit onResponse(): CODE = [" + response.code() + "], MESSAGE = [" + response.message() + "]");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "Retrofit onFailure(): t = [" + t + "]");
            }
        });
    }





}
