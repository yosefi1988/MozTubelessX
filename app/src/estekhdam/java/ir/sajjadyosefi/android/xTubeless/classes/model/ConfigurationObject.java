package ir.sajjadyosefi.android.xTubeless.classes.model;

public class ConfigurationObject {

    public boolean Bazar;
    public boolean Myket;
    public boolean Jhobin;
    public boolean Iranapps;
    public boolean Kandoo;
    public boolean Social;
    public boolean Play;



    public static String BazarStr = "Bazar";
    public static String MyketStr = "Myket";
    public static String JhobinStr = "Jhobin";
    public static String IranappsStr = "Iranapps";
    public static String KandooStr = "Kandoo";
    public static String SocialStr = "Social";
    public static String PlayStr = "Play";


    public boolean isMyket() {
        return Myket;
    }

    public void setMyket(boolean myket) {
        Myket = myket;
    }

    public boolean isJhobin() {
        return Jhobin;
    }

    public void setJhobin(boolean jhobin) {
        Jhobin = jhobin;
    }

    public boolean isIranapps() {
        return Iranapps;
    }


    public boolean isBazar() {
        return Bazar;
    }

    public void setBazar(boolean bazar) {
        Bazar = bazar;
    }

    public void setIranapps(boolean iranapps) {
        Iranapps = iranapps;
    }

    public boolean isKandoo() {
        return Kandoo;
    }

    public void setKandoo(boolean kandoo) {
        Kandoo = kandoo;
    }

    public boolean isSocial() {
        return Social;
    }

    public void setSocial(boolean social) {
        Social = social;
    }

    public boolean isPlay() {
        return Play;
    }

    public void setPlay(boolean play) {
        Play = play;
    }



}
