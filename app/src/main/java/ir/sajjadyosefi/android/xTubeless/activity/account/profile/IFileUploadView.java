package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import android.net.Uri;

import java.io.File;

public interface IFileUploadView {
    void showErrorMessage(String message);

    void uploadCompleted();

    void setUploadProgress(int progress);
}
