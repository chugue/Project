package shop.mtcoding.blog.model.apply;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ApplyRequest {

    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO{
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
    public static class ApplyResumeJobsDTO2{
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
    }

