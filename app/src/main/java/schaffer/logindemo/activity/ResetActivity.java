package schaffer.logindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.logindemo.R;
import schaffer.logindemo.base.BaseActivity;
import schaffer.logindemo.base.MyApplication;
import schaffer.logindemo.bean.ForgetSecretDataBean;
import schaffer.logindemo.dialog.DifferentNotifyDialog;
import schaffer.logindemo.utils.LogUtils;
import schaffer.logindemo.utils.ToastUtils;

/**
 * 重新设置密码
 */
public class ResetActivity extends BaseActivity {

    protected EditText mEdtPassword1;
    protected EditText mEdtPassword2;
    private String mobileNumber;
    private String verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_reset);

        initView();
        Intent intent = getIntent();
        mobileNumber = intent.getStringExtra("mobileNumber");
        verifyCode = intent.getStringExtra("verifyCode");

    }

    public void chongZhi(View v) {
        String s1 = mEdtPassword1.getText().toString();
        String s2 = mEdtPassword2.getText().toString();
        int length1 = s1.length();
        int length2 = s2.length();

        if (s1.equals("") || s2.equals("")) {
            ToastUtils.shortNotify("某一项不能为空");
            return;
        }
        if (length1 < 6 || length2 < 6) {
            ToastUtils.shortNotify("密码要大于6位");
            return;
        }
        if (!s1.equals(s2)) {
            DifferentNotifyDialog notify = new DifferentNotifyDialog(this);
            notify.show();
            return;
        }
        //不是本地测试数据的情况下
        if (!MyApplication.test) {
            resetOnline(s1);
        } else {
            finish();
        }
    }

    private void resetOnline(String s1) {
        forget(mobileNumber, Integer.parseInt(verifyCode), s1)
                .enqueue(new Callback<ForgetSecretDataBean>() {
                    @Override
                    public void onResponse(Call<ForgetSecretDataBean> call, Response<ForgetSecretDataBean> response) {
                        if (response.isSuccessful()) {
                            ForgetSecretDataBean dataBean = response.body();
                            LogUtils.w("重新设置的回复:" + dataBean.toString());
                            if (dataBean.getMessage().equals("OK")) {
                                ToastUtils.shortNotify("密码修改成功!");
                                finish();
                            } else {
                                LogUtils.w("修改失败1--->" + dataBean.getMessage());
                            }
                        } else {
                            LogUtils.w("修改失败2--->" + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgetSecretDataBean> call, Throwable t) {
                        ToastUtils.shortNotify("修改密码失败,请留意:" + t.getCause());
                    }
                });
    }


    private void initView() {
        mEdtPassword1 = (EditText) findViewById(R.id.chongzhi_pwd_edt1);
        mEdtPassword2 = (EditText) findViewById(R.id.chongzhi_pwd_edt2);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
