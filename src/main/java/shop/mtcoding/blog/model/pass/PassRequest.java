package shop.mtcoding.blog.model.pass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

public class PassRequest {

    @AllArgsConstructor
    @Data
    public static class PassResumeJobsDTO{
        private Integer resumeId;
        private Integer userId;
        private Timestamp createdAt;
        private String area;
        private String career;
        private String edu;
        private String introduce;
        private String portLink;
        private String title;
        private Integer status;
        private Integer jobsId;
    }
}
