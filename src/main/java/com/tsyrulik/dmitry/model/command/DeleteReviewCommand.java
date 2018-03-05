package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ReviewReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteReviewCommand implements Command{
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_REVIEW_ID = "reviewId";
    private static final String PARAM_REVIEWS = "reviews";
    private static final String PARAM_CLIENT = "client";
    private static final String PARAM_USER = "user";
    private static final String PARAM_ERROR_MESSAGE = "MessageReview";
    private static final String PATH_PAGE_REVIEW = "/jsp/client/review.jsp";

    private ReviewReceiver receiver;

    public DeleteReviewCommand(ReviewReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String page;
        Client client = (Client) request.getSession().getAttribute(PARAM_CLIENT);
        int reviewId = Integer.valueOf(request.getParameter(PARAM_REVIEW_ID));
        request.getSession().setAttribute(PARAM_ERROR_MESSAGE, null);
        try {
            receiver.deleteReview(reviewId);
            request.getSession().setAttribute(PARAM_REVIEWS, receiver.findAllReviews());
            page = PATH_PAGE_REVIEW;
            LOGGER.log(Level.INFO, "Deleting review by  " + client.getEmail());
            return new CommandPair(CommandPair.DispatchType.REDIRECT, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}