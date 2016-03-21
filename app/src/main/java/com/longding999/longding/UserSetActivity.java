package com.longding999.longding;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.basic.BasicCountryActivity;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DateParseUtils;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.MyApplication;
import com.makeramen.roundedimageview.RoundedImageView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/16 13:32
 * Desc: 用户设置
 * *****************************************************************
 */
public class UserSetActivity extends BasicCountryActivity implements View.OnClickListener,OnWheelChangedListener{

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;
    private LinearLayout linearLayout;

    private int _id;

    private RoundedImageView ivUserIcon;
    private TextView tvUserName,tvUserRank,tvUserPhone,tvUserQQ,tvUserLocation,tvUserBirthday,tvUserGander;
    private LinearLayout layoutUserIcon,layoutUserName,layoutUserRank,layoutUserPhone,layoutUserQQ,layoutUserLocation,layoutUserBirthday,layoutUserGander,layoutUserPwd;

    private DbManager dbManager;


    private int CHOOSE_PICTURE = 1001;

    private String  userIcon,userName,userQQ,userLocation,userPwd,userPhone;
    private int userGander,userRank;
    private long userBirthDay;

    private WheelView mWheelProvince,mWheelCity,mWheelDistrict;




    @Override
    protected void bindView() {
        setContentView(R.layout.activity_userset);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {
        Intent intent = getIntent();
        _id = intent.getIntExtra("_id",-1);
    }

    @Override
    protected void initViews() {
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("个人设置");
        tvRight.setText("保存");
        tvLeft.setVisibility(View.GONE);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        ivUserIcon = (RoundedImageView) findViewById(R.id.iv_usericon);
        tvUserBirthday = (TextView) findViewById(R.id.tv_userbirthday);
        tvUserGander = (TextView) findViewById(R.id.tv_usergander);
        tvUserPhone = (TextView) findViewById(R.id.tv_userphone);
        tvUserName = (TextView) findViewById(R.id.tv_username);
        tvUserRank = (TextView) findViewById(R.id.tv_userrank);
        tvUserQQ = (TextView) findViewById(R.id.tv_userQQ);
        tvUserLocation = (TextView) findViewById(R.id.tv_userlocation);

        layoutUserBirthday = (LinearLayout) findViewById(R.id.layout_userbirthday);
        layoutUserGander = (LinearLayout) findViewById(R.id.layout_usergander);
        layoutUserIcon = (LinearLayout) findViewById(R.id.layout_usericon);
        layoutUserLocation = (LinearLayout) findViewById(R.id.layout_userlocation);
        layoutUserName = (LinearLayout) findViewById(R.id.layout_username);
        layoutUserPhone = (LinearLayout) findViewById(R.id.layout_userphone);
        layoutUserQQ = (LinearLayout) findViewById(R.id.layout_userQQ);
        layoutUserPwd = (LinearLayout) findViewById(R.id.layout_userpwd);
        layoutUserRank = (LinearLayout) findViewById(R.id.layout_userrank);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        layoutUserRank.setOnClickListener(this);
        layoutUserPwd.setOnClickListener(this);
        layoutUserPhone.setOnClickListener(this);
        layoutUserQQ.setOnClickListener(this);
        layoutUserLocation.setOnClickListener(this);
        layoutUserBirthday.setOnClickListener(this);
        layoutUserGander.setOnClickListener(this);
        layoutUserIcon.setOnClickListener(this);
        layoutUserName.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        dbManager = DbHelper.getInstance().getDbManger();
        refreshViewById(_id);
    }

    /**
     * 根据用户Id刷新界面
     * @param _id
     */
    private void refreshViewById(int _id){
        try {
            List<UserInfo> id = dbManager.selector(UserInfo.class).where("_id", "=", _id).findAll();
            UserInfo userInfo = id.get(0);

            userIcon = userInfo.getUserIcon();
            userName = userInfo.getUserName();
            userGander = userInfo.getUserGander();
            userQQ = userInfo.getUserQQ();
            userLocation = userInfo.getUserLocation();
            userBirthDay = userInfo.getUserBirthDay();
            userRank = userInfo.getUserRank();
            userPwd = userInfo.getUserPwd();
            userPhone = userInfo.getUserPhone();

            tvUserName.setText(userName);
            tvUserRank.setText("VIP"+ userRank);
            tvUserQQ.setText(userQQ);
            tvUserPhone.setText(userInfo.getUserPhone());
            tvUserBirthday.setText(DateParseUtils.parseLongToString(userBirthDay));
            if(userGander==0){
                tvUserGander.setText("男");
            }else{
                tvUserGander.setText("女");
            }
            tvUserLocation.setText(userLocation);
            ivUserIcon.setImageBitmap(BitmapFactory.decodeFile(userIcon));

        } catch (DbException e) {
            e.printStackTrace();
        }

    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
                onBackPressed();
                break;

            case R.id.tv_right:
                saveUserInfo();
                break;

            case R.id.layout_userbirthday:
                changeBirthday();
                break;

            case R.id.layout_usergander:
                changeGrander();
                break;

            case R.id.layout_usericon:
                changeIcon();
                break;

            case R.id.layout_userlocation:
                changeLocation();
                break;

            case R.id.layout_username:
                changeUserName();
                break;

            case R.id.layout_userpwd:
                changeUserPwd();
                break;

            case R.id.layout_userQQ:
                changeUserQQ();
                break;

            case R.id.layout_userphone:
                shortToast("点击了用户手机！");
                break;

            case R.id.layout_userrank:
                shortToast("点击了用户等级！");
                break;

            default:
                break;
        }
    }

