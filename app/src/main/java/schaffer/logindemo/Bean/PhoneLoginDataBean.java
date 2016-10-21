package schaffer.logindemo.Bean;

import java.io.Serializable;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class PhoneLoginDataBean implements Serializable{
    //登录返回值
//    sex 用户类型：0男 1女
//    contact_way： 联系方式
//    emergency_contact：紧急人姓名
//    relation：与本人关系
//    emergency_way：紧急人联系方式
//    login_type：用户类型：1手机号登录 2微信 3QQ 4微博
//    type：用户身份类型 0普通用户 1为老师
//    audit_status：用户审核情况描述：1未提交身份证(一般用户) 2审核中 3审核通过 4审核不通过
//    status:用户状态  0正常  1禁用  禁用不允许登录
//
//
//    返回用户信息和token，以后每次和用户相关的操作都需要在header中带上token，
//    形式如下：
//
//    Authorization:
//    Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6XC9cL3d3dy5kZW1vLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ3NDI4NTQwNCwiZXhwIjoxNDc0Mjg5MDA0LCJuYmYiOjE0NzQyODU0MDQsImp0aSI6IjBkM2UwMDY3ZjA0ZTBjZWMxMTJmNmEwMGU4OTAwMjEzIn0.8VrTdInDWAOnnm26PYvZo6qt4J3uUHMJ3uwZX9U6mlk
//
//    token的参数为Authorization，参数值为Bearer + 一个空格 + 登录后获取的token
//    token有效期为4周，
//    失效后，需要重新登录获取，
//    获取后，前端保存记录




    /**
     * message : OK
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3d3dy5kZW1vLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ3NDU0MDQxMSwiZXhwIjoxNDc0NTQ0MDExLCJuYmYiOjE0NzQ1NDA0MTEsImp0aSI6ImY0YWQwOTI2OGQzMjc1YzgyNjU1YmY0ODI3OWY1YzlhIn0.M0-Ps_3A2p4oHaD5AHtCkINx8GEAaSWtNyy0ZSTt4ZQ","user":{"id":1,"username":"xxxa3355555","mobile":"15801519725","avatar":"www.baidu.com11223333","real_name":"","sex":1,"birthday":0,"contact_way":2147483647,"address":"","emergency_contact":"2222","relation":"3333","emergency_way":0,"login_type":1,"type":0,"audit_status":1,"status":0,"created_at":"2016-09-22 08:43:44","updated_at":"2016-09-22 10:27:17"}}
     * code : 0
     */

    private String message;
    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3d3dy5kZW1vLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ3NDU0MDQxMSwiZXhwIjoxNDc0NTQ0MDExLCJuYmYiOjE0NzQ1NDA0MTEsImp0aSI6ImY0YWQwOTI2OGQzMjc1YzgyNjU1YmY0ODI3OWY1YzlhIn0.M0-Ps_3A2p4oHaD5AHtCkINx8GEAaSWtNyy0ZSTt4ZQ
     * user : {"id":1,"username":"xxxa3355555","mobile":"15801519725","avatar":"www.baidu.com11223333","real_name":"","sex":1,"birthday":0,"contact_way":2147483647,"address":"","emergency_contact":"2222","relation":"3333","emergency_way":0,"login_type":1,"type":0,"audit_status":1,"status":0,"created_at":"2016-09-22 08:43:44","updated_at":"2016-09-22 10:27:17"}
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
        private String token;
        /**
         * id : 1
         * username : xxxa3355555
         * mobile : 15801519725
         * avatar : www.baidu.com11223333
         * real_name :
         * sex : 1
         * birthday : 0
         * contact_way : 2147483647
         * address :
         * emergency_contact : 2222
         * relation : 3333
         * emergency_way : 0
         * login_type : 1
         * type : 0
         * audit_status : 1
         * status : 0
         * created_at : 2016-09-22 08:43:44
         * updated_at : 2016-09-22 10:27:17
         */

        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "token='" + token + '\'' +
                    ", user=" + user +
                    '}';
        }

        public static class UserBean {
            private int id;
            private String username;
            private String mobile;
            private String avatar;
            private String real_name;
            private int sex;
            private int birthday;
            private int contact_way;
            private String address;
            private String emergency_contact;
            private String relation;
            private int emergency_way;
            private int login_type;
            private int type;
            private int audit_status;
            private int status;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }

            public int getContact_way() {
                return contact_way;
            }

            public void setContact_way(int contact_way) {
                this.contact_way = contact_way;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getEmergency_contact() {
                return emergency_contact;
            }

            public void setEmergency_contact(String emergency_contact) {
                this.emergency_contact = emergency_contact;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public int getEmergency_way() {
                return emergency_way;
            }

            public void setEmergency_way(int emergency_way) {
                this.emergency_way = emergency_way;
            }

            public int getLogin_type() {
                return login_type;
            }

            public void setLogin_type(int login_type) {
                this.login_type = login_type;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getAudit_status() {
                return audit_status;
            }

            public void setAudit_status(int audit_status) {
                this.audit_status = audit_status;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            @Override
            public String toString() {
                return "UserBean{" +
                        "id=" + id +
                        ", username='" + username + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", real_name='" + real_name + '\'' +
                        ", sex=" + sex +
                        ", birthday=" + birthday +
                        ", contact_way=" + contact_way +
                        ", address='" + address + '\'' +
                        ", emergency_contact='" + emergency_contact + '\'' +
                        ", relation='" + relation + '\'' +
                        ", emergency_way=" + emergency_way +
                        ", login_type=" + login_type +
                        ", type=" + type +
                        ", audit_status=" + audit_status +
                        ", status=" + status +
                        ", created_at='" + created_at + '\'' +
                        ", updated_at='" + updated_at + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "PhoneLoginDataBean{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}
