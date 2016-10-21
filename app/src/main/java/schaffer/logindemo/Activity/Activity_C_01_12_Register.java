package schaffer.logindemo.Activity;

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
import schaffer.logindemo.Base.BaseActivity;
import schaffer.logindemo.Base.MApplication;
import schaffer.logindemo.Bean.RegisterDataBean;
import schaffer.logindemo.Dialog.Dialog_load;
import schaffer.logindemo.R;
import schaffer.logindemo.Utils.LogUtils;
import schaffer.logindemo.Utils.ToastUtils;

/**
 * 注册中
 * 1. 得到验证码
 * 2. 对注册信息进行验证
 */
public class Activity_C_01_12_Register extends BaseActivity {

    protected EditText edt_nickName;
    protected EditText edt_tel;
    protected EditText edt_pwd;
    protected Button registerBtn;
    protected EditText edt_yanZhengMa;
    private CheckBox checkBox;
    private TextView tv;
    private int i;
    //验证码长度
    int code_length = 6;
    private Dialog_load load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_c_01_12_register);
        initView();
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.register_group);
//        controlKeyboardLayout(linearLayout, registerBtn);
    }

    /**
     * 已有账号
     *
     * @param v
     */
    public void registerLogin(View v) {
        fanhui(v);
    }


    /**
     * 注册按钮
     *
     * @param v
     */
    public void registerGo(View v) {
        String str;
        if (!checkBox.isChecked()) {
            ToastUtils.shortNotify("请阅读并同意相关协议");
            return;
        }
        //其他判断
        String name = edt_nickName.getText().toString().trim();
        if (name.equals(" ") || name == null || name.equals("")) {
            ToastUtils.shortNotify("昵称不能为空");
            return;
        }
        int length = edt_tel.getText().length();
        if (length != 11) {
            ToastUtils.shortNotify("手机号不规范");
            return;
        }
        int pwdLength = edt_pwd.getText().toString().length();
        if (pwdLength < 6) {
            ToastUtils.shortNotify("密码长度应该大于6");
            return;
        }

        if (edt_yanZhengMa.getText().toString().length() != code_length) {
            ToastUtils.shortNotify("验证码长度不符");
            return;
        }
        //发送注册信息,并进行响应判断
        if (!MApplication.test) {
            //不是测试的内容
            load = new Dialog_load(this);
            load.show();
            sendRegisterMessage(name, edt_tel.getText().toString(), edt_pwd.getText().toString(), edt_yanZhengMa.getText().toString());
        } else {
            //测试情况下
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
                            ToastUtils.shortNotify(message);

                        } else {
                            LogUtils.w("注册失败2");
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
     * @param v
     */
    public void xieyi(View v) {
        Intent intent = new Intent(Activity_C_01_12_Register.this, Activity_C_01_12_Agreement.class);
        startActivity(intent);
    }

    @Override
    public void sendYanZhengMa(View v) {
        int length = edt_tel.getText().length();
        if (length != 11) {
            ToastUtils.shortNotify("手机号不规范");
            return;
        }
        //测试时
        if (send_Offline()) return;
        //发送手机注册验证码

        mobile_num = edt_tel.getText().toString();
        code_type = 10000;
        if (!MApplication.test) {
            //发送验证码信息
            super.sendYanZhengMa(v);
        }
        i = 60;
        tv = (TextView) v;
        tv.setText("等待" + i + "秒重发");
        tv.setEnabled(false);
        tv.setTextColor(Color.parseColor("#999999"));
        handler.sendEmptyMessageDelayed(200, 1000);
    }

    private boolean send_Offline() {
        if (MApplication.test) {
            if (edt_tel.getText().toString().trim().equals("12345678910")) {
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
                if (MApplication.test && i == 55) {
                    edt_yanZhengMa.setText("123456");
                }
                handler.sendEmptyMessageDelayed(200, 1000);
            } else {
                tv.setEnabled(true);
                tv.setTextColor(Color.parseColor("#17B18C"));
                tv.setText("再次发送");
                return;
            }
        }
    }

    boolean isChecked = true;

    /**
     * 显示写入的密码
     *
     * @param view
     */
    public void showPwd(View view) {
        if (isChecked) {
            //需要显示内容
            edt_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //不显示内容
            edt_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        isChecked = !isChecked;
        int length = edt_pwd.getText().toString().length();
        edt_pwd.setSelection(length);
    }


    private void initView() {
        edt_nickName = (EditText) findViewById(R.id.register_nicheng);
        edt_tel = (EditText) findViewById(R.id.register_shouji);
        edt_yanZhengMa = (EditText) findViewById(R.id.register_yanzhengma);
        edt_pwd = (EditText) findViewById(R.id.register_pwd_edt);
        registerBtn = (Button) findViewById(R.id.register_btn);
        checkBox = (CheckBox) findViewById(R.id.register_cb);
//        controlKeyboardLayout();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
