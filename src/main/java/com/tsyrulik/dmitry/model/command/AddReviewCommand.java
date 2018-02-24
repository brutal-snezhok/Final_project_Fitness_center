package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ReviewReceiver;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import com.tsyrulik.dmitry.model.validator.SignUpValdator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddReviewCommand implements Command{
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_CLIENT ="client";
    private static final String PARAM_MARK ="mark";
    private static final String PARAM_COMMENT ="comment";
    private static final String PARAM_REVIEWS ="reviews";
    private static final String PARAM_ERROR_MESSAGE ="MessageReview";
    private static final String PATH_PAGE_REVIEW = "/jsp/client/review.jsp";


    private ReviewReceiver receiver;

    public AddReviewCommand(ReviewReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandFitnessException {
        String page = null;
        long idClient = ((Client) request.getSession().getAttribute(PARAM_CLIENT)).getIdClient();
        int mark = Integer.valueOf(request.getParameter(PARAM_MARK));
        String textReview = request.getParameter(PARAM_COMMENT);

        request.getSession().setAttribute(PARAM_ERROR_MESSAGE,null);

        try {
            if(SignUpValdator.isCommentCorrect(textReview)) {
                receiver.insertReview((int) idClient, textReview, mark);
                LOGGER.log(Level.INFO,"Adding new review");
            }
            else{
                request.getSession().setAttribute(PARAM_ERROR_MESSAGE,
                        MessageManager.getMessage("message.reviewError"));
                LOGGER.log(Level.INFO,"Incorrect review");
            }
            request.getSession().setAttribute(PARAM_REVIEWS, receiver.findAllReviews());
            page = PATH_PAGE_REVIEW;
            return page;
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}
