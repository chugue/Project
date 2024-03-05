package shop.mtcoding.blog.model.comp;

import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CompRequest {

    @Data
    public static class JobsViewDTO{
        private Integer id;
        private Integer userId;
        private String compName;
        private String title;
        private String task;
        private String career;
        private List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();
        private Integer number;
    }

    @Data
    public static class ResumeViewDTO {
        private Integer id;
        private Integer userId;
        private String myName;
        private String title;
        private String edu;
        private String career;
        private String area;
        private List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();
    }
}
