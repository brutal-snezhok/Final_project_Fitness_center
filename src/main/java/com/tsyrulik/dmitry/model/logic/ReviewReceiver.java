package com.tsyrulik.dmitry.model.logic;

import com.tsyrulik.dmitry.model.dao.ReviewsDAO;
import com.tsyrulik.dmitry.model.dao.impl.ReviewsDAOImpl;
import com.tsyrulik.dmitry.model.entity.Review;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;

import java.util.List;

/**
 * The Class ReviewReceiver.
 */
public class ReviewReceiver {

    public List<Review> findAllReviews() throws LogicFitnessException {
        ReviewsDAO reviewsDAO = new ReviewsDAOImpl();
        try {
            return reviewsDAO.findAllReviews();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e.getMessage(), e);
        }
    }

    public void insertReview(int clientId, String textReview, int mark) throws  LogicFitnessException {
        ReviewsDAO reviewsDAO = new ReviewsDAOImpl();
        try {
            reviewsDAO.insertNewReview(clientId, textReview, mark);
        }  catch (DAOFitnessException e) {
            throw new LogicFitnessException(e.getMessage(), e);
        }
    }

    public void deleteReview(int reviewId) throws LogicFitnessException {
        ReviewsDAO reviewsDAO = new ReviewsDAOImpl();
        try {
            reviewsDAO.deleteReview(reviewId);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e.getMessage(), e);
        }
    }
}