package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.ReviewDTO;
import pl.pwr.scent_tracker.model.entity.Review;

public class ReviewMapper {
    public static ReviewDTO toReviewDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .perfumeId(review.getPerfume().getId())
                .scent(review.getScent())
                .longevity(review.getLongevity())
                .sillage(review.getSillage())
                .bottle(review.getBottle())
                .value(review.getValue())
                .textPath(review.getTextPath())
                .build();
    }
}
