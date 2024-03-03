package shop.mtcoding.blog.model.skill;

import lombok.Builder;
import lombok.Data;

public class SkillRequest {
    @Data
    @Builder
    public static class CompskillDTO{
        private String name;
        private String color;
    }
}
