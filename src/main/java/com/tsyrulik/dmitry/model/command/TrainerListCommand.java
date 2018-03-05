package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TrainerListCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_TRAINERS = "trainers";
    private static final String ADMIN_PAGE = "/jsp/admin/admin_page";
    private TrainerReceiver receiverTrainer;

    public TrainerListCommand(TrainerReceiver receiverTrainer) {
        this.receiverTrainer = receiverTrainer;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String page;
        try {
            List<Trainer> trainers = receiverTrainer.findAllTrainers();
            request.getSession().setAttribute(PARAM_TRAINERS,trainers);
            page = ADMIN_PAGE;
            LOGGER.log(Level.INFO, "Successful searching all trainers operation.");
            return new CommandPair(CommandPair.DispatchType.FORWARD, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}