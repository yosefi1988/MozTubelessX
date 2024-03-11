/*
 * Copyright (C) 2007-2008 OpenIntents.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.sajjadyosefi.android.xTubeless.utility.file;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Peli
 * @author paulburke (ipaulpro)
 * @version 2013-12-11
 */
public class FileUtils {

    private FileUtils() {
    }



    /**
     * Copies one file into the other with the given paths.
     * In the event that the paths are the same, trying to copy one file to the other
     * will cause both files to become null.
     * Simply skipping this step if the paths are identical.
     */
    public static void copyFile(@NonNull String pathFrom, @NonNull String pathTo) throws IOException {
        if (pathFrom.equalsIgnoreCase(pathTo)) {
            return;
        }

        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            inputChannel = new FileInputStream(new File(pathFrom)).getChannel();
            outputChannel = new FileOutputStream(new File(pathTo)).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
        } finally {
            if (inputChannel != null) inputChannel.close();
            if (outputChannel != null) outputChannel.close();
        }
    }

    public static void openFileok(Activity activity, String url, Boolean isFromFile) throws IOException {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "image/*");
        (activity).startActivity(intent);
    }
    public static void openFile(Activity activity, String url, Boolean isFromFile) throws IOException {
        java.io.File file = new java.io.File(url);

        Uri uri = null;
//        Uri uri = Uri.parse("https:\\\\android-studio.ir\\wp-content\\uploads\\viewbinding\\android_view_binding.png");
//        Uri uri = Uri.parse("https:\\\\sajjadyosefi.ir\\img\\profile.jpg");
//        Uri uri = Uri.parse("https://www.tgju.org/touch-icon-ipad.png");

        if (isFromFile)
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

    public void save(Context context, Bitmap pictureBivp) {
// Assume block needs to be inside a Try/Catch block.
        try {

            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            Integer counter = 0;
            java.io.File file = new java.io.File(path, "FitnessGirl" + counter + ".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
            fOut = new FileOutputStream(file);

            Bitmap pictureBitmap = pictureBivp ;// obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        }catch (Exception e){

            int a = 5 ;
            a++;
        }
    }

    public Uri save(ir.sajjadyosefi.android.xTubeless.classes.model.file.File filez, Context context, Bitmap pictureBivp) {
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

}