    /**
     * 保存用户修改信息
     */
    private void saveUserInfo() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("保存用户信息？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            dbManager.saveOrUpdate(new UserInfo(_id,userIcon,userName,userPwd,userBirthDay,userGander,userPhone,userLocation,userQQ,userRank));
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        setResult(2001);
                        finish();
                    }
                }).show();
    }

    /**
     * 修改用户性别
     */
    private void changeGrander() {
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setSingleChoiceItems(items,userGander,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if ("男".endsWith(items[which])){
                    userGander = 0;
                }else{
                    userGander = 1;
                }
                tvUserGander.setText(items[which]);
            }
        });
        builder.create().show();

    }

    /**
     * 修改用户qq
     */
    private void changeUserQQ() {
        View view = View.inflate(this, R.layout.dialog_edittext, null);
        final EditText dialogEdit = (EditText) view.findViewById(R.id.dialogEdit);
        new AlertDialog.Builder(this).setTitle("请输入QQ：")
                .setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userQQ = dialogEdit.getText().toString();
                        tvUserQQ.setText(userQQ);
                    }
                }).show();
    }

    /**
     * 修改用户密码
     */
    private void changeUserPwd() {

    }

    /**
     * 修改用户昵称
     */
    private void changeUserName() {
        View view = View.inflate(this, R.layout.dialog_edittext, null);
        final EditText dialogEdit = (EditText) view.findViewById(R.id.dialogEdit);
        new AlertDialog.Builder(this).setTitle("请输入昵称：")
                .setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userName = dialogEdit.getText().toString();
                        tvUserName.setText(userName);
                    }
                }).show();
    }

    /**
     * 修改用户常驻地
     */
    private void changeLocation() {
        View view = View.inflate(this, R.layout.dialog_country, null);
        mWheelProvince = (WheelView) view.findViewById(R.id.wheelProvince);
        mWheelCity = (WheelView) view.findViewById(R.id.wheelCity);
        mWheelDistrict = (WheelView) view.findViewById(R.id.wheelDistrict);
        initProvinceDatas();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择地区：");
        builder.setView(view);
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userLocation = mCurrentProviceName+mCurrentCityName+mCurrentDistrictName;
                tvUserLocation.setText(userLocation);
//                shortToast(mCurrentProviceName+mCurrentCityName+mCurrentDistrictName);
            }
        });
        builder.create().show();

        mWheelProvince.setViewAdapter(new ArrayWheelAdapter<>(this, mProvinceDatas));
        mWheelDistrict.addChangingListener(this);
        mWheelCity.addChangingListener(this);
        mWheelProvince.addChangingListener(this);
        // 设置可见条目数量
        mWheelProvince.setVisibleItems(6);
        mWheelCity.setVisibleItems(6);
        mWheelDistrict.setVisibleItems(6);
        updateCities();
        updateAreas();
    }

    /**
     * 修改用户头像
     */
    private void changeIcon() {
        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
    }

    /**
     * 修改出生日期
     */
    private void changeBirthday() {

        View view = View.inflate(this, R.layout.dialog_datepicker, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datepicker);
        datePicker.setMinDate(DateParseUtils.parseStringToLong("1900-01-01"));
        datePicker.setMaxDate(new Date().getTime());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("出生日期：");
        builder.setView(view);
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int dayOfMonth = datePicker.getDayOfMonth();
                getUserBirthDay(year,month,dayOfMonth);
            }
        });
        builder.create().show();

    }

    private void getUserBirthDay(int year, int month, int dayOfMonth) {
        StringBuilder builder = new StringBuilder();
        builder.append(year+"-");
        if(month<10){
            builder.append("0"+month+"-");
        }else{
            builder.append(month+"-");
        }

        if(dayOfMonth < 10){
            builder.append("0"+dayOfMonth);
        }else{
            builder.append(dayOfMonth);
        }
        tvUserBirthday.setText(builder.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(builder.toString());
            userBirthDay = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK){
            return;
        }

        switch (requestCode){
            case 1001:
                Uri uri = data.getData();
                if(MyApplication.isKitKat) {
                     userIcon= getPath_above19(getApplication(), uri);
                }else{
                    userIcon = getPath_below19(getApplication(), uri);
                }
                Logger.e(userIcon);
                ivUserIcon.setImageBitmap(BitmapFactory.decodeFile(userIcon));
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 低于API19时获取相册返回图片地址方法
     * @param context
     * @param uri
     * @return
     */
    public static String getPath_below19(final Context context,final Uri uri){
        //这里开始的第二部分，获取图片的路径：低版本的是没问题的，但是sdk>19会获取不到
        String[] proj = {MediaStore.Images.Media.DATA};
        //好像是android多媒体数据库的封装接口，具体的看Android文档
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        //获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        //将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();
        //最后根据索引值获取图片路径   结果类似：/mnt/sdcard/DCIM/Camera/IMG_20151124_013332.jpg
        String path = cursor.getString(column_index);
        return path;
    }


    /**
     * 高于API19时获取相册返回图片地址方法
     * @param context
     * @param uri
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public  static String getPath_above19(final Context context, final Uri uri) {
        // DocumentProvider
        if ( DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }



    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
// TODO Auto-generated method stub
        if (wheel == mWheelProvince) {
            updateCities();
        } else if (wheel == mWheelCity) {
            updateAreas();
        } else if (wheel == mWheelDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mWheelCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mWheelDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mWheelDistrict.setCurrentItem(0);
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mWheelProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mWheelCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mWheelCity.setCurrentItem(0);
        updateAreas();
    }

}
