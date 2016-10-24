package schaffer.logindemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import schaffer.logindemo.R;
import schaffer.logindemo.base.BaseActivity;
import schaffer.logindemo.base.MyApplication;
import schaffer.logindemo.bean.PhoneLoginDataBean;
import schaffer.logindemo.dialog.LoadDialog;
import schaffer.logindemo.dialog.LoginNotifyDialog;
import schaffer.logindemo.utils.LogUtils;
import schaffer.logindemo.utils.ToastUtils;

/**
 * 登录界面-->登录成功
 */
public class LoginActivity extends BaseActivity {

    private EditText mEdtAccount;
    private EditText mEdtPassword;
    private LoginNotifyDialog dialog;
    private LoadDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
//        controlKeyboardLayout(activitymain, loginbtn);
    }

    private void initViews() {
        mEdtAccount = (EditText) findViewById(R.id.login_shoujihao);
        mEdtPassword = (EditText) findViewById(R.id.login_mima);
    }

    /**
     * 登录
     * 账号已经限制为11位数字
     * 密码限制最长为12位任意内容
     *
     * @param view 登录按钮
     */
    public void login(View view) {
        String str;
        String acc = mEdtAccount.getText().toString();
        String pwd = mEdtPassword.getText().toString();
        if (acc.equals("") || pwd.equals("")) {
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
     * @param acc 账号
     * @param pwd 密码
     */
    private void queryByNetwork(final String acc, final String pwd) {
        if (MyApplication.test) {
            if (acc.equals("12345678910") && pwd.equals("12345678910")) {
                ToastUtils.shortNotify("假数据测试登录成功,请在application下更改test属性");
            } else {
                ToastUtils.shortNotify("假数据测试登录失败");
            }
            return;
        }
        load = new LoadDialog(this);
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
                            LogUtils.w("dataBean-->" + dataBean.toString());
                            if (dataBean.getMessage().equals("OK")) {
                                dialog = new LoginNotifyDialog(LoginActivity.this);
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
     * @param view 注册按钮
     */
    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 第三方登录操作
     *
     * @param view 第三方登录操作
     */
    public void loginThird(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_weixin:
                // TODO: 第三方登录:微信 
                break;
            case R.id.login_qq:
                // TODO: 第三方登录:QQ 

                break;
            case R.id.login_weibo:
                // TODO: 第三方登录:微博 

                break;

        }
    }

    /**
     * 忘记密码选项
     *
     * @param view 忘记密码TextView
     */
    public void forget(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
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
