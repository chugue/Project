package shop.mtcoding.blog.model.skill;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class SkillResponse {

    @Data
    public static class SkillCheckedDTO {
        private boolean java = false;
        private boolean javaScript = false;
        private boolean spring = false;
        private boolean htmlCss = false;
        private boolean jquery = false;
        private boolean jsp = false;
        private boolean vueJs = false;
        private boolean oracle = false;
        private boolean mySql = false;
        private boolean react = false;

        public SkillCheckedDTO (List<String> skillNames){
            for (String skillName : skillNames){
                if(skillName.equals("Java")){
                    this.java = true;
                }else if(skillName.equals("JavaScript")){
                    this.javaScript = true;
                }else if(skillName.equals("Spring")){
                    this.spring = true;
                }else if(skillName.equals("HTML/CSS")){
                    this.htmlCss = true;
                }else if(skillName.equals("jquery")){
                    this.jquery = true;
                }else if(skillName.equals("JSP")){
                    this.jsp = true;
                } else if(skillName.equals("Vue.js")){
                    this.vueJs = true;
                }else if(skillName.equals("Oracle")){
                    this.oracle = true;
                }else if(skillName.equals("MysQL")){
                    this.mySql = true;
                }else if(skillName.equals("React")){
                    this.react = true;
                }
            }
        }
    }
}
