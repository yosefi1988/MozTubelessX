package ir.sajjadyosefi.android.xTubeless.activity.common.blog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.Adapter.ViewPagerAdapter;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.File;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.FileListProperties;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineItemRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.CommentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.FavRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineItemResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.UserItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.RoundedCornersTransformation;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_AMLAK_LIST_1;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.READ_BLOG_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.classes.model.file.File.MAP_1;

/**
 * Created by sajjad on 2/11/2018.
 */

public class ReadAdActivity extends TubelessTransparentStatusBarActivity {

    Context mContext = null;
    ParentItem blogItem = null;
    UserItem blogCreator = null;
    public static int CALL_AGAIN = 45;
    //private static NonSwipeableViewPager mPager;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent x;
        if (requestCode == CALL_AGAIN) {

        }
    }

    @SuppressLint("Range")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//          قسمت بالا و پایین به صورت کامل ترنسپرنت می شود
//        هیچ سایه ای نداره
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_read_blog_ad);
        mContext = getContext();

        imageView = findViewById(R.id.imageView);
        user_profile_photo = findViewById(R.id.user_profile_photo);
        viewPager = (ViewPager) findViewById(R.id.pager1);

        //mPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        //imageviewPicture = findViewById(R.id.imageviewPicture);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewText = findViewById(R.id.textViewText);
        textViewCreator = findViewById(R.id.textViewCreator);
        textViewElectedAmlak = findViewById(R.id.textViewElectedAmlak);
        textViewAmountTitle = findViewById(R.id.textViewAmountTitle);
        textViewCommentTitle = findViewById(R.id.textViewCommentTitle);
        textViewDate = findViewById(R.id.textViewDate);
        textViewAmounts = findViewById(R.id.textViewAmounts);
        buttonFav = findViewById(R.id.buttonFav);
        buttonMessages = findViewById(R.id.buttonMessages);
        buttonInvisible = findViewById(R.id.buttonInvisible);
        buttonAccept = findViewById(R.id.buttonAccept);
        buttonBack = findViewById(R.id.buttonBack);
        //buttonCharg_e = findViewById(R.id.buttonCharg_e);   // refactor send to TubelessPayAcrivity parent
        buttonElectedAmlak = findViewById(R.id.buttonElectedAmlak);
        buttonreport = findViewById(R.id.buttonreport);
        mRecyclerViewTimeline = findViewById(R.id.recyclerView);

        Gson gson = new Gson();

        //old Transfer
