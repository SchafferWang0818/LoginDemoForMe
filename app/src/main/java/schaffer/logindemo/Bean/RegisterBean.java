package schaffer.logindemo.Bean;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class RegisterBean {
    //http://git.ychlink.com/api/register

    //    username 	字符串 	Y 		用户名5-50字符
//    mobile 	整型 	Y 		手机号 以1,3,5,7,9
//    mobile_code 	整型 	Y 		5位验证码，暂时随意5位
//    password 	字符串 	Y 		6-12数字字母混合
    public String username;
    public int mobile;
    public int mobile_code;
    public String password;

    public RegisterBean(String username, int mobile, int mobile_code, String password) {
        this.username = username;
        this.mobile = mobile;
        this.mobile_code = mobile_code;
        this.password = password;
    }

    public RegisterBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
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
