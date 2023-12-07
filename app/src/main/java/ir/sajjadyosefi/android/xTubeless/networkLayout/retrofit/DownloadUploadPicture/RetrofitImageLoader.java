package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.File;

public class RetrofitImageLoader extends AsyncTask<InputStream, Integer, Bitmap> {
    private ImageView imageView;
    private File file;
    Context context;
    public RetrofitImageLoader(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(InputStream... inputStreams) {
        return BitmapFactory.decodeStream(inputStreams[0]);
    }

    @Override
    protected void onPostExecute( Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);

        if (bitmap != null) {
            Uri url = save(file, context, bitmap);
            if (url != null) {
                try {
                    openFile(FileListActivity.activity, url.toString(),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void openFile(Activity activity, String url, Boolean b) throws IOException
    {
        java.io.File file = new java.io.File(url);

        Uri uri=null;


        if (b)
            uri = Uri.fromFile(file);
        else
            uri = Uri.parse(url);


        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);


        if (url.toString().toLowerCase().contains(".doc") || url.toString().toLowerCase().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (url.toString().toLowerCase().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (url.toString().toLowerCase().contains(".ppt") || url.toString().toLowerCase().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (url.toString().toLowerCase().contains(".xls") || url.toString().toLowerCase().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (url.toString().toLowerCase().contains(".zip") || url.toString().toLowerCase().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if (url.toString().toLowerCase().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (url.toString().toLowerCase().contains(".wav") || url.toString().toLowerCase().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if (url.toString().toLowerCase().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (url.toString().toLowerCase().toLowerCase().contains(".jpg") || url.toString().toLowerCase().contains(".jpeg") || url.toString().toLowerCase().contains(".png")) {
            //JPG file
            intent.setDataAndType(uri, "image/*");
        } else if (url.toString().toLowerCase().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (url.toString().toLowerCase().contains(".3gp") || url.toString().toLowerCase().contains(".mpg") || url.toString().toLowerCase().contains(".mpeg") || url.toString().toLowerCase().contains(".mpe") || url.toString().toLowerCase().contains(".mp4") || url.toString().toLowerCase().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            intent.setDataAndType(uri, "*/*");
        }
        //intent.setDataAndType(uri, "application/*");


        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        try {
            (activity).startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Log.e("Error -> ", e + "");
        }
    }

    public Uri save(File filez, Context context, Bitmap pictureBivp) {
// Assume block needs to be inside a Try/Catch block.
        try {

            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            Integer counter = 0;
            java.io.File file = new java.io.File(path, "arzyabi" + filez.getRequestContentId()+filez.getFrame() + ".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
            fOut = new FileOutputStream(file);

            Bitmap pictureBitmap = pictureBivp ;// obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());


            return Uri.fromFile(new java.io.File(file.getAbsolutePath()));
        }catch (Exception e){

            int a = 5 ;
            a++;
        }
        return null;
    }

    public void execute(File file, InputStream byteStream) {
        this.file = file;
        this.execute(byteStream);
    }
}