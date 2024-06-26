package com.growbiz.backend.ReviewsAndRatings.service;

import com.growbiz.backend.RequestResponse.ReviewsAndRatings.ReviewsAndRatingsRequest;
import com.growbiz.backend.ReviewsAndRatings.models.ReviewsAndRatings;

import java.util.List;

public interface IReviewsAndRatingsService {
    ReviewsAndRatings getReviewAndRatingById(Long reviewAndRatingId);

    List<ReviewsAndRatings> getReviewsAndRatingsByServiceId(Long serviceId);

    List<ReviewsAndRatings> fetchReviewsAndRatingsList();

    ReviewsAndRatings addReviewAndRating(ReviewsAndRatingsRequest newReviewsAndRatingsRequest);

    ReviewsAndRatings updateReviewAndRating(ReviewsAndRatingsRequest reviewsAndRatingsRequest);

    Boolean deleteReviewAndRating(Long reviewAndRatingID);
}
