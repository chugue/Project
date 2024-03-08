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
        private Timestamp createdAt;
        private List<String> skill;
    }



    @Data
    public static class UserViewDTO{
        private Integer id;
        private String title;
        private String edu;
        private String area;
        private String career;


        public UserViewDTO(Integer id, String title, String edu, String area, String career) {
            this.id = id;
            this.title = title;
            this.edu = edu;
            this.area = area;
            this.career = career;
        }

        private List<SkillRequest.ResumeSkillDTO> skillList;
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