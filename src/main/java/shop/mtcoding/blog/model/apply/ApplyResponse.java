package shop.mtcoding.blog.model.apply;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.ArrayList;
import java.util.List;

public class ApplyResponse {

    @Data
    public static class ApplyByJobsDTO{
        private Integer id;
        private String myName;
        private String title;
        private String career;
        private List<SkillRequest.ApplyskillDTO> skillList;

        public ApplyByJobsDTO(Integer id, String myName, String title, String career) {
            this.id = id;
            this.myName = myName;
            this.title = title;
            this.career = career;
        }
    }
}