//        String objectString = getIntent().getStringExtra("Object");

        //new transfer
        Bundle intent = getIntent().getExtras();
        String objectString = intent.getString("Object");
        String type = intent.getString("Type");

        if (type.equals("TimelineItem")) {
//            blogItem = gson.fromJson(objectString, TimelineItem.class);
//            firstFillData((TimelineItem)blogItem);
//
//            fillTitle(getContext(),((TimelineItem) blogItem).getTitle(),((TimelineItem) blogItem).getCategoryID(),textViewTitle);

        } else if (type.equals("NewTimelineItem")) {
            blogItem = gson.fromJson(objectString, TextItem.class);
            firstFillData((TextItem) blogItem);

            fillTitle(
                    getContext(),
                    ((TextItem) blogItem),
                    textViewTitle);

        } else if (type.equals("MainPageNews")) {

//            blogItem = (MainItem) gson.fromJson(objectString, TimelineItem.class);
//            firstFillDataNews((TimelineItem)blogItem);
//
////            fillTitle(getContext(),((TimelineItem) blogItem).getTitle(),((TimelineItem) blogItem).getCategoryID(),textViewTitle);
        }


        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavRequest aaaa = new FavRequest();
                aaaa.setUserCode(Global.user2.getUserCodeAsString());
                aaaa.setIDPost(((MainItem) blogItem).getID() + "");
                actionOnPost(aaaa, DO_FAV);
            }
        });




        buttonInvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavRequest aaaa = new FavRequest();
                aaaa.setUserCode(Global.user2.getUserCodeAsString());
                aaaa.setIDPost(((MainItem) blogItem).getID() + "");
                actionOnPost(aaaa, DO_INVISIBLE);
            }
        });

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavRequest aaaa = new FavRequest();
                aaaa.setUserCode(Global.user2.getUserCodeAsString());
                aaaa.setIDPost(((MainItem) blogItem).getID() + "");
                actionOnPost(aaaa, DO_ACCEPT);
            }
        });


        if (((TextItem) blogItem).isFav()) {
            Drawable tempImage = getResources().getDrawable(R.drawable.png_star);
            buttonFav.setImageDrawable(tempImage);

        } else {
            Drawable tempImage = getResources().getDrawable(R.drawable.png_star_off);
            buttonFav.setImageDrawable(tempImage);
        }
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();

                if (Global.user2 != null) {
                    Toast.makeText(getContext(), "به زودی...", Toast.LENGTH_SHORT).show();
//                    if (isOwner){
////                    if (true){
////                        Toast.makeText(getContext(),"owner" , Toast.LENGTH_SHORT).show();
//                        bundle2.putInt("type", TYPE_LIST_Message_FROM_USERS);
//                        bundle2.putInt("CAT_COUNT", 10);
//                        bundle2.putBoolean("isOwner" ,isOwner);
//                        bundle2.putInt("blogId", blogItem.getID());
//                        getContext().startActivity(ContainerActivity.getIntent(getContext(), bundle2));
//                    }else {
//                        bundle2.putInt("type", TYPE_LIST_Message);
//                        bundle2.putInt("CAT_COUNT", 10);
//                        bundle2.putBoolean("isOwner" ,isOwner);
//                        bundle2.putInt("blogId", blogItem.getID());
////                        bundle2.putString("userId", ((NewTimelineItem) blogItem).getUi());
//                        getContext().startActivity(ContainerActivity.getIntent(getContext(), bundle2));
//                    }
                } else {

                }
            }
        });


        if (BuildConfig.FLAVOR_version_name.equals("estekhdam")||
            BuildConfig.FLAVOR_version_name.equals("yafte")||
            BuildConfig.FLAVOR_version_name.equals("yadak")||
            BuildConfig.FLAVOR_version_name.equals("moz")) {
            textViewElectedAmlak.setVisibility(View.GONE);
            buttonElectedAmlak.setVisibility(View.GONE);
            textViewCommentTitle.setVisibility(View.GONE);
        }


    }



    //    public static void fillTitle(Context context ,TimelineNewItem blog. TextView textViewTitle) {
    private void fillTitle(Context context, TextItem mainItem, TextView textViewTitle) {
        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(mainItem.getTitle());
        textViewTitle.setText(stringBuilderTitle.toString());

        if (BuildConfig.FLAVOR_version_name.equals("winner")) {

        } else if (BuildConfig.FLAVOR_version_name.equals("moz")) {

        } else {

        }

    }

    public static String fillTitleForShare(Context context, String title, int cat) {
        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(title);


        if (BuildConfig.FLAVOR_version_name.equals("winner")) {

        } else if (BuildConfig.FLAVOR_version_name.equals("moz")) {

        } else {

        }

        if (context.getResources().getInteger(R.integer.cat1Yadak) == cat) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type1_yadak));
            stringBuilderTitle.append(")");
        } else if (context.getResources().getInteger(R.integer.cat2Yadak) == cat) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type2_yadak));
            stringBuilderTitle.append(")");
        } else if (context.getResources().getInteger(R.integer.cat3Yadak) == cat) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type3_yadak));
            stringBuilderTitle.append(")");
        } else if (context.getResources().getInteger(R.integer.cat1Yafte) == cat) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type1_yafte));
            stringBuilderTitle.append(")");
        } else if (context.getResources().getInteger(R.integer.cat2Yafte) == cat) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type2_yafte));
            stringBuilderTitle.append(")");
        } else if (context.getResources().getInteger(R.integer.cat3Yafte) == cat) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type3_yafte));
            stringBuilderTitle.append(")");
        } else {
            stringBuilderTitle.append(" - ");
            stringBuilderTitle.append(cat + "");
        }

        return stringBuilderTitle.toString();
    }


    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }


    TextView textViewTitle, textViewDescription, textViewDate, textViewAmounts, textViewText,textViewCreator, textViewElectedAmlak,textViewAmountTitle, textViewCommentTitle;
    Button buttonMessages, buttonInvisible, buttonAccept, buttonBack, buttonElectedAmlak, buttonreport;
    ImageButton buttonFav;
    ImageView imageviewPicture,user_profile_photo, imageView;

    private void initSlideShow(List<File> files, TextItem textItem) {
        ArrayList<String> XMENArrayV2 = new ArrayList<>();

        if (textItem.getTitlePicture() != null && textItem.getTitlePicture().length() > 5) {
            XMENArrayV2.add(textItem.getTitlePicture());
        }

        if (textItem.getTextPicture() != null && textItem.getTextPicture().length() > 5) {
            XMENArrayV2.add(textItem.getTextPicture());
        }

        if (files != null && files.size() > 0) {
            for (File item : files) {
                if (item.getFileType() == MAP_1)
                    XMENArrayV2.add(item.getUri());
            }
        }

        if (XMENArrayV2.size() == 0) {
            viewPager.setVisibility(View.GONE);
        } else {
            //mPager.setAdapter(new SliderAdapterHome(getContext(), XMENArrayV2));
            viewPager.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);


            adapter = new ViewPagerAdapter(getContext(), XMENArrayV2);
            viewPager.setAdapter(adapter);

        }
    }
    PagerAdapter adapter;
    ViewPager viewPager;


    private void firstFillData(TextItem mainItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();
//        if (newTimelineItem.getCategoryID() == StaticValue.newsCategory){
//            viewHeader.setVisibility(View.GONE);
//        }else {
//            viewHeader.setVisibility(View.VISIBLE);
//        }

//        textViewLocation.setText(newTimelineItem.getTextFromJson());

        if (mainItem.getText() == null) {
            textViewText.setText("در حال دریافت اطلاعات...");
            textViewCreator.setText("در حال دریافت اطلاعات...");
            textViewElectedAmlak.setText("در حال دریافت اطلاعات...");
            //textViewText.setVisibility(View.GONE);
//            textViewText.setText("\n");
        } else
            textViewText.setText(mainItem.getText());

        StringBuilder desc = new StringBuilder();
        desc.append("در");
        desc.append(" ");
        desc.append(mainItem.getTTN());
        desc.append(" ");
        desc.append("استان");
        desc.append(" ");
        desc.append(mainItem.getStateName());
        desc.append(" ");
//        desc.append("شهر");
//        desc.append(newTimelineItem.getCityName());

            desc.append("(");
            desc.append("فعال تا");
            desc.append(" ");
            desc.append(":");
            desc.append(mainItem.getDateTimeExpire());
            desc.append(")");

        desc.append("\n");
        textViewDescription.setText(desc.toString());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("تاریخ ثبت : ");
        stringBuilder.append(mainItem.getDateTime());
        stringBuilder.append("\n");

        if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
            stringBuilder.append(mainItem.getTTN());
            stringBuilder.append(" در تاریخ : ");
        } else {
            stringBuilder.append("تاریخ اعتبار : ");
        }
        stringBuilder.append(mainItem.getDateTimeExpire());
        textViewDate.setText(stringBuilder.toString() + "\n");

