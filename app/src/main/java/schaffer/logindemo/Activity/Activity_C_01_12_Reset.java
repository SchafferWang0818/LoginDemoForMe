package schaffer.logindemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.logindemo.Base.BaseActivity;
import schaffer.logindemo.Base.MApplication;
import schaffer.logindemo.Bean.ForgetSecretDataBean;
import schaffer.logindemo.Dialog.Dialog_different_notify;
import schaffer.logindemo.R;
import schaffer.logindemo.Utils.LogUtils;
import schaffer.logindemo.Utils.ToastUtils;

/**
 * 重新设置密码
 */
public class Activity_C_01_12_Reset extends BaseActivity {

    protected EditText edt1;
    protected EditText edt2;
    private String mobile;
    private String mobile_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_c_01_12_reset);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.reset_group);
        initView();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        mobile_code = intent.getStringExtra("mobile_code");

    }

    public void chongZhi(View v) {
        String s1 = edt1.getText().toString();
        String s2 = edt2.getText().toString();
        int length1 = s1.length();
        int length2 = s2.length();

        if (s1.equals("") || s1 == null || s2.equals("") || s2 == null) {
            ToastUtils.shortNotify("某一项不能为空");
            return;
        }
        if (length1 < 6 || length2 < 6) {
            ToastUtils.shortNotify("密码要大于6位");
            return;
        }
        if (!s1.equals(s2)) {
//            ToastUtils.shortNotify("两次输入不匹配");
            Dialog_different_notify notify = new Dialog_different_notify(this);
            notify.show();
            return;
        }
        //不是本地测试数据的情况下
        if (!MApplication.test) {
            reset_Online(s1);
        } else {
            finish();
        }
    }

    private void reset_Online(String s1) {
        forget(mobile, Integer.parseInt(mobile_code), s1)
                .enqueue(new Callback<ForgetSecretDataBean>() {
                    @Override
                    public void onResponse(Call<ForgetSecretDataBean> call, Response<ForgetSecretDataBean> response) {
                        if (response.isSuccessful()) {
                            ForgetSecretDataBean dataBean = response.body();
                            LogUtils.w("重新设置的回复:"+dataBean.toString());
                            if (dataBean.getMessage().equals("OK")) {
                                ToastUtils.shortNotify("密码修改成功!");
                                finish();
                            } else {
                                LogUtils.w("修改失败1--->"+dataBean.getMessage());
                            }
                        } else {
                            LogUtils.w("修改失败2--->"+response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgetSecretDataBean> call, Throwable t) {
                        ToastUtils.shortNotify("修改密码失败,请留意:" + t.getCause());
                    }
                });
    }


    private void initView() {
        edt1 = (EditText) findViewById(R.id.chongzhi_pwd_edt1);
        edt2 = (EditText) findViewById(R.id.chongzhi_pwd_edt2);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
