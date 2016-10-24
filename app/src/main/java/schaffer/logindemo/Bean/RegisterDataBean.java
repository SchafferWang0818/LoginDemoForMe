package schaffer.logindemo.bean;



public class RegisterDataBean {

    /**
     * message : ErrorCode.0
     * data : {"username":"11444222","mobile":"15801519757","updated_at":"2016-09-21 07:58:00","created_at":"2016-09-21 07:58:00","id":2}
     * code : 0
     */

    private String message;
    /**
     * username : 11444222
     * mobile : 15801519757
     * updated_at : 2016-09-21 07:58:00
     * created_at : 2016-09-21 07:58:00
     * id : 2
     */

    private DataBean data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        private String username;
        private String mobile;
        private String updated_at;
        private String created_at;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
