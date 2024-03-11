package shop.mtcoding.blog.model.apply;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ApplyRequest {

    @Data
    public static class saveDTO {
        private Integer resumeId;
        private Integer jobsId;
        private String isPass;

        public saveDTO() {
            this.isPass = "대기중";
        }
    }

    @AllArgsConstructor
    @Data
    public static class JobsIdAndResumeIdDTO {
        private String isPass;
    }
}
