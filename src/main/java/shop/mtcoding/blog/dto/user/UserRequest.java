package shop.mtcoding.blog.dto.user;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

public class UserRequest {

    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }

    @Data
    public static class JoinDTO{
        private String email;
        private String myName;
        private String password;
        private Date birth;
        private String tel;
        private Integer role;
    }

    @Data
    @Builder
    public static class UserAllDTO{

        private Integer id;
        private String email;
        private String myName;
        private String password;
        private String phone;
        private String address;
        private Date birth;
        private String businessNumber;
        private String photo;
        private String compName;
        private String homepage;
        private Integer role;
        private Timestamp createdAt;
    }



}
