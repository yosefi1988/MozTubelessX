package ir.sajjadyosefi.android.xTubeless;



//import ir.tapsell.plus.TapsellPlus;


import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;

/**
 * Created by Sajad on 2/11/2017.
 */
public class GlobalLevel2 extends Global {

    @Override
    public void onCreate() {
        super.onCreate();


        //library
        AccountGeneral.setIDApplication(1006); //todo read from system
        AccountGeneral.setIDApplicationVersion(2004);  //todo read from system
//        CommonClass.GetAppVersion(context)    //todo fix
        //zarinpal
        AccountGeneral.setAppName(getString(R.string.app_name));
        AccountGeneral.setZarinpalpayment(this.getString(R.string.zarinpalpayment));
        AccountGeneral.setSchemezarinpalpayment(this.getString(R.string.schemezarinpalpayment));


        if (BuildConfig.FLAVOR_market.equals("myket") ) {
//            MagnetSDK.initialize(getApplicationContext());
//            if (BuildConfig.DEBUG) {
//                MagnetSDK.getSettings().setTestMode(true);
//            } else {
//                MagnetSDK.getSettings().setTestMode(false);
//            }

//            TapsellPlus.initialize(this, "esaohdhqicloeoqojffeteerbddecqkcecoabcckcnrofigptnmsghsmfkacoqmoratktg");
        }


//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
//        built.setLoggingEnabled(true);
//        Picasso.setSingletonInstance(built);
//
//        Picasso.with(getActivity())
//                .load(imageUrl)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//                        //Try again online if cache failed
//                        Picasso.with(getActivity())
//                                .load(posts.get(position).getImageUrl())
//                                .error(R.drawable.header)
//                                .into(imageView, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//
//                                    }
//
//                                    @Override
//                                    public void onError() {
//                                        Log.v("Picasso","Could not fetch image");
//                                    }
//                                });
//                    }
//                });
    }

}