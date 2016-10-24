package schaffer.logindemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.logindemo.base.BaseActivity;
import schaffer.logindemo.base.MyApplication;
import schaffer.logindemo.bean.RegisterDataBean;
import schaffer.logindemo.dialog.LoadDialog;
import schaffer.logindemo.R;
import schaffer.logindemo.utils.LogUtils;
import schaffer.logindemo.utils.ToastUtils;

public class RegisterActivity extends BaseActivity {

    protected EditText mEdtNickName;
    protected EditText mEdtPhoneNum;
    protected EditText mEdtPassword;
    protected Button mBtnRegister;
    protected EditText mEdtVerifyCode;
    private CheckBox mCbAgreement;
    private TextView tv;
    private int i;
    //验证码长度
    int code_length = 6;
    private LoadDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_register);
        initView();
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.register_group);
//        controlKeyboardLayout(linearLayout, mBtnRegister);
    }

    /**
     * 已有账号
     *
     * @param v 已有账号按钮
     */
    public void registerLogin(View v) {
        back(v);
    }


    /**
     * 注册逻辑
     *
     * @param v 注册按钮
     */
    public void registerGo(View v) {
        if (!mCbAgreement.isChecked()) {
            ToastUtils.shortNotify("请阅读并同意相关协议");
            return;
        }
        String name = mEdtNickName.getText().toString().trim();
        if (name.equals(" ") || name.equals("")) {
            ToastUtils.shortNotify("昵称不能为空");
            return;
        }
        int length = mEdtPhoneNum.getText().length();
        if (length != 11) {
            ToastUtils.shortNotify("手机号不规范");
            return;
        }
        int pwdLength = mEdtPassword.getText().toString().length();
        if (pwdLength < 6) {
            ToastUtils.shortNotify("密码长度应该大于6");
            return;
        }

        if (mEdtVerifyCode.getText().toString().length() != code_length) {
            ToastUtils.shortNotify("验证码长度不符");
            return;
        }
        if (!MyApplication.test) {
            load = new LoadDialog(this);
            load.show();
            sendRegisterMessage(name, mEdtPhoneNum.getText().toString(), mEdtPassword.getText().toString(), mEdtVerifyCode.getText().toString());
        } else {
            ToastUtils.shortNotify("注册成功,发送注册信息");
            finish();
        }

    }

    /**
     * 发送注册信息给服务器并获得返回数据,来进行相应的反馈
     *
     * @param name       昵称
     * @param phone      电话
     * @param password   密码
     * @param yanZhengMa 验证码
     */
    private void sendRegisterMessage(String name, String phone, String password, String yanZhengMa) {
        register(name, Long.parseLong(phone), Integer.parseInt(yanZhengMa), password)
                .enqueue(new Callback<RegisterDataBean>() {
                    @Override
                    public void onResponse(Call<RegisterDataBean> call, Response<RegisterDataBean> response) {
                        if (response.isSuccessful()) {
                            RegisterDataBean dataBean = response.body();
                            String message = dataBean.getMessage();
                            //注册信息处理
//                            LogUtils.w("注册内容:" + message);
                            if (message.equals("字段校验失败")) {
                                ToastUtils.shortNotify("手机已经注册");
                                return;
                            }
                            ToastUtils.shortNotify(message);

                        } else {
                            LogUtils.w("注册失败");
                        }
                        if (load != null) {
                            load.dismiss();
                        }
                        if (response.isSuccessful()) {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterDataBean> call, Throwable t) {
                        ToastUtils.shortNotify("注册成功!");
                        if (load != null) {
                            load.dismiss();
                            finish();
                        }
                    }
                });
    }

    /**
     * 点击协议界面
     *
     * @param v 查看协议
     */
    public void agreement(View v) {
        Intent intent = new Intent(RegisterActivity.this, AgreementActivity.class);
        startActivity(intent);
    }

    @Override
    public void sendVerifyCode(View v) {
        int length = mEdtPhoneNum.getText().length();
        if (length != 11) {
            ToastUtils.shortNotify("手机号不规范");
            return;
        }
        //测试时
        if (send_Offline()) return;
        //发送手机注册验证码

        mMobileNum = mEdtPhoneNum.getText().toString();
        mCodeType = 10000;
        if (!MyApplication.test) {
            //发送验证码信息
            super.sendVerifyCode(v);
        }
        i = 60;
        tv = (TextView) v;
        tv.setText("等待" + i + "秒重发");
        tv.setEnabled(false);
        tv.setTextColor(Color.parseColor("#999999"));
        handler.sendEmptyMessageDelayed(200, 1000);
    }

    private boolean send_Offline() {
        if (MyApplication.test) {
            if (mEdtPhoneNum.getText().toString().trim().equals("12345678910")) {
                ToastUtils.shortNotify("已经注册...");
                return true;
            } else {
                LogUtils.w("离线测试未注册");
            }
        }
        return false;
    }

    @Override
    public void handleMsg(Message msg) {
        super.handleMsg(msg);
        if (msg.what == 200) {
            if (i != 0) {
                i--;
                tv.setText("等待" + i + "秒重发");
                if (MyApplication.test && i == 55) {
                    String falseData = "123456";
                    mEdtVerifyCode.setText(falseData);//假数据
                }
                handler.sendEmptyMessageDelayed(200, 1000);
            } else {
                tv.setEnabled(true);
                tv.setTextColor(Color.parseColor("#17B18C"));
                tv.setText("再次发送");
            }
        }
    }

    boolean mIsChecked = true;

    /**
     * 显示写入的密码
     *
     * @param view 显示密码
     */
    public void showPwd(View view) {
        if (mIsChecked) {
            //需要显示内容
            mEdtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //不显示内容
            mEdtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        mIsChecked = !mIsChecked;
        int length = mEdtPassword.getText().toString().length();
        mEdtPassword.setSelection(length);
    }


    private void initView() {
        mEdtNickName = (EditText) findViewById(R.id.register_nicheng);
        mEdtPhoneNum = (EditText) findViewById(R.id.register_shouji);
        mEdtVerifyCode = (EditText) findViewById(R.id.register_yanzhengma);
        mEdtPassword = (EditText) findViewById(R.id.register_pwd_edt);
        mBtnRegister = (Button) findViewById(R.id.register_btn);
        mCbAgreement = (CheckBox) findViewById(R.id.register_cb);
//        controlKeyboardLayout();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