//        textViewUserName.setText(newTimelineItem.getUserNameMasked(newTimelineItem.getUserName()));
//        textViewCount.setText(newTimelineItem.getViewCount() + "");

        if (mainItem.isFav()) {
            Drawable tempImage = getResources().getDrawable(R.drawable.png_star);
            buttonFav.setImageDrawable(tempImage);
        } else {
            Drawable tempImage = getResources().getDrawable(R.drawable.png_star_off);
            buttonFav.setImageDrawable(tempImage);
        }


//        if (newTimelineItem.getUserImage() != null && newTimelineItem.getUserImage().length() < 5){
//            Picasso.get()
//                    .load(R.drawable.png_user)
//                    .transform(transformation)
//                    .into(imageViewUserAvatar);
//        }else {
//            Picasso.get()
//                    .load(newTimelineItem.getUserImage())
//                    .placeholder(R.drawable.bg_search)
//                    .centerInside()
//                    .transform(transformation)
//                    .into(imageViewUserAvatar, new com.squareup.picasso.Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            Picasso.get()
//                                    .load(R.drawable.png_user)
//                                    .transform(transformation)
//                                    .into(imageViewUserAvatar);
//                        }
//                    });
//        }
//

        fillClicks(mainItem);
//        imageViewTitle(mainItem);
    }

    private void imageViewTitle(TextItem mainItem) {
//        //image title
//        if (mainItem.getTitlePicture() == null) {
//            imageView.setVisibility(View.GONE);
//        }else {
//            imageView.setVisibility(View.VISIBLE);
//            Picasso.get()
//                    .load(mainItem.getTitlePicture())
//                    //.noPlaceholder()
//                    .placeholder(R.drawable.png_image)
//                    .error(R.drawable.png_image)
////                    .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
////                    .centerCrop()
//                    .into(imageView, new com.squareup.picasso.Callback() {
//                        @Override
//                        public void onSuccess() {
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            Picasso.get()
//                                    .load(R.drawable.png_image)
//                                    //.transform(transformation)
//                                    .into(imageView);
//                        }
//                    });
//
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Intent intent = new Intent(getContext(), zoomImageActivity.class);
////                    intent.putExtra("image" , newTimelineItem.getTitlePicture());
////                    intent.putExtra("text" , newTimelineItem.getTextFromJson());
////                    getContext().startActivity(intent);
////                    overridePendingTransition(R.anim.fadeout, R.anim.fadein);
//                }
//            });
//        }
    }

    private void firstFillDataNews(TimelineItem newTimelineItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();

        if (newTimelineItem.getCategoryID() == StaticValue.newsCategory){
//            viewHeader.setVisibility(View.GONE);
        }else {
//            viewHeader.setVisibility(View.VISIBLE);
        }


        if (newTimelineItem.getPicture() != null && newTimelineItem.getPicture().length() > 10) {
//            if ((imageviewPicture != null)) {
//                imageviewPicture.setVisibility(View.VISIBLE);
//                Picasso.get()
//                        .load(newTimelineItem.getPicture())
//                        .placeholder(R.drawable.png_image)
//                        //.centerInside()
//                        //.transform(transformation)
//                        .into(imageviewPicture, new com.squareup.picasso.Callback() {
//                            @Override
//                            public void onSuccess() {
//
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//
//                                Picasso.get()
//                                        .load(R.drawable.png_image)
//                                        //.transform(transformation)
//                                        .into(imageviewPicture);
//                            }
//                        });
//            }
        }



        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(newTimelineItem.getRegisterDate());

        stringBuilder.append("تاریخ ثبت : ");
        stringBuilder.append(" ( ");
        stringBuilder.append(newTimelineItem.getDate());
        stringBuilder.append(" ) ");
        stringBuilder.append("\n");

        if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
//            stringBuilder.append(mainItem.getTTN());
            stringBuilder.append("در تاریخ : ");
        }else {
            stringBuilder.append("تاریخ اعتبار : ");
        }
        stringBuilder.append(" ( ");
        stringBuilder.append(newTimelineItem.getRegisterDate());
        stringBuilder.append(" ) ");
        textViewDate.setText(stringBuilder.toString() + "\n");


