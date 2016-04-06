package com.longding999.longding;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.SharedHelper;
import com.longding999.longding.view.ClearEditText;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/15 9:57
 * Desc: 登陆页面
 * *****************************************************************
 */
public class LoginActivity extends BasicActivity implements View.OnClickListener {
    private TextView tvTitle, tvLeft, tvRight;
    private ImageView imageLeft;

    private ClearEditText edtPhoneNumber, edtPassWord;
    private Button btnLogin;

    private DbManager dbManager;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("登录");
        tvLeft.setVisibility(View.GONE);
        tvRight.setText("注册");
        imageLeft.setImageResource(R.mipmap.iv_close);

        edtPhoneNumber = (ClearEditText) findViewById(R.id.edt_phoneNumber);
        edtPassWord = (ClearEditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        dbManager = DbHelper.getInstance().getDbManger();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_left:
                onBackPressed();
                break;

            case R.id.tv_right:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_login:
                login();
                break;

            default:
                break;
        }
    }


    private void login() {
        String phoneNumber = edtPhoneNumber.getText().toString().trim();
        String passWord = edtPassWord.getText().toString().trim();
        if (phoneNumber.isEmpty() || passWord.isEmpty()) {
            shortToast("手机或密码不能为空!");
        } else {
            try {
                List<UserInfo> userPhone = dbManager.selector(UserInfo.class).where("userPhone", "=", phoneNumber).findAll();
                if (userPhone == null) {
                    shortToast("账号错误!");
                } else {
                    UserInfo userInfo = userPhone.get(0);
                    if (passWord.equals(userInfo.getUserPwd())) {
                        shortToast("登录成功!");

                        SharedHelper.saveBoolean(SharedHelper.LOGIN, true);
                        SharedHelper.saveString(SharedHelper.ID, userInfo.get_id() + "");

                        Intent intent = new Intent();
                        intent.putExtra("_id", userInfo.get_id());
                        setResult(1001, intent);
                        finish();
                    } else {
                        shortToast("密码错误!");
                    }
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }


    public CookieManager cookieManager = null;
    public static String cookies;

    public String sendPost(String url, String username, String password) {
        CookieSyncManager.createInstance(LoginActivity.this);
        // 每次登录操作的时候先清除cookie
        removeAllCookie();
        // 根据url获得HttpPost对象
        HttpPost httpRequest = new HttpPost(url);
        // 取得默认的HttpClient
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String strResult = null;
        // NameValuePair实现请求参数的封装
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("_tk", "codefrom"));
        params.add(new BasicNameValuePair("name", username));
        params.add(new BasicNameValuePair("pass", password));
        httpRequest.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpRequest.addHeader("Origin", "http://www.codefrom.com");
        httpRequest.addHeader("Referer", "http://www.codefrom.com/login");
        httpRequest.addHeader("X-Requested-With", "XMLHttpRequest");
        try {
            // 添加请求参数到请求对象
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            // 获得响应对象
            HttpResponse httpResponse = httpclient.execute(httpRequest);
            // 判断是否请求成功
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 获得响应返回Json格式数据
                strResult = EntityUtils.toString(httpResponse.getEntity());
                // 取得Cookie
                CookieStore mCookieStore = httpclient.getCookieStore();
                List<Cookie> cookies = mCookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("Cookies为空");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        // 保存cookie
                        Cookie cookie = cookies.get(i);
                        Log.d("Cookie", cookies.get(i).getName() + "=" + cookies.get(i).getValue());
                        cookieManager = CookieManager.getInstance();
                        String cookieString = cookie.getName() + "=" + cookie.getValue() + "; domain=" + cookie.getDomain();
                        cookieManager.setCookie("http://www.codefrom.com/", cookieString);
                    }
                }
                return strResult;
            } else {
                strResult = "错误响应:" + httpResponse.getStatusLine().toString();
            }
        } catch (ClientProtocolException e) {
            strResult = "错误响应:" + e.getMessage().toString();
            e.printStackTrace();
            return strResult;
        } catch (IOException e) {
            strResult = "错误响应:" + e.getMessage().toString();
            e.printStackTrace();
            return strResult;
        } catch (Exception e) {
            strResult = "错误响应:" + e.getMessage().toString();
            e.printStackTrace();
            return strResult;
        }
        return strResult;
    }

    private void removeAllCookie() {
        cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }
}
