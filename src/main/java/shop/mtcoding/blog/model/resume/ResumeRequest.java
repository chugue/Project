package shop.mtcoding.blog.model.resume;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {

    @Data
    @NoArgsConstructor
    public static class ResumeWriterDTO {
        private Integer id;
        private Integer userId;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private Boolean isPublic;
        private List<String> skill = new ArrayList<>();
        private Timestamp createdAt;
    }

    @Data
    public static class UserViewDTO {
        private Integer id;
        private Integer userId;
        private String title;
        private String edu;
        private String area;
        private Integer resumeId;
        private String career;
        private List<SkillRequest.UserskillDTO> skillList = new ArrayList<>();

    }

    @Builder
    @Data
    public static class ResumeUpdateDTO {
        private String area;
        private String career;
        private String edu;
        private String introduce;
        private String portLink;
        private String title;
        // private Integer isPublic;
        private Integer id;

//       private List<String> skills;

    }


}