package schaffer.logindemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.logindemo.R;
import schaffer.logindemo.base.BaseActivity;
import schaffer.logindemo.base.MyApplication;
import schaffer.logindemo.bean.SendSmsDataBean;
import schaffer.logindemo.dialog.RegisterNotifyDialog;
import schaffer.logindemo.utils.LogUtils;
import schaffer.logindemo.utils.ToastUtils;

/**
 * 根据手机号发送验证码请求找回密码界面
 * 得到数据成功,存在onFailed()回调中
 * 验证验证码失败,验证码的code = 500,不在成功范围内
 */
public class ForgetActivity extends BaseActivity {


    private EditText mEdtPhoneNum;
    private EditText mEdtVerifyCode;
    private int mSecond;
    private TextView mTvSend;
    //验证码的长度
    int code_length = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_forget);
        initView();
    }

    /**
     * 下一步,
     * 首先验证二者中是否有空,有就toast
     * 然后验证手机号是否已经注册,否则弹出Dialog
     * 然后验证手机号和验证码是否与服务器的内容一致,然后做出响应
     *
     * @param v 下一步按钮
     */
    public void next(View v) {
        final String phoneStr = mEdtPhoneNum.getText().toString();
        final String codeStr = mEdtVerifyCode.getText().toString();
        if (phoneStr.equals("") || codeStr.equals("")) {
            ToastUtils.shortNotify("手机号或验证码不能为空");
            return;
        }

        if (codeStr.length() != code_length) {
            ToastUtils.shortNotify("验证码长度不符");
            return;
        }

        //验证是否与服务器一致
        // 假数据
        if (next_Offline(phoneStr, codeStr)) return;
        //真数据
        next_Online(phoneStr, codeStr);
    }

    private void next_Online(final String phoneStr, final String codeStr) {
        verifySms(Long.parseLong(phoneStr), Integer.parseInt(codeStr))
                .enqueue(new Callback<SendSmsDataBean>() {
                    @Override
                    public void onResponse(Call<SendSmsDataBean> call, Response<SendSmsDataBean> response) {
                        LogUtils.w("下一步-->" + response.toString() + "-->" + response.code());
                        //出现code = 500,回复的信息不是javabean对应的类型
                        toReset(phoneStr, codeStr);
                        if (response.isSuccessful()/*||response.code()==500*/) {
                            SendSmsDataBean dataBean = response.body();
                            if (dataBean.getMessage().equals("OK")) {
                                LogUtils.w("验证码验证成功");
                                //跳转
                                toReset(phoneStr, codeStr);
                            } else {
                                LogUtils.w("验证码验证失败1");
                            }
                        } else {
                            LogUtils.w("验证码验证失败");
                        }
                    }

                    @Override
                    public void onFailure(Call<SendSmsDataBean> call, Throwable t) {
//                        ToastUtils.shortNotify("验证码验证失败,请检查错误:" + t.getCause());
                        //出现问题:验证码正常发送还是会出错
                    }
                });
    }

    private boolean next_Offline(String phoneStr, String codeStr) {
        if (MyApplication.test) {
            if (phoneStr.equals("12345678910") && codeStr.equals("12345")) {
                toReset(phoneStr, codeStr);
                return true;
            } else {
                LogUtils.w("离线验证错误");
            }
        }
        return false;
    }

    private void toReset(String phoneStr, String codeStr) {
        Intent intent = new Intent(ForgetActivity.this, ResetActivity.class);
        intent.putExtra("mobileNumber", phoneStr);
        intent.putExtra("verifyCode", codeStr);
        startActivity(intent);
        finish();
    }

    /**
     * 需要判断是否已经注册,未注册就显示dialog,注册就发送验证码请求
     *
     * @param v 注册按钮
     */
    @Override
    public void sendVerifyCode(View v) {
        int length = mEdtPhoneNum.getText().length();
        if (length != 11) {
            ToastUtils.shortNotify("手机号不规范");
            return;
        }
        //测试内容
        String phoneNum = mEdtPhoneNum.getText().toString().trim();
        if (MyApplication.test && !phoneNum.equals("12345678910")) {
            //已经注册
            ToastUtils.shortNotify("当前账号未注册");
            RegisterNotifyDialog registerNotifyDialog = new RegisterNotifyDialog(this);
            registerNotifyDialog.show();
            return;
        }
        //发送修改验证码
        mMobileNum = phoneNum;
        mCodeType = 10001;

        if (!MyApplication.test) {
            super.sendVerifyCode(v);
        }
        mSecond = 60;
        mTvSend = (TextView) v;
        mTvSend.setText("等待" + mSecond + "秒重发");
        mTvSend.setEnabled(false);
        mTvSend.setTextColor(Color.parseColor("#999999"));
        handler.sendEmptyMessageDelayed(200, 1000);
    }

    @Override
    public void handleMsg(Message msg) {
        super.handleMsg(msg);
        if (mSecond != 0) {
            mSecond--;
            mTvSend.setText("等待" + mSecond + "秒重发");
            //测试内容
            if (MyApplication.test && mSecond == 55) {
                mEdtVerifyCode.setText("123456");
            }
            handler.sendEmptyMessageDelayed(200, 1000);
        } else {
            mTvSend.setEnabled(true);
            mTvSend.setTextColor(Color.parseColor("#17B18C"));
            mTvSend.setText("再次发送");
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void initView() {
        this.mEdtVerifyCode = (EditText) findViewById(R.id.find_yanzhengma_edt);
        this.mEdtPhoneNum = (EditText) findViewById(R.id.find_shouji);

    }
}
