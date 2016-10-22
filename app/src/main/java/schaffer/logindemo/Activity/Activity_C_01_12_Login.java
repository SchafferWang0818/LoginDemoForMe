package schaffer.logindemo.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import schaffer.logindemo.Base.BaseActivity;
import schaffer.logindemo.Base.MApplication;
import schaffer.logindemo.Bean.PhoneLoginDataBean;
import schaffer.logindemo.Dialog.Dialog_load;
import schaffer.logindemo.Dialog.Dialog_login_notify;
import schaffer.logindemo.R;
import schaffer.logindemo.Utils.LogUtils;
import schaffer.logindemo.Utils.ToastUtils;

/**
 * 登录界面-->登录成功
 */
public class Activity_C_01_12_Login extends BaseActivity {

    private android.widget.RelativeLayout logintoolbar;
    private android.widget.ImageView loginlogo;
    private android.widget.TextView loginbrand;
    private android.widget.Button loginbtn;
    private android.widget.TextView loginforget;
    private android.widget.ImageView loginweixin;
    private android.widget.ImageView loginqq;
    private android.widget.ImageView loginweibo;
    private android.widget.RelativeLayout logindisanfang;
    private android.widget.LinearLayout activitymain;
    private EditText accountEdt;
    private EditText pwdEdt;
    private Dialog_login_notify dialog;
    private Dialog_load load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_01_12_login);
        initViews();
//        controlKeyboardLayout(activitymain, loginbtn);
    }

    private void initViews() {
        this.activitymain = (LinearLayout) findViewById(R.id.activity_main);
        this.logindisanfang = (RelativeLayout) findViewById(R.id.login_disanfang);
        this.loginweibo = (ImageView) findViewById(R.id.login_weibo);
        this.loginqq = (ImageView) findViewById(R.id.login_qq);
        this.loginweixin = (ImageView) findViewById(R.id.login_weixin);
        this.loginforget = (TextView) findViewById(R.id.login_forget);
        this.loginbtn = (Button) findViewById(R.id.login_btn);
        this.loginbrand = (TextView) findViewById(R.id.login_brand);
        this.loginlogo = (ImageView) findViewById(R.id.login_logo);
        this.logintoolbar = (RelativeLayout) findViewById(R.id.login_toolbar);
        accountEdt = (EditText) findViewById(R.id.login_shoujihao);
        pwdEdt = (EditText) findViewById(R.id.login_mima);

    }

    /**
     * 登录
     * 账号已经限制为11位数字
     * 密码限制最长为12位任意内容
     *
     * @param view
     */
    public void login(View view) {
        String str;
        String acc = accountEdt.getText().toString();
        String pwd = pwdEdt.getText().toString();
        if (acc.equals("") || pwd.equals("") || acc == null || pwd == null) {
            str = "某项内容不能为空";
            ToastUtils.shortNotify(str);
        } else if (acc.length() != 11) {
            str = "账号不是手机号";
            ToastUtils.shortNotify(str);
        } else if (pwd.length() < 6) {
            str = "密码长度不能少于6位";
            ToastUtils.shortNotify(str);
        } else {
            //服务器对数据进行检索
            queryByNetwork(acc, pwd);
        }

    }

    /**
     * 从网络数据库进行登录验证,需要具体实现
     *
     * @param acc
     * @param pwd
     */
    private void queryByNetwork(final String acc, final String pwd) {
        if (MApplication.test) {
            if (acc.equals("12345678910") && pwd.equals("12345678910")) {
                ToastUtils.shortNotify("假数据测试登录成功,请在application下更改test属性");
            } else {
                ToastUtils.shortNotify("假数据测试登录失败");
            }
            return;
        }
        load = new Dialog_load(this);
        load.show();
        long mobile = Long.parseLong(acc);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();

        login(mobile, pwd, DEVICE_ID)
                .enqueue(new Callback<PhoneLoginDataBean>() {
                    @Override
                    public void onResponse(Call<PhoneLoginDataBean> call, Response<PhoneLoginDataBean> response) {
                        if (response.isSuccessful()) {
                            //处理数据
                            PhoneLoginDataBean dataBean = response.body();
                            LogUtils.w("--->" + dataBean.getMessage() + "," + dataBean.getCode() + "," + dataBean.getData());
                            LogUtils.w("dataBean-->"+dataBean.toString());
                            if (dataBean.getMessage().equals("OK")) {
                                dialog = new Dialog_login_notify(Activity_C_01_12_Login.this);
                                dialog.show();
                                //处理返回的登录数据


                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                }, 1000);
                            } else {
                                ToastUtils.shortNotify("登录失败");
                                load.dismiss();
                                return;
                            }
                        } else {
                            ToastUtils.shortNotify("登录失败,请稍后再试");
                        }
                        load.dismiss();
                    }

                    @Override
                    public void onFailure(Call<PhoneLoginDataBean> call, Throwable t) {
                        ToastUtils.shortNotify("登录失败,错误因素:" + t.getCause());
                        load.dismiss();
                    }
                });


    }

    /**
     * 手机注册
     *
     * @param view
     */
    public void register(View view) {
        Intent intent = new Intent(Activity_C_01_12_Login.this, Activity_C_01_12_Register.class);
        startActivity(intent);
    }

    /**
     * 第三方登录操作
     *
     * @param view
     */
    public void loginThird(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_weixin:

                break;
            case R.id.login_qq:

                break;
            case R.id.login_weibo:

                break;

        }
        ToastUtils.shortNotify("此处应该添加第三方登录");
    }

    /**
     * 忘记密码选项
     *
     * @param view
     */
    public void forget(View view) {
        Intent intent = new Intent(Activity_C_01_12_Login.this, Activity_C_01_12_FindByTel.class);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public Call<PhoneLoginDataBean> login(@Field("mobile") long mobile, @Field("password") String password, @Field("device_num") String device_num) {
        return super.login(mobile, password, device_num);
    }
}