//        if(yafteItem.isInMyFavList())
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
//        else
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);


    }

//    private void firstFillData(TimelineItem timelineItem) {
//
//        DateConverterSjd dateUtiliti = new DateConverterSjd();
//
//        if (timelineItem.getCategoryID() == StaticValue.newsCategory){
//            viewHeader.setVisibility(View.GONE);
//        }else {
//            viewHeader.setVisibility(View.VISIBLE);
//        }
//
//
//
//        textViewLocation.setText(timelineItem.getLocation());
//
//
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(timelineItem.getDate());
//        stringBuilder.append(" ( ");
//        stringBuilder.append("تاریخ ثبت : ");
//        stringBuilder.append(timelineItem.getRegisterDate());
//        stringBuilder.append(" ) ");
//        textViewDate.setText(stringBuilder.toString());
//
//        textViewUserName.setText(timelineItem.getUserNameMasked(timelineItem.getUserName()));
//        textViewCount.setText(timelineItem.getViewCount() + "");
//
//
//
////        if(yafteItem.isInMyFavList())
////            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
////        else
////            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);
//
//
//
//
//        if (timelineItem.getUserImage().length() < 5){
//            Picasso.get()
//                    .load(R.drawable.png_user)
//                    //.transform(transformation)
//                    .into(imageViewUserAvatar);
//        }else {
//            Picasso.get()
//                    .load(timelineItem.getUserImage())
//                    .placeholder(R.drawable.bg_search)
//                    //.centerInside()
//                    //.transform(transformation)
//                    .into(imageViewUserAvatar, new com.squareup.picasso.Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            Picasso.get()
//                                    .load(R.drawable.png_user)
//                                    //.transform(transformation)
//                                    .into(imageViewUserAvatar);
//                        }
//                    });
//        }
//
//
//        fillClicks(timelineItem);
//    }

    private void fillClicks(TextItem timelineItem) {

//        newTimelineItem.getCreatorFullName();
//        newTimelineItem.getAmount();
//        newTimelineItem.isDeleted();
//        newTimelineItem.isActive();

        View.OnClickListener onReportClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timelineItem instanceof MainItem) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CONTENT_REPORT);
                    bundle.putString(ContactUsActivity.ID, timelineItem.getID() + "");
                    bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                            getContext().getString(R.string.reportContent),
                            ((TextItem) timelineItem).getTitle()));
                    bundle.putString(ContactUsActivity.Phone,  String.format("BID=%s-BUID=%s-UID=%s",
                            ((TextItem) timelineItem).getID(),
                            ((TextItem) timelineItem).getCreatorID(),
                            ((Global.user2 == null ? "X":Global.user2.getUserCode()))));
                    getActivity().startActivity(ContactUsActivity.getIntent(getContext(), bundle));
                }else {

                }
            }
        };
        buttonreport.setOnClickListener(onReportClickListener);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (timelineItem instanceof TimelineItem) {
//                    share(mContext,0,timelineItem);
//                }else
                    if (timelineItem instanceof MainItem) {
                    shareNew(mContext,0,timelineItem);
                }else {

                }
            }
        };

        View.OnClickListener onCommentsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (timelineItem instanceof TimelineItem) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type" , TYPE_COMMENTS);
//                    bundle.putInt("blogId" , timelineItem.getBlogID());
//                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
//                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
//                }else
                    if (timelineItem instanceof MainItem) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , FRAGMENT_COMMENTS);
