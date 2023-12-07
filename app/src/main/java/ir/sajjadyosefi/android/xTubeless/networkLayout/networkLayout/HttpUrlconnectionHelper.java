package ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import ir.sajjadyosefi.android.xTubeless.classes.JsonDateDeserializer;

/**
 * Created by sajjad on 7/16/2017.
 */

 public class HttpUrlconnectionHelper implements INetwork{


    //request = "phoneNumber=" + phoneNumber + "&message=" + message
    @Override
    public Object PostJsonToServer(String url , String request, Class returnClass) {
        URL _url = null;
        HttpURLConnection httpConn = null;
        URLConnection urlConn = null;
        Object serverResponse = null;

        try {
            _url = new URL(url);
            urlConn = _url.openConnection();
            httpConn = (HttpURLConnection) urlConn;

            if(urlConn != null && httpConn != null) {
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("POST");
            }

            OutputStream os = httpConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(request);
            writer.flush();
            writer.close();
            os.close();


            int status = httpConn.getResponseCode();
            InputStream in;
            int aaaaaaaaaaa ;
            String aaaaaaaaaaaa;
            if(status >= HttpURLConnection.HTTP_OK) {
                in = httpConn.getErrorStream();
            }else
                in = httpConn.getInputStream();



            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
            StringBuilder sb = getStringBuilder(httpConn);

            serverResponse = gson.fromJson(sb.toString(), returnClass);

            int a = 5 + 56 ;
            a += 5 ;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    @Override
    public Object PostRequestToFCM(String json, Class tClass) {
        return null;
    }

    @Override
    public Object GetDataFromServer(String url, String json, Class tClass) {
        return null;
    }

    @Override
    public Object PostPictureToServer(String url, String json, Class tClass) {
        return null;
    }


    @NonNull
    public static StringBuilder getStringBuilder(HttpURLConnection httpConn) throws IOException {
        InputStream in = httpConn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb;
    }



}
