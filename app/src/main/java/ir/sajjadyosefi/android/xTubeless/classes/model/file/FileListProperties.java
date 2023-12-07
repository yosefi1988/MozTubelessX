package ir.sajjadyosefi.android.xTubeless.classes.model.file;

public class FileListProperties {
    private boolean activeHeader;
    private boolean onlyShow;
    private boolean deletable;

    public FileListProperties(boolean onlyShow) {
        if (onlyShow == true){
            deletable = false;
        }
        this.onlyShow = onlyShow;
    }
    public boolean isActiveHeader() {
        return activeHeader;
    }

    public void setActiveHeader(boolean activeHeader) {
        this.activeHeader = activeHeader;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public boolean isOnlyShow() {
        return onlyShow;
    }

    public void setOnlyShow(boolean onlyShow) {
        this.onlyShow = onlyShow;
    }

}
