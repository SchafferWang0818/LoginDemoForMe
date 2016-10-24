package schaffer.logindemo.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import schaffer.logindemo.bean.ForgetSecretDataBean;
import schaffer.logindemo.bean.PhoneLoginDataBean;
import schaffer.logindemo.bean.RegisterDataBean;
import schaffer.logindemo.bean.SendSmsDataBean;

public interface RetrofitService {
    /**
     * 登录
     *
     * @param mobile     手机号
     * @param password   密码
     * @param device_num 设备号
     * @return call
     */
    @FormUrlEncoded
    @POST("api/login")
    Call<PhoneLoginDataBean> login(@Field("mobile") long mobile, @Field("password") String password, @Field("device_num") String device_num);


    /**
     * 忘记密码
     *
     * @param mobile      手机号
     * @param mobile_code 手机验证码
     * @param password    密码
     * @return call
     */
    @FormUrlEncoded
    @POST("api/forgotPassword")
    Call<ForgetSecretDataBean> forget(@Field("mobile") String mobile, @Field("mobile_code") int mobile_code, @Field("password") String password);

    /**
     * 注册账号
     *
     * @param username    用户昵称
     * @param mobile      手机号
     * @param mobile_code 手机验证码
     * @param password    密码
     * @return call
     */
    @FormUrlEncoded
    @POST("api/register")
    Call<RegisterDataBean> register(@Field("username") String username, @Field("mobile") long mobile, @Field("mobile_code") int mobile_code, @Field("password") String password);

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @param type   验证码种类
     * @return call
     */
    @FormUrlEncoded
    @POST("api/sendSms")
    Call<SendSmsDataBean> sendSms(@Field("mobile") String mobile, @Field("type") int type);

    /**
     * 验证验证码
     *
     * @param mobile      手机号
     * @param mobile_code 验证码
     * @return call
     */
    @FormUrlEncoded
    @POST("api/checkSms")
    Call<SendSmsDataBean> verifySms(@Field("mobile") long mobile, @Field("mobile_code") int mobile_code);


}
