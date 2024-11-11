package ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post;

import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;


public class PostSearchResponseItem extends TubelessObject {

    private String id;
    private int fkFindedType;
    private String findDate;
    private String n;
    private String fName;
    private String lName;
    private String fatherName;
    private String identityNumber;
    private String nationalCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFkFindedType() {
        return fkFindedType;
    }

    public void setFkFindedType(int fkFindedType) {
        this.fkFindedType = fkFindedType;
    }

    public String getFindDate() {
        return findDate;
    }

    public void setFindDate(String findDate) {
        this.findDate = findDate;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

//    public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position) {
//        PostItemViewHolder holder = (PostItemViewHolder) holder0;
//        final PostSearchResponseItem timelineItem = (PostSearchResponseItem)item;
//
//        holder.imageViewMenu.setVisibility(View.GONE);
//
//        StringBuilder date = new StringBuilder();
////        date.append(" ( ");
//        date.append("تاریخ پیدا شدن : ");
//        date.append(timelineItem.getFindDate());
////        date.append(" ) ");
//        holder.textViewDate.setText(date.toString());
//
//        StringBuilder partType = new StringBuilder();
//        partType.append("نوع :");
//        partType.append(timelineItem.getN());
//        partType.append(" ");
//        partType.append("کد شناسایی :");
//        partType.append(timelineItem.getNationalCode());
//        holder.textViewTitle.setText(partType);
//
//        holder.textViewLocation.setText("متعلق به " + timelineItem.fName + " " + timelineItem.lName + " فرزند " + timelineItem.getFatherName());
////        holder.textViewUserName.setText(timelineItem.getUserName());
////        holder.textViewCount.setText(timelineItem.getViewCount() + "");
//
//        holder.textViewUserName.setText("اپراتور شرکت پست");
////        holder.textViewCount.setText(timelineItem.getNationalCode()+ "");
//
//        View.OnClickListener onShareClickListenr = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StringBuilder stringBuilder0 = new StringBuilder();
//
//                stringBuilder0.append(timelineItem.n);
//                stringBuilder0.append("\n");
//
////                stringBuilder0.append(timelineItem.getTitle());
////                stringBuilder0.append("-");
//                stringBuilder0.append("توضیحات:");
//                stringBuilder0.append("\n");
//                stringBuilder0.append(timelineItem.n);
//                stringBuilder0.append("-");
//                stringBuilder0.append("متعلق به " + timelineItem.fName + " " + timelineItem.lName + " فرزند " + timelineItem.getFatherName());
//
//                stringBuilder0.append("\n");
//                stringBuilder0.append("تاریخ پیدا شدن: ");
//                stringBuilder0.append(timelineItem.findDate);
//
//                stringBuilder0.append("\n");
//                stringBuilder0.append("منتشر شده از اپلیکیشن مدارک گم شده");
//                stringBuilder0.append("\n");
//                if (BuildConfig.FLAVOR.equals("myket")){
//                    stringBuilder0.append("https://myket.ir/app/ir.sajjadyosefi.android.xYafte");
//                }else {
//                    stringBuilder0.append("https://cafebazaar.ir/app/ir.sajjadyosefi.android.xYafte");
//                }
//                stringBuilder0.append("\n");
//
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
//                intent.setType("text/plain");
//                ((Activity)mContext).startActivityForResult(intent , 60);
//            }
//        };
//        holder.imageViewShare.setOnClickListener(onShareClickListenr);
//        holder.textViewShare.setOnClickListener(onShareClickListenr);
//
//        if (Global.user2 == null) {
//            if (holder.linearLayoutAdmin != null)
//                holder.linearLayoutAdmin.setVisibility(View.GONE);
//        }else {
//            if (Global.userFixxxxxxxxx != null && (Global.userFixxxxxxxxx.getUserId() == 140241 || Global.userFixxxxxxxxx.getUserName().equals("yosefi1988@gmail.com")|| Global.userFixxxxxxxxx.getUserName().equals("09123678522"))) {
//                holder.linearLayoutAdmin.setVisibility(View.VISIBLE);
//            } else {
//                holder.linearLayoutAdmin.setVisibility(View.GONE);
//            }
//        }
//        LoadImages.loadProfileimage("https://tracking.post.ir/Content/Image/postarm.png",holder.imageViewUserAvatar);
//    }

}
