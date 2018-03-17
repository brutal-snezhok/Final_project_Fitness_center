package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Review;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;

/**
 * The Interface ReviewsDAO.
 */
public interface ReviewsDAO {

    /**
     * Find all.
     *
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Review> findAllReviews() throws DAOFitnessException;

    /**
     * Creates the review.
     *
     * @param clientId the id
     * @param textReview the text
     * @param mark the mark
     * @throws DAOFitnessException the dao fitness exception
     */
    void insertNewReview(int clientId, String textReview, int mark) throws DAOFitnessException;

    /**
     * Delete by id.
     *
     * @param reviewId the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteReview(int reviewId) throws DAOFitnessException;

    void deleteAll()  throws DAOFitnessException;
}
