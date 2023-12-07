package ir.sajjadyosefi.android.xTubeless.classes.model.network;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface IFileUploaderModel {

    Flowable<Double> uploadFile(String selectedFilePath, String userId, String type);

    Single<ResponseBody> uploadFileWithoutProgress(String filePath, String userId, String type);
}
