package shop.mtcoding.blog.model.resume;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.user.User;

import java.sql.Timestamp;

public class ResumeRequest {


    @Data
    @NoArgsConstructor
    public static class ResumeWriterDTO {
        private User user;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private Boolean isPublic;
        private Timestamp createdAt;

    }



}