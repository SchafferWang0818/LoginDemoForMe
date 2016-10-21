package schaffer.logindemo.Bean;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class PhoneLoginBean {
    // http://git.ychlink.com/api/login
    //手机号,密码,设备号
    public long mobile;
    public String password;
    public String device_num;

    public PhoneLoginBean(long mobile, String password, String device_num) {
        this.mobile = mobile;
        this.password = password;
        this.device_num = device_num;
    }

    public PhoneLoginBean() {
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_num() {
        return device_num;
    }

    public void setDevice_num(String device_num) {
        this.device_num = device_num;
    }
}
