package shop.mtcoding.blog.model.apply;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

public class ApplyRequest {

    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO {

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
    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO2 {
        private Date deadLine;
        private Integer id;
        private Integer userId;
        private Timestamp createdAt;
        private String area;
        private String career;
        private String content;
        private String edu;
        private String task;
        private String title;
        private Integer resumeId;
        private Integer status;
    }

    // -------------------------------------------------------------------------------------------------------- 추가
    @Data
    public static class saveDTO {
        private Integer resumeId;
        private Integer jobsId;
        private Integer isPass;

        public saveDTO() {
            this.isPass = 1;
        }
    }

    @AllArgsConstructor
    @Data
    public static class JobsIdAndResumeIdDTO {
        private Integer isPass;
    }
}
