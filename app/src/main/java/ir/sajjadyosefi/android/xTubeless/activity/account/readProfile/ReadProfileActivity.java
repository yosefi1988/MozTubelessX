package ir.sajjadyosefi.android.xTubeless.activity.account.readProfile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
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
import ir.sajjadyosefi.android.xTubeless.classes.model.response.electedUser.ElectedUserResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.ElectedUserItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.UserItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.RoundedCornersTransformation;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MYPOSTS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.READ_BLOG_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.classes.model.file.File.MAP_1;

/**
 * Created by sajjad on 2/11/2018.
 */

public class ReadProfileActivity extends TubelessTransparentStatusBarActivity {

    Context mContext = null;
    UserItem userItem = null;
    public static int CALL_AGAIN = 45;
    TextView textViewTitle,user_wallet,textViewCommiunicate, ueditTextNameUserId,textViewStateName,user_profile_name, textViewDescription, textViewDate;
    Button buttonMessages, buttonBack, buttonreport,buttonPosts;
    RatingBar ratingBarStar;

    //@BindView(R.id.header_cover_image)
    ImageView headerProfileImage;
    //@BindView(R.id.user_profile_photo)
    ImageButton userAvatarPhoto;
    //@BindView(R.id.user_mobile_confirmed)
    ImageButton checkMark;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_profile);
        mContext = getContext();

        buttonMessages = findViewById(R.id.buttonMessages);
        buttonreport = findViewById(R.id.buttonreport);
        buttonPosts = findViewById(R.id.buttonPosts);
        buttonBack = findViewById(R.id.buttonBack);

        ratingBarStar           = (RatingBar) findViewById(R.id.ratingBarStar);

        headerProfileImage      = findViewById(R.id.header_cover_image);
        userAvatarPhoto         = findViewById(R.id.user_profile_photo);
        checkMark               = findViewById(R.id.user_mobile_confirmed);

        user_wallet             = findViewById(R.id.user_wallet);
        textViewStateName       = findViewById(R.id.textViewStateName);
        user_profile_name       = findViewById(R.id.user_profile_name);
        ueditTextNameUserId     = findViewById(R.id.ueditTextNameUserId);
        textViewDescription     = findViewById(R.id.textViewDescription);
        textViewTitle           = findViewById(R.id.textViewTitle);
        textViewDate            = findViewById(R.id.textViewDate);
        textViewCommiunicate    = findViewById(R.id.textViewCommiunicate);

        Gson gson = new Gson();

        //new transfer
        Bundle intent = getIntent().getExtras();
        String objectString = intent.getString("Object");
        String type = intent.getString("Type");

        if (type.equals("electedUserItem")) {
            userItem = gson.fromJson(objectString, ElectedUserItem.class);
            firstFillData((ElectedUserItem)userItem);
        } else if (type.equals("NewTimelineItem")) {
        } else if (type.equals("MainPageNews")) { }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    RoundedCornersTransformation transformation = new RoundedCornersTransformation(50000, 0);

    @SuppressLint("SetTextI18n")
    private void firstFillData(ElectedUserItem electedUserItem) {
        DateConverterSjd dateUtiliti = new DateConverterSjd();
//        textViewLocation.setText(newTimelineItem.getTextFromJson());

        //1
        textViewStateName.setText(String.format("(%s: %s)",getString(R.string.state2), electedUserItem.getStateName()));
        user_profile_name.setText(electedUserItem.getName() + "" + electedUserItem.getFamily());
        textViewTitle.setText(electedUserItem.getTitle());
        ratingBarStar.setProgress(electedUserItem.getStar());

        //2
        if(electedUserItem.getMobile() == null) {
            textViewDescription.setText("در حال دریافت اطلاعات...");
            ueditTextNameUserId.setText("در حال دریافت اطلاعات...");
            textViewCommiunicate.setText("در حال دریافت اطلاعات...");
            textViewDate.setText("در حال دریافت اطلاعات...");
            user_wallet.setText("در حال دریافت اطلاعات...");
            user_wallet.setText("در حال دریافت اطلاعات...");
            user_wallet.setText("در حال دریافت اطلاعات...");
        }

        if (electedUserItem.getAvatar() != null && electedUserItem.getAvatar().length() < 5){
            Picasso.get()
                    .load(R.drawable.png_user)
                    .transform(transformation)
                    .into(userAvatarPhoto);
        }else {
            Picasso.get()
                    .load(electedUserItem.getAvatar())
                    .placeholder(R.drawable.bg_search)
                    //.centerInside()
                    .transform(transformation)
                    .into(userAvatarPhoto, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.png_user)
                                    .transform(transformation)
                                    .into(userAvatarPhoto);
                        }
                    });
        }
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

    private void fillClicks(ElectedUserItem electedUserItem) {

//        newTimelineItem.getCreatorFullName();
//        newTimelineItem.getAmount();
//        newTimelineItem.isDeleted();
//        newTimelineItem.isActive();

        View.OnClickListener onReportClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (electedUserItem instanceof UserItem) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CONTENT_REPORT);
                    bundle.putString(ContactUsActivity.ID, electedUserItem.getID() + "");
                    bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                            getContext().getString(R.string.reportContent),
                            ((ElectedUserItem) electedUserItem).getTitle()));
                    bundle.putString(ContactUsActivity.Phone,  String.format("BID=%s-BUID=%s-UID=%s",
                            ((ElectedUserItem) electedUserItem).getIDApplication(),
                            ((ElectedUserItem) electedUserItem).getUserId(),
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
//                    shareNew(mContext,0,timelineItem);
//                    share(mContext,0,timelineItem);
                if (electedUserItem instanceof ElectedUserItem) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , FRAGMENT_MYPOSTS);
                    bundle.putInt("CAT_COUNT", 10);
                    bundle.putString("AMLAK_USERCODE", electedUserItem.getUserCode());
                    getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));
                }
            }
        };
        buttonPosts.setOnClickListener(onClickListener);

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
//                    if (timelineItem instanceof MainItem) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type" , FRAGMENT_COMMENTS);
////                    bundle.putInt("blogId" , timelineItem.getBlogID());
//                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
//                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
//                }else {
//
//                }
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        TimelineItemRequest req = new TimelineItemRequest();
        req.setID(String.valueOf(((ElectedUserItem)userItem).getUserId()));
        req.setIDApplication("5");

        if (Global.user2 != null)
            Global.apiManagerTubeless.getElectedUserByUserCode(req, callbackss);
        else {
            finish();
        }
    }

    private void fillData(ElectedUserItem electedUserItem) {

        textViewDescription.setText(electedUserItem.getComment());
        ueditTextNameUserId.setText(electedUserItem.getUserCode());
        textViewCommiunicate.setText(
                getString(R.string.mobile_number) + " : " + electedUserItem.getMobile() + "\n" +
                        getString(R.string.emailAddress) + " : " + electedUserItem.getEmail() + "\n"+
                getString(R.string.telephone) + " : " + electedUserItem.getTel() + "\n"+
                getString(R.string.Address) + " : " + electedUserItem.getAddress() + "\n"
        );

        user_wallet.setText(String.format(getString(R.string.CountOfPosts),String.valueOf(electedUserItem.getCountPosts())));


        if (electedUserItem.isMobileNumberConfirmed()) {
//                Drawable tempImage = getResources().getDrawable(R.drawable.png_check);
//                checkMark.setImageDrawable(tempImage);
            Picasso.get()
                    .load(R.drawable.png_check)
                    .into(checkMark);
        }

        if (electedUserItem.getProfileImage() != null && electedUserItem.getProfileImage().length() < 5){
            Picasso.get()
                    .load(R.drawable.jpg_paeez)
                    .into(headerProfileImage);
        }else {
            Picasso.get()
                    .load(electedUserItem.getProfileImage())
                    .placeholder(R.drawable.jpg_paeez)
                    //.centerInside()
                    .into(headerProfileImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.jpg_paeez)
                                    .into(headerProfileImage);
                        }
                    });
        }
        buttonreport.setEnabled(true);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("عضویت : ");
        stringBuilder.append(electedUserItem.getUserCreateDate());
        stringBuilder.append("\n");
        stringBuilder.append("شروع همکاری : ");
        stringBuilder.append(electedUserItem.getElectedDate());
        textViewDate.setText(stringBuilder.toString());

        fillClicks(electedUserItem);

    }

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
//                        ((TextItem) blogItem).setFav(!((TextItem) blogItem).isFav());
//                        if (((TextItem) blogItem).isFav()) {
//                            Drawable tempImage = getResources().getDrawable(R.drawable.png_star);
//                            buttonFav.setImageDrawable(tempImage);
//                        } else {
//                            Drawable tempImage = getResources().getDrawable(R.drawable.png_star_off);
//                            buttonFav.setImageDrawable(tempImage);
//                        }
//                        ListFragment.fragment.favMethod(((MainItem) blogItem).getID(), ((TextItem) blogItem).isFav());
                    }
                    if (type == DO_ACCEPT) {

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

    TubelessRetrofitCallbackss callbackss = new TubelessRetrofitCallbackss(this, ElectedUserResponse.class) {
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
                JsonObject jsonObject2 = gson.toJsonTree(response).getAsJsonObject();
                ElectedUserResponse electedUserResponse = gson.fromJson(jsonObject2,  ElectedUserResponse.class);
                userItem = (UserItem) electedUserResponse.getElectedUserList().get(0);
                fillData((ElectedUserItem) userItem);


//                ListFragment.fragment.seenMethod(((MainItem)blogItem).getID());
            }else if (((TimelineItemResponse)response).getTubelessException().getCode() == -10){
//                buttonCharge.setEnabled(true);
//                buttonCharge.setVisibility(View.VISIBLE);
//                buttonMessages.setVisibility(View.GONE);
//                buttonMessages.setEnabled(false);
//
////                    textViewCommentTitle.setVisibility(View.GONE);
////                    textViewText.setVisibility(View.GONE);
//
//                textViewText.setText("محتوی غیر قابل نمایش"+"\n");
//                textViewText.setVisibility(View.VISIBLE);
//
//                textViewAmountTitle.setVisibility(View.VISIBLE);
//                textViewAmounts.setVisibility(View.VISIBLE);
//                textViewAmounts.setText("محتوی غیر قابل نمایش"+"\n");
//
//
//                TubelessException sException = new TubelessException(-10);
//                sException.handleServerMessage(mContext,((ServerResponseBase) response));
            }
        }
    };
}


