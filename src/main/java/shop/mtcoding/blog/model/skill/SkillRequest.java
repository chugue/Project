package shop.mtcoding.blog.model.skill;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

public class SkillRequest {
    @Data
    @Builder
    public static class CompskillDTO{
        private String name;
        private String color;
    }

    @Data
    @Builder
    public static class UserskillDTO{
        private String name;
        private String color;
    }

    @Data
    public static class ApplyskillDTO{
        private String name;
        private String color;
    }
}
