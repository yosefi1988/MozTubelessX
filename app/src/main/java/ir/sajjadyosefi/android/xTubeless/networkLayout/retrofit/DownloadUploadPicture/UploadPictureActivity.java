package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;

/**
 * Created by sajjad on 11/8/2017.
 */


public class UploadPictureActivity extends AppCompatActivity {

    Context context;
    public static final int REQUEST_CODE_TAKE_PICTURE = 139;
    public static final int REQUEST_CODE_GALLERY      = 140;
    public static final int REQUEST_CODE_CROP_IMAGE   = 141;
    public static final int REQUEST_CODE_GET_CATEGORY   = 142;

    int selectedCategory = 0 ;
    String selectedCar;
    public static boolean selectedPicture = false;
    DilatingDotsProgressBar dilatingDotsProgressBar;

    public static final String TEMP_PHOTO_FILE_NAME = "TubelessImageTemp.jpg";
    ImageView ivShow ;
    EditText etTags;
    public static File mFileTemp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {

            return;
        }

        Bitmap bitmap;

        switch (requestCode) {

            case REQUEST_CODE_GALLERY:

                try {

                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();

                    startCropImage();

                } catch (Exception e) {

                    //Log.e(TAG, "Error while creating temp file", e);
                }

                break;
            case REQUEST_CODE_TAKE_PICTURE:

                startCropImage();
                break;
            case REQUEST_CODE_GET_CATEGORY:

                Bundle res = data.getExtras();
                int result = res.getInt("ID");
                selectedCar = res.getString("DirectoryName");
                selectedCategory = result;

                break;
            case REQUEST_CODE_CROP_IMAGE:

//                String path = data.getStringExtra(CropImage.IMAGE_PATH);
//                if (path == null) {
//                    selectedPicture = false;
//                    return;
//                }
//                selectedPicture = true;
//                bitmap = BitmapFactory.decodeFile(mFileTemp.getPath());
//                ivShow.setImageBitmap(bitmap);
//                ivShow.setVisibility(View.VISIBLE);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void takePicture() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);
            }
            else {
	        	/*
	        	 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
	        	 */
//                mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
        } catch (ActivityNotFoundException e) {

            //Log.d(TAG, "cannot take picture", e);
        }
    }
    private void openGallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
    }
    private void startCropImage() {

//        Intent intent = new Intent(this, CropImage.class);
//        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
//        intent.putExtra(CropImage.SCALE, true);
//
//        intent.putExtra(CropImage.ASPECT_X, 3);
//        intent.putExtra(CropImage.ASPECT_Y, 2);

//        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }
    public static void copyStream(InputStream input, OutputStream output) throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9001:
                if ((grantResults.length > 0) ){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        takePicture();

                    }else {
                        //Toast.makeText(context,context.getString(R.string.WeNeedYourDeviceInfo),Toast.LENGTH_LONG).show();
                        DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.WeNeedYourDeviceInfo));
                    }
                }else {
                    Toast.makeText(context,context.getString(R.string.ErrorOnGetPermision),Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }


    private static final String KEY_TEXT_VALUE = "textTag";
    private static final String KEY_INT_CATEGORY_VALUE = "selectedCategory";
    private static final String KEY_STRING_CAR_VALUE = "selectedCar";
    private static final String KEY_IS_CAR_SELECTED_VALUE = "selectedPicture";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_TEXT_VALUE, etTags.getText());
        outState.putInt(KEY_INT_CATEGORY_VALUE, selectedCategory);
        outState.putCharSequence(KEY_STRING_CAR_VALUE, selectedCar);
        outState.putBoolean(KEY_IS_CAR_SELECTED_VALUE, selectedPicture);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_picture);
        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);

        etTags = (EditText) findViewById(R.id.etTags);
        ivShow = (ImageView) findViewById(R.id.ivShow);



        if (savedInstanceState != null) {
            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE);
            etTags.setText(savedText);

            selectedCategory = savedInstanceState.getInt(KEY_INT_CATEGORY_VALUE);
            selectedCar = savedInstanceState.getString(KEY_STRING_CAR_VALUE);
            selectedPicture = savedInstanceState.getBoolean(KEY_IS_CAR_SELECTED_VALUE);
        }
        if(selectedPicture == true){
            Bitmap bitmap = BitmapFactory.decodeFile(mFileTemp.getPath());
            ivShow.setImageBitmap(bitmap);
            ivShow.setVisibility(View.VISIBLE);
        }


        (findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSelectSturceDialog(context);
            }
        });

        (findViewById(R.id.btnBrowse)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((UploadPictureActivity)context).startActivityForResult(new Intent(context, CategorySelectActivity.class),REQUEST_CODE_GET_CATEGORY);
            }
        });



        final Button btnSend = (Button)(findViewById(R.id.btnSend));
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCategory == 0){
                    DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.categoryNotSelect));
                }
                if(!selectedPicture){
                    DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.pictureNotSelect));
                }
                if(etTags.getText().length() <= 12){
                    DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.tagNotImported));
                }

                if (selectedCategory != 0 && selectedPicture && etTags.getText().length() >= 12){
                    //Do Send

                    //first
//                    RestApiHelper helper = new RestApiHelper();
//                    helper.sendImageToServer(context,mFileTemp,RestApiHelper.CAR_PIC);

                    if(getFileSize(context, mFileTemp)<4) {
//                        //3
//                        try {
//                            if(Global.user == null) {
//                                DialogUtil.ShowMessageDialog(context, "", context.getString(R.string.NotLoggedIn2));
//                            }else {
//                                if(Global.user.getCanSendPicture() == false) {
//                                    DialogUtil.ShowMessageDialog(context, "", context.getString(R.string.CanNotSendPicture));
//                                }else {
//                                    HttpFileUploadAsync HttpFileUploadAsync = new HttpFileUploadAsync(context, dilatingDotsProgressBar, btnSend,
//                                            Url.POST_ADD_CAR_PICTURE,
//                                            mFileTemp,
//                                            "Files/Pictures/Cars/" + selectedCar + "/" + Global.user.getUserID() + "/",
//                                            Url.MAIN_SERVER_ADDRESS + "Account/AddCarPictureX?Tags=" +
//                                                    etTags
//                                                    .getText()
//                                                    .toString()
//                                                    .replace(" ", "%20")
//                                                    .replace("_", "%5F") + "&CarID=" + selectedCategory + "&UserID=" + Global.user.getUserID() + "");
//
//                                    HttpFileUploadAsync.execute();
//                                }
//                            }
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
                    }else
                        DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.fileSize4Mb));

                }
            }
        });


        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        }
        else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }
        if(!selectedPicture)
            ShowSelectSturceDialog(context);
    }


    public static long getFileSize(Context context, File file) {
        long length = file.length();
        length = length/1024;
        //System.out.println("File Path : " + file.getPath() + ", File size : " + length +" KB");
        return length/1024; //MB
    }


    public void ShowSelectSturceDialog(final Context context){

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.picture_type_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(true);


        final Button btnCamera = (Button) dialoglayout.findViewById(R.id.btnCamera);
        final Button btnBrowse = (Button) dialoglayout.findViewById(R.id.btnBrowse);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();

                int permissionReadCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                int permissionWriteCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissionReadCheck != PackageManager.PERMISSION_GRANTED && permissionWriteCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity)context),
                            new String[]{
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },
                            9001);
                } else {
                    takePicture();
                }
            }//end click
        });
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                openGallery();
            }
        });

        //text
        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textView1);
        textViewText.setText(context.getString(R.string.inputType));


        try {
            alertDialog1.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
