package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Review;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;

public interface ReviewsDAO {
    List<Review> findAllReviews() throws DAOFitnessException;

    void insertNewReview(int clientId, String textReview, int mark) throws DAOFitnessException;

    void deleteReview(int reviewId) throws DAOFitnessException;
}
