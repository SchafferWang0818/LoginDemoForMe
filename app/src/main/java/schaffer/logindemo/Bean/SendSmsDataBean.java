package schaffer.logindemo.Bean;

import java.util.List;

/**
 * Created by SchafferW on 2016/10/21.
 */

public class SendSmsDataBean {


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
}
