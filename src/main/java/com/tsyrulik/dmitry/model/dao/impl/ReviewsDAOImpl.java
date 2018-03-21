package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.dao.ReviewsDAO;
import com.tsyrulik.dmitry.model.entity.Review;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDAOImpl implements ReviewsDAO{
    private static final String SQL_SELECT_REVIEWS =
            "SELECT `idreviews`, `client_idclient`, `text_review`, `mark` FROM `reviews`;";

    private static final String SQL_INSERT_REVIEW =
            "INSERT INTO `reviews` (`client_idclient`, `text_review`, `mark`) VALUES (?, ?, ?);";

    private static final String SQL_DELETE_REVIEW =
            "DELETE FROM `reviews` WHERE `idreviews`=?;";


    @Override
    public List<Review> findAllReviews() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SQL_SELECT_REVIEWS);
            ResultSet resultSet = statement.getResultSet();
            List<Review> reviewsList = new ArrayList<>();
            while (resultSet.next()) {
                reviewsList.add(createReviewFromResult(resultSet));
            }
            return reviewsList;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    private Review createReviewFromResult(ResultSet resultSet) throws SQLException {
        Review review = new Review(resultSet.getInt(1),resultSet.getInt(2),
                resultSet.getString(3), resultSet.getInt(4));
        return review;
    }

    @Override
    public void insertNewReview(int clientId, String textReview, int mark) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_REVIEW)) {

            preparedStatement.setInt(1, clientId);
            preparedStatement.setString(2, textReview);
            preparedStatement.setInt(3, mark);

            preparedStatement.executeUpdate();
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteReview(int reviewId) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_REVIEW)) {
            preparedStatement.setString(1, String.valueOf(reviewId));
            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }

    }
}