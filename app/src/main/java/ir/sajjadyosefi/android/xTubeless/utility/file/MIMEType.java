package ir.sajjadyosefi.android.xTubeless.utility.file;

public enum MIMEType {
    IMAGE("image/*"), VIDEO("video/*");
    public String value;

    MIMEType(String value) {
        this.value = value;
    }
}
