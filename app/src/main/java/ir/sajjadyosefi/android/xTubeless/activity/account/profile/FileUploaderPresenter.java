package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import android.text.TextUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.IFileUploaderModel;


public class FileUploaderPresenter implements IFileUploadPresenter {

    private final IFileUploaderModel model;
    private final IFileUploadView view;

    private Disposable videoUploadDisposable;

    public FileUploaderPresenter(IFileUploadView view, IFileUploaderModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onFileSelected(String selectedFilePath, String userId, String type) {
        if (TextUtils.isEmpty(selectedFilePath)) {
            view.showErrorMessage("Incorrect file path");
            return;
        }
        videoUploadDisposable = model.uploadFile(selectedFilePath, userId, type)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        progress -> view.setUploadProgress((int) (100 * progress)),
                        error -> view.showErrorMessage(error.getMessage()),
                        () -> view.uploadCompleted()
                );
    }

    @Override
    public void onFileSelectedWithoutShowProgress(String selectedFilePath,String userId, String type) {
        if (TextUtils.isEmpty(selectedFilePath)) {
            view.showErrorMessage("Incorrect file path");
            return;
        }
        videoUploadDisposable = model.uploadFileWithoutProgress(selectedFilePath,userId, type)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> view.uploadCompleted(),
                        error -> view.showErrorMessage(error.getMessage())
                );
    }

    @Override
    public void cancel() {
        if (videoUploadDisposable != null && !videoUploadDisposable.isDisposed()) {
            videoUploadDisposable.dispose();
        }
    }
}