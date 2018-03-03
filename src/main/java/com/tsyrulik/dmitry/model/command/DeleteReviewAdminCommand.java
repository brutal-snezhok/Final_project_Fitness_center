package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ReviewReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteReviewAdminCommand implements Command{
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_REVIEW_ID = "reviewId";
    private static final String PARAM_REVIEWS = "reviews";
    private static final String PARAM_USER = "user";
    private static final String PARAM_ERROR_MESSAGE = "MessageReview";
    private static final String PATH_ADMIN_REVIEW = "/jsp/admin/adminReview.jsp";

    private ReviewReceiver receiver;

    public DeleteReviewAdminCommand(ReviewReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String page;
        User user = (User)request.getSession().getAttribute(PARAM_USER);
        int reviewId = Integer.valueOf(request.getParameter(PARAM_REVIEW_ID));
        request.getSession().setAttribute(PARAM_ERROR_MESSAGE, null);
        try {
            receiver.deleteReview(reviewId);
            request.getSession().setAttribute(PARAM_REVIEWS, receiver.findAllReviews());
            page = PATH_ADMIN_REVIEW;
            LOGGER.log(Level.INFO, "Deleting review by  " + user.getEmail());
            return new CommandPair(CommandPair.DispatchType.REDIRECT, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}