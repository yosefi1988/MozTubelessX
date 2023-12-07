package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import java.io.File;

public interface IFileUploadPresenter {
    void onFileSelected(String selectedFile, String userId, String type);

    void onFileSelectedWithoutShowProgress(String selectedFilePath, String userId, String type);

    void cancel();
}
