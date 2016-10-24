package schaffer.logindemo.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import schaffer.logindemo.bean.ForgetSecretDataBean;
import schaffer.logindemo.bean.PhoneLoginDataBean;
import schaffer.logindemo.bean.RegisterDataBean;
import schaffer.logindemo.bean.SendSmsDataBean;
import schaffer.logindemo.dialog.RegisterNotifyDialog;
import schaffer.logindemo.network.RetrofitService;
import schaffer.logindemo.network.RetrofitUtils;
import schaffer.logindemo.utils.LogUtils;
import schaffer.logindemo.utils.ToastUtils;

public class BaseActivity extends AutoLayoutActivity implements RetrofitService {
    /**
     * 返回上一个页面
     *
     * @param view 返回
     */
    public void back(View view) {
        finish();
    }

    public Context mContext;
    public String mMobileNum;
    public int mCodeType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /**
     * 发送验证码的操作
     *
     * @param v 发送验证码
     */
    public void sendVerifyCode(View v) {
        if (mMobileNum != null && mCodeType != 0) {
            sendSms(mMobileNum, mCodeType).enqueue(new Callback<SendSmsDataBean>() {
                @Override
                public void onResponse(Call<SendSmsDataBean> call, Response<SendSmsDataBean> response) {
                    if (response.isSuccessful()) {
                        SendSmsDataBean body = response.body();
                        String message = body.getMessage();
                        LogUtils.w(message + "," + body.getCode() + "," + body.getData());
                        //手机号已注册,请勿重复注册
                        if (message.equals("手机号未注册,无法进行找回密码操作")) {
                            RegisterNotifyDialog register_notify = new RegisterNotifyDialog(mContext);
                            register_notify.show();
                            return;
                        }
                        ToastUtils.shortNotify(message);
                    } else {
                        ToastUtils.shortNotify("验证码发送失败,错误代码:" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<SendSmsDataBean> call, Throwable t) {
                    LogUtils.w(t.getMessage() + "-" + t.getCause());

                }
            });
        }

    }


    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMsg(msg);
        }
    };

    /**
     * Handler处理信息
     *
     * @param msg message
     */
    public void handleMsg(Message msg) {
        // TODO: 子类通过message处理线程间通信
    }

    @Override
    public void finish() {
        handler.removeCallbacksAndMessages(null);
        super.finish();
    }

    @Override
    public Call<PhoneLoginDataBean> login(@Field("mobile") long mobile, @Field("password") String password, @Field("device_num") String device_num) {
        return RetrofitUtils.getStringService().login(mobile, password, device_num);
    }

    @Override
    public Call<ForgetSecretDataBean> forget(@Field("mobile") String mobile, @Field("mobile_code") int mobile_code, @Field("password") String password) {
        return RetrofitUtils.getStringService().forget(mobile, mobile_code, password);
    }

    @Override
    public Call<RegisterDataBean> register(@Field("username") String username, @Field("mobile") long mobile, @Field("mobile_code") int mobile_code, @Field("password") String password) {
        return RetrofitUtils.getStringService().register(username, mobile, mobile_code, password);
    }

    @Override
    public Call<SendSmsDataBean> sendSms(@Field("mobile") String mobile, @Field("type") int type) {
        return RetrofitUtils.getStringService().sendSms(mobile, type);
    }

    @Override
    public Call<SendSmsDataBean> verifySms(@Field("mobile") long mobile, @Field("mobile_code") int mobile_code) {
        return RetrofitUtils.getStringService().verifySms(mobile, mobile_code);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }
}

