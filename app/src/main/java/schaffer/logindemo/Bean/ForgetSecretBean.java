package schaffer.logindemo.Bean;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class ForgetSecretBean {
    //http://git.ychlink.com/api/forgotPassword
    //手机,验证码,新密码
    public String mobile;
    public int mobile_code;
    public String password;

    public ForgetSecretBean(String mobile, int mobile_code, String password) {
        this.mobile = mobile;
        this.mobile_code = mobile_code;
        this.password = password;
    }

    public ForgetSecretBean() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobile_code() {
        return mobile_code;
    }

    public void setMobile_code(int mobile_code) {
        this.mobile_code = mobile_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