//                    bundle.putInt("blogId" , timelineItem.getBlogID());
                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
                }else {

                }
            }
        };

        View.OnClickListener onStarClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,yafteItem.getID(),Global.user.getUserID(),yafteItem.isInMyFavList());
//                    asyncFavouriteBlogItem.execute();

            }
        };

//        holder.imageViewFavourite.setOnClickListener(onStarClickListener);
//        holder.textViewFavourite.setOnClickListener(onStarClickListener);


        View.OnClickListener onclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        buttonElectedAmlak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type" , ITEM_TYPE_AMLAK_LIST_1);
                bundle.putInt("CAT_COUNT", 10);
                getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        TimelineItemRequest req = new TimelineItemRequest();
        req.setIDPost(((MainItem)blogItem).getID() + "");

        buttonCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetNameActivity.launch(intentPayment);
            }
        });

        if (Global.user2 != null)
            Global.apiManagerTubeless.getAdItem(req, readBlogCallBack);

        else {
            finish();
        }
    }

    private void fillData(TextItem timelineItem) {

        StringBuilder desc = new StringBuilder();
        desc.append("در");
        desc.append(" ");
        desc.append(timelineItem.getTTN());
        desc.append(" ");
        desc.append("استان");
        desc.append(" ");
        desc.append(timelineItem.getStateName());
        desc.append(" ");
//        desc.append("شهر");
//        desc.append(timelineItem.getCityName());

        desc.append("(");
        desc.append("فعال تا");
        desc.append(" ");
        desc.append(":");
        desc.append(timelineItem.getDateTimeExpire());
        desc.append(")");
        desc.append("\n");
        textViewDescription.setText(desc.toString());


        if (timelineItem.getText() == null) {
            textViewText.setVisibility(View.GONE);
            textViewCommentTitle.setVisibility(View.GONE);
        }else {
            textViewText.setText(timelineItem.getText());
            textViewText.setVisibility(View.VISIBLE);

            if (!BuildConfig.FLAVOR_version_name.equals("estekhdam"))
                textViewCommentTitle.setVisibility(View.VISIBLE);
        }

        if (Global.user2.isUserAdmin()){
            buttonAccept.setVisibility(View.VISIBLE);
            buttonInvisible.setVisibility(View.VISIBLE);
        }

        if (timelineItem.isReciveMessage()){
            buttonMessages.setEnabled(true);
            buttonMessages.setVisibility(View.VISIBLE);
            buttonCharge.setVisibility(View.GONE);
        }else {
            buttonMessages.setEnabled(false);
            buttonMessages.setVisibility(View.VISIBLE);
            buttonCharge.setVisibility(View.GONE);
        }
        buttonreport.setEnabled(true);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("تاریخ ثبت : ");
        stringBuilder.append(timelineItem.getDateTime());
        stringBuilder.append("\n");

        if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
            stringBuilder.append(timelineItem.getTTN());
            stringBuilder.append(" در تاریخ : ");
        }else {
            stringBuilder.append("تاریخ اعتبار : ");
        }
        stringBuilder.append(timelineItem.getDateTimeExpire());
        stringBuilder.append("\n");
        stringBuilder.append("تاریخ انتشار : ");
        stringBuilder.append(timelineItem.getPublishDate());

        if(timelineItem.getCreatorFullName() != null){
            stringBuilder.append("\n");
            stringBuilder.append("ناشر :");
            stringBuilder.append(timelineItem.getCreatorFullName());
        }

        if(timelineItem.getAcceptDate() != null){
            stringBuilder.append("\n");
            stringBuilder.append("تاریخ تایید اگهی :");
            stringBuilder.append(timelineItem.getAcceptDate());
        }
        if(timelineItem.getModifiedDate() != null){
            stringBuilder.append("\n");
            stringBuilder.append("تاریخ ویرایش اگهی :");
            stringBuilder.append(timelineItem.getModifiedDate());
        }

        if (timelineItem.getViewCount() > 15) {
            stringBuilder.append("\n");
            stringBuilder.append("دفعات مشاهده اگهی :");
            stringBuilder.append(timelineItem.getViewCount());
        }

        textViewDate.setText(stringBuilder.toString() + "\n");


        //amounts
        if (timelineItem.getAmounts() == null || (timelineItem.getAmounts().size() == 0)){
            textViewAmountTitle.setVisibility(View.GONE);
            textViewAmounts.setVisibility(View.GONE);
        }else {
            textViewAmountTitle.setVisibility(View.VISIBLE);
            textViewAmounts.setVisibility(View.VISIBLE);
            StringBuilder amounts = new StringBuilder();
            for (Amounts item : timelineItem.getAmounts()) {
                amounts.append(item.getText());
                amounts.append(":");
                desc.append("\t\t");
                amounts.append(item.getValue());
                amounts.append("\n");
            }
            textViewAmounts.setText(amounts.toString());
        }

        //images
        if (timelineItem.getImages() == null || (timelineItem.getImages().size() == 0)){
            mRecyclerViewTimeline.setVisibility(View.GONE);

        }else {

            fileList = new ArrayList<File>();
//            File headerInList = new File();
//            headerInList.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_HEADER);
//            fileList.add(headerInList);
//
//            File Emptylist = new File();
//            Emptylist.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_EMPTY_LIST);
//            fileList.add(Emptylist);



            for (String item : timelineItem.getImages()) {
                File map1 = new File();
//                map1.setTitle("item " + java.util.UUID.randomUUID());
                map1.setTitle(item.substring(item.lastIndexOf("/")));
                map1.setRequestContentId(1);
                map1.setFrame(1);
                map1.setFileType(MAP_1);
                map1.setUri(item);
                map1.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_ITEM);
                fileList.add(fileList.size() , map1);
            }
            FileListProperties fileListProperties = new FileListProperties(true);

            mRecyclerViewTimeline.setVisibility(View.VISIBLE);
            mRecyclerViewTimeline.setHasFixedSize(true);
            mRecyclerViewTimeline.setItemAnimator(new DefaultItemAnimator());
            mLayoutManager = new LinearLayoutManager(getContext());
             adapter_Posts = new EndlessList_AdapterFile(
                    getContext(),
                    mLayoutManager,
                    getRootActivity(),
                    fileList,
                    EndlessList_AdapterFile.ListType.LIST_OF_PICTURES,
                    fileListProperties);

            mRecyclerViewTimeline.setLayoutManager(mLayoutManager);
            mRecyclerViewTimeline.setAdapter(adapter_Posts);
        }

        //text picture
