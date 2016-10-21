package schaffer.logindemo.NetWork;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import schaffer.logindemo.Bean.ForgetSecretDataBean;
import schaffer.logindemo.Bean.PhoneLoginDataBean;
import schaffer.logindemo.Bean.RegisterDataBean;
import schaffer.logindemo.Bean.SendSmsDataBean;

/**
 * Created by SchafferW on 2016/10/20.
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST("api/login")
    Call<PhoneLoginDataBean> login(@Field("mobile") long mobile, @Field("password") String password, @Field("device_num") String device_num);


    //http://git.ychlink.com/api/forgotPassword
    //手机,验证码,新密码
    @FormUrlEncoded
    @POST("api/forgotPassword")
    Call<ForgetSecretDataBean> forget(@Field("mobile") String mobile, @Field("mobile_code") int mobile_code, @Field("password") String password);

    //http://git.ychlink.com/api/register
    @FormUrlEncoded
    @POST("api/register")
    Call<RegisterDataBean> register(@Field("username") String username, @Field("mobile") long mobile, @Field("mobile_code") int mobile_code, @Field("password") String password);

    //api/sendSms
    @FormUrlEncoded
    @POST("api/sendSms")
    Call<SendSmsDataBean> sendSms(@Field("mobile") String mobile, @Field("type") int type);

    //    api/checkSms
    @FormUrlEncoded
    @POST("api/checkSms")
    Call<SendSmsDataBean> verifySms(@Field("mobile") long mobile, @Field("mobile_code") int mobile_code);


}
