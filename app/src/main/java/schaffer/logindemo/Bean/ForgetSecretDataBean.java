package schaffer.logindemo.bean;

import java.util.List;

public class ForgetSecretDataBean {
    //只用查看message
    /**
     * message : OK
     * data : []
     * code : 0
     */

    private String message;
    private int code;
    private List<?> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ForgetSecretDataBean{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
