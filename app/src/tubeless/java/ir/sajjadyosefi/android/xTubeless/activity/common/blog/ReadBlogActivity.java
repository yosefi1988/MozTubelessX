package ir.sajjadyosefi.android.xTubeless.activity.common.blog;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.Adapter.ViewPagerAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.PrePaymentActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.lottery.activity.SearchLotteryActivity;
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

public class ReadBlogActivity extends TubelessTransparentStatusBarActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//          قسمت بالا و پایین به صورت کامل ترنسپرنت می شود
//        هیچ سایه ای نداره
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_read_blog);
        mContext = getContext();

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        user_profile_photo = findViewById(R.id.user_profile_photo);
        //mPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewText = findViewById(R.id.textViewText);
        textViewElectedAmlak = findViewById(R.id.textViewElectedAmlak);
        buttonFav = findViewById(R.id.buttonFav);
        buttonInvisible = findViewById(R.id.buttonInvisible);
        buttonAccept = findViewById(R.id.buttonAccept);
        buttonBack = findViewById(R.id.buttonBack);
        buttonCharge = findViewById(R.id.buttonCharge);
        buttonElectedAmlak = findViewById(R.id.buttonElectedAmlak);
        buttonreport = findViewById(R.id.buttonreport);

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

        buttonCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PrePaymentActivity.class);
                ((Activity) mContext).startActivityForResult(intent, CALL_AGAIN);
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
    }


    //    public static void fillTitle(Context context ,TimelineNewItem blog. TextView textViewTitle) {
    private void fillTitle(Context context, TextItem mainItem, TextView textViewTitle) {
        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(mainItem.getTitle());
        textViewTitle.setText(stringBuilderTitle.toString());
    }


    public static String fillTitleForShare(Context context, String title, int cat) {
        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(title);
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


    TextView textViewTitle, textViewDescription, textViewText, textViewElectedAmlak;
    Button buttonInvisible, buttonAccept, buttonBack, buttonCharge,buttonElectedAmlak, buttonreport;
    ImageButton buttonFav;
    ImageView user_profile_photo, imageView, imageView2;

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
            textViewElectedAmlak.setText("در حال دریافت اطلاعات...");
            //textViewText.setVisibility(View.GONE);
//            textViewText.setText("\n");
        } else
            textViewText.setText(mainItem.getText());

        textViewDescription.setText("");

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
        imageViewTitle(mainItem);
    }

    private void imageViewTitle(TextItem mainItem) {
//        //image title
        if (mainItem.getTitlePicture() == null) {
            imageView.setVisibility(View.GONE);
        }else {
            imageView.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(mainItem.getTitlePicture())
                    //.noPlaceholder()
                    .placeholder(R.drawable.png_image)
                    .error(R.drawable.png_image)
//                    .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
//                    .centerCrop()
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.png_image)
                                    //.transform(transformation)
                                    .into(imageView);
                        }
                    });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(getContext(), zoomImageActivity.class);
//                    intent.putExtra("image" , newTimelineItem.getTitlePicture());
//                    intent.putExtra("text" , newTimelineItem.getTextFromJson());
//                    getContext().startActivity(intent);
//                    overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                }
            });
        }
    }

    private void firstFillDataNews(TimelineItem newTimelineItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();

        if (newTimelineItem.getCategoryID() == StaticValue.newsCategory){
//            viewHeader.setVisibility(View.GONE);
        }else {
//            viewHeader.setVisibility(View.VISIBLE);
        }


        if (newTimelineItem.getPicture() != null && newTimelineItem.getPicture().length() > 10) {
        }
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
//        textViewLocation.setText(timelineItem.getLocation())
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
                getActivity().startActivity(new Intent(getContext(), SearchLotteryActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        TimelineItemRequest req = new TimelineItemRequest();
        req.setIDPost(((MainItem)blogItem).getID() + "");

        if (Global.user2 != null)
            Global.apiManagerTubeless.getTimelineItem(req, callbackss);
        else {
            finish();
        }
    }

    private void fillData(TextItem timelineItem) {
        textViewDescription.setText("");

        if (timelineItem.getText() == null) {
            textViewText.setVisibility(View.GONE);
        }else {
            textViewText.setText(timelineItem.getText());
            textViewText.setVisibility(View.VISIBLE);

        }

        if (Global.user2.isUserAdmin()){
            buttonAccept.setVisibility(View.VISIBLE);
            buttonInvisible.setVisibility(View.VISIBLE);
        }

        if (timelineItem.isReciveMessage()){
            buttonCharge.setVisibility(View.GONE);
        }else {
            buttonCharge.setVisibility(View.GONE);
        }
        buttonreport.setEnabled(true);
        //images
        if (timelineItem.getImages() == null || (timelineItem.getImages().size() == 0)){

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
        }
    }
    private List<File> fileList ;
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
                        progressDialog.show();
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

    TubelessRetrofitCallbackss callbackss = new TubelessRetrofitCallbackss(this, TimelineItemResponse.class) {
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
                initSlideShow(fileList,textItem);

                if (Global.user2.getWallet() != null)
                    Global.user2.getWallet().setAmount(((TimelineItemResponse) response).getWallet().getAmount());
                
                 Wallet wallet = new Wallet();
                AWallet aWallet = wallet.loadWalletData();
                wallet.updateWalletAmount(((TimelineItemResponse) response).getWallet().getAmount());


                aWallet = wallet.loadWalletData();

                ListFragment.fragment.seenMethod(((MainItem)blogItem).getID());
                blogCreator = itemResponse.creator;
                initCreator(itemResponse.creator);
            }else if (((TimelineItemResponse)response).getTubelessException().getCode() == -10){
                buttonCharge.setEnabled(true);
                buttonCharge.setVisibility(View.VISIBLE);
//                    textViewText.setVisibility(View.GONE);

                textViewText.setText("محتوی غیر قابل نمایش");
                textViewText.setVisibility(View.VISIBLE);
                TubelessException sException = new TubelessException(-10);
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
    private void initSlideShow(List<File> files, TextItem textItem) {
        imageViewTitle(textItem);


        if (textItem.getTextPicture() == null) {
            imageView2.setVisibility(View.GONE);
        }else {
            imageView2.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(textItem.getTextPicture())
                    //.noPlaceholder()
                    .placeholder(R.drawable.png_image)
                    .error(R.drawable.png_image)
//                    .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
//                    .centerCrop()
                    .into(imageView2, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.png_image)
                                    //.transform(transformation)
                                    .into(imageView2);
                        }
                    });

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), zoomImageActivity.class);
                    intent.putExtra("image" , textItem.getTextPicture());
                    //intent.putExtra("text" , textItem.getTextFromJson());
                    getContext().startActivity(intent);
                    overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                }
            });
        }

    }

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


