package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AdminTrainerCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_TRAINERS = "trainers";
    private static final String ADMIN_PAGE = "/jsp/admin/admin_page.jsp";
    private TrainerReceiver receiverTrainer;

    private static final String PARAM_ID_TRAINER = "idTrainer";
    private static final String PARAM_NAME_TRAINER = "nameTrainer";
    private static final String PARAM_SURNAME_TRAINER = "surnameTrainer";
    private static final String PARAM_YEARS_OLD_TRAINER = "yearOldTrainer";
    private static final String PARAM_SEX_TRAINER = "sexTrainer";
    private static final String PARAM_EDUCATION_TRAINER = "education";
    private static final String PARAM_COST_PER_LESSON_TRAINER = "costPerLesson";
    private static final String PARAM_CHECKBOX_TRAINER = "selectTrainer";


    public AdminTrainerCommand(TrainerReceiver receiverTrainer) {
        this.receiverTrainer = receiverTrainer;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String[] checkboxTrainer = request.getParameterValues(PARAM_CHECKBOX_TRAINER);
        ArrayList<Trainer> trainers = new ArrayList<>();

        String actionButtonRemoveTrainer = request.getParameter("RemoveTrainerButton");
        String actionButtonUpdateTrainer = request.getParameter("UpdateTrainerButton");
        String page;
        try{
            if(checkboxTrainer != null){
                for (int i = 0; i < checkboxTrainer.length; i++){
                    Trainer currentTrainer = receiverTrainer.findTrainerByEmail(checkboxTrainer[i]);
                    LOGGER.log(Level.INFO,"Find trainer");
                    trainers.add(currentTrainer);
                }
            }

            for(int i = 0; i < trainers.size(); i++) {
                if (actionButtonRemoveTrainer != null) {
                    receiverTrainer.deleteTrainer(trainers.get(i).getIdTrainer());
                    LOGGER.log(Level.INFO,"Delete trainer");
                }

                if (actionButtonUpdateTrainer != null){
                    int idTrainer= Integer.parseInt(request.getParameter(PARAM_ID_TRAINER));
                    String name = request.getParameter(PARAM_NAME_TRAINER);
                    String surname = request.getParameter(PARAM_SURNAME_TRAINER);
                    String yearsOld = request.getParameter(PARAM_YEARS_OLD_TRAINER);
                    String sex = request.getParameter(PARAM_SEX_TRAINER);
                    String education = request.getParameter(PARAM_EDUCATION_TRAINER);
                    String costPerLesson = request.getParameter(PARAM_COST_PER_LESSON_TRAINER);
                    Trainer trainer = receiverTrainer.findTrainerById(idTrainer);
                    trainer.setName(name);
                    trainer.setSurname(surname);
                    trainer.setYearOld(Integer.parseInt(yearsOld));
                    trainer.setSex(sex);
                    trainer.setEducation(education);
                    trainer.setCostPerHour(new BigDecimal(costPerLesson));
                    receiverTrainer.updateTrainer(trainer);
                    LOGGER.log(Level.INFO,"Update trainer");
                }
            }

            request.getSession().setAttribute(PARAM_TRAINERS, receiverTrainer.findAllTrainers());
            page = ADMIN_PAGE;
            return new CommandPair(CommandPair.DispatchType.REDIRECT, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }

    }
}