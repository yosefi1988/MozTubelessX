package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.BlankFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.TwoLevelListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.FilterFragment;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.CATEGORY_ID;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FirstFragmentsAdapter extends FragmentStatePagerAdapter  {
    Context context;
    FragmentManager supportFragmentManager;

    int PAGE_COUNT;
    private String mTabTitles[] = new String[] {
            "\uE802",
            "\uE801",
            "\uE804",
            "\uE800",
            "\uE803"};


    public static int ITEM_TYPE_IMAGE_ITEM = 112;


    public static int TYPE_TUBELESS_NEWS  = 7;
    public static int TYPE_BOURSE_NEWS = 26;
    public static int TYPE_BOURSE_ANALIZE_All = 25;
    public static int TYPE_BOURSE_ANALIZE_Old = 27;

    public static int TYPE_ITEM_MESSAGE = 36;
    public static int TYPE_ITEM_MESSAGE2 = 39;
    public static int TYPE_ITEM_Message_FROM_USERS = 37;
    public static int TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm = 38;
    public static int TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm2 = 40;


    //Main Adapter
    //---------------New Category----------------------//
    public static int LIST_TYPE_AMLAK_TIMELINE = 23;
    public static int FRAGMENTLIST_TYPE_BUSINESSES_TIMELINE = 232;
    public static int FRAGMENTLIST_TYPE_MOZ_TIMELINE = 2;
    public static int ITEM_TYPE_MYFAVS = 34;
    public static int ITEM_TYPE_MYPURCHESE = 3;                 //خرید های من - مشاهده شده ها
    public static int LIST_TYPE_MYPOSTS = 33;
    public static int LIST_TYPE_AMLAK_FILTER = 28;
    public static int LIST_CATEGORY_ONE_SELECT = 102;
    public static int LIST_CATEGORY_MULTY_SELECT = 104;
    public static int LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS = 103;
    public static int LIST_CATEGORY_ONE_SELECT_FOR_READ_BLOGS = 105;

    public static int TYPE_LIST_CATEGORIES_DATA = 103;
    public static int LIST_TYPE_IMAGE_IN_YADAK = 1;
    public static int FRAGMENTLIST_TYPE_BOURSE_TRAIN = 24;

    //-------------------------------------------------//
    public static int FRAGMENTLIST_TYPE_MOZ_CREATORS_POST = 922;

    //Users Adapter
    public static int ITEM_TYPE_AMLAK_LIST_1 = 63;             //املاک مورد اعتماد

    //Transactions Adapter
    public static int ITEM_TYPE_TRANS = 35;
    public static int ITEM_TYPE_FILTER = 72;
    public static int FRAGMENTLIST_YADAK_TIMELINE = 22;




    ////////////////// Nerkh Rox  ///////////////////////
    public static int TYPE_NERKHROZ_BOURSE = 201;
    /////////////////////////////////////////////////////
    public final class CalendarPageListener implements CalendarPageFragmentListener {
        public void onSwitchToNextFragment(long category,long parentId,String title,int selectType) {
            supportFragmentManager.beginTransaction().remove(fragmentx1).commit();
            if (fragmentx1 instanceof ListFragment){
                Bundle bundle = new Bundle();
//                        bundle.putInt("type" , TYPE_SELECT_CATEGORY);
                bundle.putInt("type" , FRAGMENT_CATEGORY);
                bundle.putInt("CAT_COUNT", 11);
                bundle.putString("item1","value1");
                bundle.putString("title",title);
                bundle.putLong("category",category);
                bundle.putLong("parentId",parentId);
                bundle.putInt("selectType",selectType);
                Intent intent = new Intent(context,ContainerActivity.class);
                intent.putExtras(bundle);
                fragmentx1 = new ListFragment(context, calendarPageListenerObject, LIST_CATEGORY_ONE_SELECT, intent);
            }else{ // Instance of NextFragment

                Bundle bundle = new Bundle();
//                        bundle.putInt("type" , TYPE_SELECT_CATEGORY);
                bundle.putInt("type" , FRAGMENT_CATEGORY);
                bundle.putInt("CAT_COUNT", 1);
                bundle.putString("item1","value1");
                bundle.putLong("category",category);
//                bundle.putLong("parentId",parentId);
//                bundle.putInt("selectType",selectType);
                bundle.putString("title",title);
                Intent intent = new Intent(context,ContainerActivity.class);
                intent.putExtras(bundle);
                fragmentx1 = new ListFragment(context, calendarPageListenerObject, LIST_CATEGORY_ONE_SELECT, intent);
            }
            notifyDataSetChanged();
        }
    }
    CalendarPageListener calendarPageListenerObject = new CalendarPageListener();




    public FirstFragmentsAdapter(Context context, ViewPager viewPager, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.supportFragmentManager = supportFragmentManager;
        this.context = context ;
        setCount();
    }

    public FirstFragmentsAdapter(final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        setCount();
    }

    public FirstFragmentsAdapter(Context context  ,final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        this.supportFragmentManager = activity.getSupportFragmentManager();
        this.context = context;
        setCount();
    }

    private void setCount() {
        if (BuildConfig.FLAVOR_version_name.equals("winner")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
            PAGE_COUNT = 3;
        }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("bourse")){
            PAGE_COUNT = 4;
        }else if (BuildConfig.FLAVOR_version_name.equals("moz")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("amlak")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("estekhdam")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")){
            PAGE_COUNT = 3;
        }else if (BuildConfig.FLAVOR_version_name.equals("businesses")){
            PAGE_COUNT = 2;
        }else {
            PAGE_COUNT = 2;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
//        return super.getItemPosition(object);
        //return POSITION_NONE;

        if (object instanceof ListFragment && fragmentx1 instanceof ListFragment)
            return POSITION_NONE;
        return POSITION_UNCHANGED;
    }

    public void notifyList() {
//        if (BuildConfig.FLAVOR_version_name.equals("bourse")){
//            boolean isVip = true;
//            if (isVip){
//                fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_All);
//            }
//        }else {
            if (fragmentx1 instanceof ListFragment)
                ((ListFragment) fragmentx1).refreshForAdmin();

            if (fragmentx2 instanceof ListFragment)
                ((ListFragment) fragmentx2).refreshForAdmin();

            if (fragmentx3 instanceof ListFragment)
                ((ListFragment) fragmentx3).refreshForAdmin();
//        }
    }


    public static Fragment fragmentx1;
    public static Fragment fragmentx2;
    Fragment fragmentx3;
    Fragment fragmentx4;

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (fragmentx1 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("winner")) {
                        //fragmentx1 = new ListFragment(context, ITEM_TYPE_MYPURCHESE);

                        //ContainerActivity > ListFragment > CategoryAdapter
                        Bundle bundle = new Bundle();
//                        bundle.putInt("type" , TYPE_SELECT_CATEGORY);
                        bundle.putInt("type" , FRAGMENT_CATEGORY);
                        bundle.putInt("CAT_COUNT", 1);
                        bundle.putLong("category",CATEGORY_ID);
                        bundle.putLong("parentId",0);
                        bundle.putInt("selectType" , LIST_CATEGORY_MULTY_SELECT);
                        bundle.putInt("selectType" , LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS);
                        bundle.putInt("selectType" , LIST_CATEGORY_ONE_SELECT);
                        bundle.putString("item1","value1");
                        Intent intent = new Intent(context,ContainerActivity.class);
                        intent.putExtras(bundle);
                        fragmentx1 = new ListFragment(context, calendarPageListenerObject, LIST_CATEGORY_ONE_SELECT, intent);
//////////////////////////////////////////////////////////////////////////////////////////////

                    } else if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
                        //1
//                        fragmentx1 = new SearchByNameFragment();


                        //2
                        TimelineRequest searchRequest = new TimelineRequest("", 0);
                        searchRequest.setTtc(null);
                        searchRequest.setPageSize("10");
                        searchRequest.setPageIndex("0");
                        searchRequest.setActive(true);

                        if (Global.user2 != null) {
                            searchRequest.setUserCode(Global.user2.getUserCodeAsString());
                        } else {
                            searchRequest.setUserCode(null);
                        }
                        fragmentx1 = new FilterFragment(searchRequest);


                        //3
                        //fragmentx1 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);

                    } else if (BuildConfig.FLAVOR_version_name.equals("amlak")) {
                        fragmentx1 =  new FilterFragment(null);
//                        fragmentx1 = new ListFragment(context, ITEM_TYPE_AMLAK_LIST_1);

                    } else if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
                        if (fragmentx1 == null) {
                        fragmentx1 = new ListFragment(context, FRAGMENTLIST_YADAK_TIMELINE);
//                        List<IItems> iItems = new ArrayList<>();
//                        CategoryFiltersNode newNode = createRootNode();
//                        categoryFiltersNodeList.AddLast(newNode);
//                        fragmentx1 = new ListFragment(context,iItems, TYPE_LIST_CATEGORY, categoryFiltersNodeList.getHead());
//////////////////////////////////////////////////////////////////////////////////////////////


                        }
                    } else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")) {

                        TimelineRequest searchRequest = new TimelineRequest("", 0);
                        searchRequest.setTtc(null);
                        searchRequest.setPageSize("10");
                        searchRequest.setPageIndex("0");
                        searchRequest.setActive(true);

                        if (Global.user2 != null) {
                            searchRequest.setUserCode(Global.user2.getUserCodeAsString());
                        } else {
                            searchRequest.setUserCode(null);
                        }
                        fragmentx1 = new FilterFragment(searchRequest);
                    } else if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
                        //fragmentx1 = new ListFragment(context, FRAGMENTLIST_TYPE_BOURSE_TRAIN);//FRAGMENTLIST_TYPE_MOZ_TIMELINE
                        fragmentx1  =  new BlankFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("businesses")) {
                        fragmentx1 =  new FilterFragment(null);
                    } else if (BuildConfig.FLAVOR_version_name.equals("moz")) {
                        fragmentx1 =  new FilterFragment(null);
                    } else if (BuildConfig.FLAVOR_version_name.equals("estekhdam")) {
//                        fragmentx1 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);
                        fragmentx1 =  new FilterFragment(null);
//                        fragmentx1 =  new BlankFragment();

                    } else {
                        fragmentx1 = new ListFragment(context, ITEM_TYPE_MYPURCHESE);
                    }
                }
                return fragmentx1;

            case 1:
                if (fragmentx2 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("winner")) {
                        fragmentx2 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
                        fragmentx2 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("amlak")) {
                        fragmentx2 = new ListFragment(context, LIST_TYPE_AMLAK_TIMELINE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
                        fragmentx2 = new ListFragment(context, LIST_TYPE_IMAGE_IN_YADAK);
                    } else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")) {
                        fragmentx2 = new ListFragment(context, ITEM_TYPE_MYPURCHESE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
                        //fragmentx2 = new ListFragment(context, TYPE_BOURSE_NEWS);
                        fragmentx2 = new BlankFragment(context);
                    } else if (BuildConfig.FLAVOR_version_name.equals("businesses")) {
                        TimelineRequest searchRequest = new TimelineRequest("",0);
                        searchRequest.setTtc("9138");
                        searchRequest.setPageSize("10");
                        searchRequest.setPageIndex("0");
                        searchRequest.setActive(true);
                        if(Global.user2 != null){
                            searchRequest.setUserCode(Global.user2.getUserCodeAsString());
                        }else {
                            searchRequest.setUserCode(null);
                        }

                        fragmentx2 = new ListFragment(context, FRAGMENTLIST_TYPE_BUSINESSES_TIMELINE, searchRequest);
                    } else if (BuildConfig.FLAVOR_version_name.equals("estekhdam")) {
                        fragmentx2 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);
//                        fragmentx2 = new BlankFragment(context);
                    } else if (BuildConfig.FLAVOR_version_name.equals("moz")) {
////                        fragmentx2 = new BlankFragment(supportFragmentManager,this);
//                        List<IItems> iItems = new ArrayList<>();
//                        CategoryFiltersNode newNode = createRootNode();
//                        categoryFiltersNodeList.AddLast(newNode);
//                        fragmentx2 = new ListFragment(context,iItems, LIST_CATEGORY_ONE_SELECT, categoryFiltersNodeList.getHead());
////                        fragmentx2 = new ListFragment(context, TYPE_YADAK);

                        fragmentx2 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);

                    } else {
                        fragmentx2 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);
                    }
                }
                return fragmentx2;

            case 2:
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                fragment = mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );
//
//                FragmentYafteha fragmentNotifications = new FragmentYafteha();
//                fragment = fragmentNotifications.newInstance(context,position,LIST_TIMELINE );

                if (fragmentx3 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("winner")) {
                        fragmentx3 = new BlankFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
                        TimelineRequest searchRequest = new TimelineRequest("",0);
                        searchRequest.setTtc("5051");
                        searchRequest.setPageSize("10");
                        searchRequest.setPageIndex("0");
                        searchRequest.setActive(true);
                        if(Global.user2 != null){
                            searchRequest.setUserCode(Global.user2.getUserCodeAsString());
                        }else {
                            searchRequest.setUserCode(null);
                        }

                        fragmentx3 = new ListFragment(context, ITEM_TYPE_FILTER, searchRequest);

                    } else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")) {
                        fragmentx3 = new ListFragment(context, FRAGMENTLIST_TYPE_MOZ_TIMELINE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yadak")) {

                        //Fragment CATEGORY
                        Bundle bundle = new Bundle();
//                        bundle.putInt("type" , TYPE_SELECT_CATEGORY);
                        bundle.putInt("type" , FRAGMENT_CATEGORY);
                        bundle.putInt("CAT_COUNT", 1);
                        bundle.putString("item1","value1");
                        Intent intent = new Intent(context,ContainerActivity.class);
                        intent.putExtras(bundle);
                        fragmentx3 = new ListFragment(context, intent);

                    } else if (BuildConfig.FLAVOR_version_name.equals("estekhdam")) {

//                        fragmentx3 =  new FilterFragment(null);
                        fragmentx3 =  new BlankFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
//                        if (!isFreeStore(context, StaticValue.configuration)) {
//                            fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_All);
//                        } else {
//                            if (StaticValue.bourseState != null && StaticValue.bourseState.totalPayedValue > 0) {
//                                if (BourseState.CheckDateIsValid(StaticValue.bourseState.endDate, StaticValue.configuration.getData()..getDate())) {
//                                    fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_All);
//                                } else {
//                                    fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_Old);
//                                }
//                            } else {
//                                //هیچ پرداختی قبلا انجام نداده است
//                                fragmentx3 = new FinancialAccountLimitFragment(context);
//                            }
//                        }
                        fragmentx3 =  new BlankFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("moz")) {
                        fragmentx3 = new ListFragment(context, ITEM_TYPE_MYPURCHESE);
                    } else {
                        fragmentx3 = new BlankFragment();
                    }
                }
                return fragmentx3;

            case 3:
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                fragment = mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );
//
//                FragmentYafteha fragmentNotifications = new FragmentYafteha();
//                fragment = fragmentNotifications.newInstance(context,position,LIST_TIMELINE );

                if (fragmentx4 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
//                        fragmentx4 = new BourseDataFragment(context);
                        fragmentx4 = new TwoLevelListFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("winner")) {
                        fragmentx4 = new BlankFragment();
                    } else {
                        fragmentx4 = new BlankFragment();
                    }
                }
                return fragmentx4;
        }
        return null;
    }

//    public static CategoryItem createRootNode() {
//        //todo hard code
//        CategoryItem newNode = new CategoryItem();
////        newNode.setCatId(10021);
//        newNode.setDescription("10021");
//        newNode.setPathTitle("\\" + "10021");
//        newNode.setTitle("10021");
//        return newNode;
//    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return mTabTitles[position];
    }


    public void kartesokhtPayComplete(ViewGroup rootView) {
        if (fragmentx1 != null)
            if (fragmentx1 instanceof KartesekhtFragment){
                ((KartesekhtFragment)fragmentx1).callService(rootView);
            }

    }
    public interface CalendarPageFragmentListener {
        void onSwitchToNextFragment(long category,long parentId,String title,int selectType);
//        void onSwitchToPrevFragment(long category,long parentId,String title);
    }
}