//        if (timelineItem.getTextPicture() != null && timelineItem.getTextPicture().length() > 10) {
//            imageviewPicture.setVisibility(View.VISIBLE);
//            Picasso.get()
//                    .load(timelineItem.getTextPicture())
//                    //.load("https://tejaratnews.com/wp-content/uploads/2018/12/boors.jpg")
//                    //.placeholder(R.drawable.bg_search)
//                    //.centerInside()
//                    //.transform(transformation)
//                    .into(imageviewPicture, new com.squareup.picasso.Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            Picasso.get()
//                                    .load(R.drawable.png_image)
//                                    //.transform(transformation)
//                                    .into(imageviewPicture);
//                        }
//                    } );
//
//        }else {
//            imageviewPicture.setVisibility(View.GONE);
//        }
    }
    private List<File> fileList ;
    EndlessList_AdapterFile adapter_Posts;
    LinearLayoutManager mLayoutManager;
    RecyclerView mRecyclerViewTimeline;


    protected void shareNew(Context mContext, int listType, TextItem timelineItem0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        TextItem timelineItem = (TextItem) timelineItem0;

//        stringBuilder0.append(fillTitleForShare(mContext, timelineItem.getTitle(),timelineItem.getCategoryID()));
        stringBuilder0.append("\n");
        stringBuilder0.append("\n");


        stringBuilder0.append(timelineItem.getTitle());
        stringBuilder0.append("-");
        stringBuilder0.append("توضیحات:");
//        stringBuilder0.append(((NewTimelineItem) timelineItem0).getStatementFromJson());

        stringBuilder0.append("\n");
        stringBuilder0.append("\n");
        stringBuilder0.append(" ثبت شده در اپلیکیشن در تاریخ ");
//        stringBuilder0.append(timelineItem.getRegisterDate());

        stringBuilder0.append("\n");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
        intent.setType("text/plain");
        ((Activity)mContext).startActivityForResult(intent , 60);
    }

    protected void share(Context mContext, int listType, ParentItem timelineItem0) {
//                EndlessList_Adapter.prepareToShare(mContext,blogItem.getTitlePicture(), blogItem.getStatement(), loadedImage[0]);

//        TimelineItem timelineItem = (TimelineItem) timelineItem0;
//        StringBuilder stringBuilder0 = new StringBuilder();
//        stringBuilder0.append(fillTitleForShare(mContext, timelineItem.getTitle(),timelineItem.getCategoryID()));
//        stringBuilder0.append("\n");
//        stringBuilder0.append("\n");
//
//
//        stringBuilder0.append(timelineItem.getTitle());
//        stringBuilder0.append("-");
//        stringBuilder0.append(timelineItem.getPicture());
//
//        stringBuilder0.append(" در ");
//        stringBuilder0.append(((TimelineItem) timelineItem0).getLocation());
//
//        stringBuilder0.append("\n");
//        stringBuilder0.append("\n");
//        stringBuilder0.append(" ثبت شده در اپلیکیشن  در تاریخ ");
//        stringBuilder0.append(timelineItem.getRegisterDate());
//
//        stringBuilder0.append("\n");
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
//        intent.setType("text/plain");
//        ((Activity)mContext).startActivityForResult(intent , 60);
    }


    public static int DO_FAV = 1;
    public static int DO_INVISIBLE = 2;
    public static int DO_ACCEPT = 3;

    private void actionOnPost(FavRequest favRequest , int type) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
                progressDialog.show();
            }

            @Override
            public void t_afterGetResponse() {
                progressDialog.hide();
            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() == 200) {
                Toast.makeText(mContext,"انجام شد" ,Toast.LENGTH_SHORT).show();
//                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            finish();
//                        }
//                    });

                    if (type == DO_FAV) {
                        ((TextItem) blogItem).setFav(!((TextItem) blogItem).isFav());
                        if (((TextItem) blogItem).isFav()) {
                            Drawable tempImage = getResources().getDrawable(R.drawable.png_star);
                            buttonFav.setImageDrawable(tempImage);
                        } else {
                            Drawable tempImage = getResources().getDrawable(R.drawable.png_star_off);
                            buttonFav.setImageDrawable(tempImage);
                        }
                        ListFragment.fragment.favMethod(((MainItem) blogItem).getID(), ((TextItem) blogItem).isFav());
                    }
                    if (type == DO_ACCEPT) {


                        if (BuildConfig.FLAVOR_version_name.equals("amlak")) {
                            if (blogCreator.getMobile() != null){
                                progressDialog.show();
                                sendSMS(mContext, blogCreator.getMobile());
                            }
                        }
                    }
                    if (type == DO_INVISIBLE) {

                    }

                }else {
                    Toast.makeText(mContext,"به مشکل خوردیم" ,Toast.LENGTH_SHORT).show();
//                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dialog.dismiss();
//                        }
//                    });
                }
            }
        };

        if (type == DO_ACCEPT) {
            Global.apiManagerTubeless.acceptPost(favRequest, ssssssss);
        }
        if (type == DO_INVISIBLE) {
            Global.apiManagerTubeless.invisiblePost(favRequest, ssssssss);
        }
        if (type == DO_FAV) {
            Global.apiManagerTubeless.favoritPost(favRequest, ssssssss);
        }
    }

    private void invisibleComment(int id ,String userId) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {

            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    //pic
//                    boolean havePic = true;
//                    if (filesList.size()>=3){
//                        havePic = true;
//                    }
//
//                    if(havePic){
//
//                        //ok
//                        Intent mIntent = new Intent(getContext(), FileUploadService.class);
//                        mIntent.putExtra("BlogId", responseX.getTubelessException().getMessage());
//
//                        if (lastCheckedPosition2 != -1){
//                            mIntent.putExtra("TitlePicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition2).getUri())));
//                        ..
//                        }
//
//                        if (lastCheckedPosition != -1){
//                            mIntent.putExtra("TextPicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition).getUri())));
//                        }
//
//                        int index = 0;
//                        for (File file : filesList) {
//                            if (file.getListItemType() == EndlessList_AdapterFile.ListItemType.TYPE_ITEM && !file.isHeaderPic() && !file.isContentPic() ) {
//                                index++;
//                                mIntent.putExtra("image" + index, UriUtil.getPath(getContext(), Uri.parse(file.getUri())));
//                            }
//                        }
//                        mIntent.putExtra("filesCount", index );
//                        FileUploadService.enqueueWork(getContext(), mIntent);
//
//
//                    }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
//                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.invisibleBlogComment(id,userId ,ssssssss);
    }

    private void deleteComment(int id ,String userId) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {

            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
//                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.deleteBlogComment(id,userId ,ssssssss);
    }

    TubelessRetrofitCallbackss readBlogCallBack = new TubelessRetrofitCallbackss(this, TimelineItemResponse.class) {
        @Override
        public void t_beforeSendRequest() {
            if (progressDialog != null)
                progressDialog.show();
        }

        @Override
        public void t_afterGetResponse() {
//                progressDialog.hide();
            progressDialog.cancel();
        }

        @Override
        public void t_complite() {

        }


        @Override
        public void t_responseNull() {
            Toast.makeText(mContext,"اوه! ما دچار مشکل شدیم.",Toast.LENGTH_LONG).show();
        }


        @Override
        public void t_retry(Call<Object> call) {

        }


        @Override
        public void t_onSuccess(Object response) {
            int a = 5 ;
            a++;
            Gson gson = new Gson();

            JsonObject jsonObject = gson.toJsonTree(response).getAsJsonObject();
            TimelineItemResponse itemResponse = gson.fromJson(jsonObject, TimelineItemResponse.class);

            if(itemResponse.getTubelessException().getCode() == 200) {

                JsonObject jsonObject2 = gson.toJsonTree(itemResponse.getPostItem()).getAsJsonObject();
                TextItem textItem = gson.fromJson(jsonObject2, TextItem.class);

                blogItem = textItem;
                fillData(textItem);

                //todo save new balanse

                if (Global.user2.getWallet() != null)
                    Global.user2.getWallet().setAmount(((TimelineItemResponse) response).getWallet().getAmount());
                
                 Wallet wallet = new Wallet();
                AWallet aWallet = wallet.loadWalletData();
                wallet.updateWalletAmount(((TimelineItemResponse) response).getWallet().getAmount());


                aWallet = wallet.loadWalletData();

                ListFragment.fragment.seenMethod(((MainItem)blogItem).getID());
                initSlideShow(fileList,textItem);
                blogCreator = itemResponse.creator;
                initCreator(itemResponse.creator);
            }else if (((TimelineItemResponse)response).getTubelessException().getCode() == -10){
                buttonCharge.setEnabled(true);
                buttonCharge.setVisibility(View.VISIBLE);
                buttonMessages.setVisibility(View.GONE);
                buttonMessages.setEnabled(false);

//                    textViewCommentTitle.setVisibility(View.GONE);
//                    textViewText.setVisibility(View.GONE);

                textViewText.setText("محتوی غیر قابل نمایش");
                textViewText.setVisibility(View.VISIBLE);

                textViewAmountTitle.setVisibility(View.VISIBLE);
                textViewAmounts.setVisibility(View.VISIBLE);
                textViewAmounts.setText("محتوی غیر قابل نمایش"+"\n");


                TubelessException sException = new TubelessException(-10);
                sException.handleServerMessage(mContext,((ServerResponseBase) response));
            }else if (((TimelineItemResponse)response).getTubelessException().getCode() == -11){
                buttonCharge.setEnabled(true);
                buttonCharge.setVisibility(View.VISIBLE);
                buttonMessages.setVisibility(View.GONE);
                buttonMessages.setEnabled(false);

//                    textViewCommentTitle.setVisibility(View.GONE);
//                    textViewText.setVisibility(View.GONE);

                textViewText.setText("محتوی غیر قابل نمایش");
                textViewText.setVisibility(View.VISIBLE);

                textViewAmountTitle.setVisibility(View.VISIBLE);
                textViewAmounts.setVisibility(View.VISIBLE);
                textViewAmounts.setText("محتوی غیر قابل نمایش / پول آگهی دهنده تموم شده"+"\n");


                TubelessException sException = new TubelessException(-11);
                sException.handleServerMessage(mContext,((ServerResponseBase) response));
            }
        }

        private void initCreator(UserItem creator) {

            StringBuilder desc = new StringBuilder();
            desc.append(creator.getName());
            desc.append(" ");
            desc.append(creator.getFamily());
            desc.append(" ");
            desc.append("(");
            desc.append(getString(R.string.userCode));
            desc.append(":");
            desc.append(creator.getUserCode());

            if(!creator.isUserIsActive()) {
                desc.append("-");
                desc.append(getString(R.string.inactiveUser));
            }
            desc.append(")");
            desc.append("\n");
            desc.append(getString(R.string.emailAddress));
            desc.append(":");
            desc.append(creator.getEmail());
            desc.append("\n");
            desc.append(getString(R.string.mobile_number));
            desc.append(":");
            desc.append(creator.getMobile());
            desc.append("(");
            if (creator.isMobileNumberConfirmed())
                desc.append(getString(R.string.conformed));
            else
                desc.append(getString(R.string.notConformed));
            desc.append(")");
            textViewCreator.setText(desc.toString());
            textViewElectedAmlak.setText(getString(R.string.electedAmlaksText));
            RoundedCornersTransformation transformation = new RoundedCornersTransformation(50000, 0);
            if (creator.getAvatar() != null && creator.getAvatar().length() < 5){
                Picasso.get()
                        .load(R.drawable.png_user)
                        .transform(transformation)
                        .into(user_profile_photo);
            }else {
                Picasso.get()
                        .load(creator.getAvatar())
                        .placeholder(R.drawable.png_user)
                        //.centerInside()
                        .transform(transformation)
                        .into(user_profile_photo, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(R.drawable.png_user)
                                        .transform(transformation)
                                        .into(user_profile_photo);
                            }
                        });
            }
        }
    };

    int MESSAGE_CODE = 12;
    public void sendSMS(Context mContext,String number) {
        try {
            //1 sms all
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(
                    number,
                    null,
//                    context.getString(R.string.smsText) ,
                    String.format("%s\n%s\n%s",mContext.getString(R.string.smsText3),"ارسال توسط اپلیکیشن:",mContext.getString(R.string.app_name)) ,
                    null,
                    null);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.hide();
                    finish();
                }
            }, 5000);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
            progressDialog.hide();
        }
    }

}


