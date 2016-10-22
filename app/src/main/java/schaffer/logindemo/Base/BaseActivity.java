package schaffer.logindemo.Base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.zhy.autolayout.AutoLayoutActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import schaffer.logindemo.Bean.ForgetSecretDataBean;
import schaffer.logindemo.Bean.PhoneLoginDataBean;
import schaffer.logindemo.Bean.RegisterDataBean;
import schaffer.logindemo.Bean.SendSmsDataBean;
import schaffer.logindemo.Dialog.Dialog_register_notify;
import schaffer.logindemo.NetWork.RetrofitService;
import schaffer.logindemo.NetWork.RetrofitUtils;
import schaffer.logindemo.Utils.LogUtils;
import schaffer.logindemo.Utils.ToastUtils;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class BaseActivity extends AutoLayoutActivity implements RetrofitService {
    /**
     * 返回上一个页面
     *
     * @param view
     */
    public void fanhui(View view) {
        finish();
    }

    public Context context;
    public String mobile_num;
    public int code_type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    /**
     * 发送验证码的操作
     *
     * @param v
     */
    public void sendverifyCode(View v) {
        if (mobile_num != null && code_type != 0) {
            sendSms(mobile_num, code_type).enqueue(new Callback<SendSmsDataBean>() {
                @Override
                public void onResponse(Call<SendSmsDataBean> call, Response<SendSmsDataBean> response) {
                    if (response.isSuccessful()) {
                        SendSmsDataBean body = response.body();
                        String message = body.getMessage();
                        LogUtils.w(message + "," + body.getCode() + "," + body.getData());
                        //手机号已注册,请勿重复注册
                        if (message.equals("手机号未注册,无法进行找回密码操作")) {
                            Dialog_register_notify register_notify = new Dialog_register_notify(context);
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
                    if (t.getCause() == null && t.getMessage() == null) {
//                        ToastUtils.shortNotify("请稍后");
                        LogUtils.w("正在发送短信...");
                    }
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

    public void handleMsg(Message msg) {

    }

    @Override
    public void finish() {
        handler.removeCallbacksAndMessages(null);
        super.finish();
    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    public void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;
                        Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (location[1] + scrollToView
                                    .getHeight()) - rect.bottom;
                            root.scrollTo(0, srollHeight);
                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }

    @Override
    public Call<PhoneLoginDataBean> login(@Field("mobile") long mobile, @Field("password") String password, @Field("device_num") String device_num) {
        Call<PhoneLoginDataBean> call = RetrofitUtils.getStringService().login(mobile, password, device_num);
        return call;
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

