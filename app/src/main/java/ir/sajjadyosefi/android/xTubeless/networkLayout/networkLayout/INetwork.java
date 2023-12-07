package ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout;

/**
 * Created by sajjad on 10/12/2018.
 */

public interface INetwork {
    public Object PostJsonToServer(String url, String json, Class tClass);
    public Object PostRequestToFCM(String json, Class tClass);
    public Object GetDataFromServer(String url, String json, Class tClass);
    public Object PostPictureToServer(String url, String json, Class tClass);

}
