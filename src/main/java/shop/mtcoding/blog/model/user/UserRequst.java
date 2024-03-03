package shop.mtcoding.blog.model.user;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

public class UserRequst {

    @Data
    public static class UserDto {
        private Integer id;
        private String email;
        private String myName;
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
