package shop.mtcoding.blog.model.offer;

import lombok.AllArgsConstructor;
import lombok.Data;

public class OfferResponse {
    @AllArgsConstructor
    @Data
    public static class OfferDetailDTO {
        private Integer id;
        private Boolean isScrap;
    }
}
