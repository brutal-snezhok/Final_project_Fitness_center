package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.*;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import com.tsyrulik.dmitry.model.util.ParserToLocalTime;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TrainerCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_FOOD = "food";
    private static final String PARAM_EXERCISES = "exercises";
    private static final String PARAM_CLIENTS = "clients";
    private static final String TRAINER_CABINET = "/jsp/trainer/trainer_cabinet.jsp";
    private ClientReceiver clientReceiver;
    private TrainerReceiver trainerReceiver;

    private static final String PARAM_NAME_OF_DISH = "nameOfDish";
    private static final String PARAM_DATA_OF_RECEIPT = "dataOfReceipt";
    private static final String PARAM_TIME_OF_RECEIPT = "timeOfReceipt";

    private static final String PARAM_MUSCLE_GROUP = "muscleGroup";
    private static final String PARAM_NAME_OF_EXERCISES = "nameOfExercises";
    private static final String PARAM_EQUIPMENT = "equipment";

    private static final String PARAM_CHECKBOX = "selectClient";


    public TrainerCommand(ClientReceiver clientReceiver, TrainerReceiver trainerReceiver) {
        this.clientReceiver = clientReceiver;
        this.trainerReceiver = trainerReceiver;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String[] checkbox = request.getParameterValues(PARAM_CHECKBOX);
        ArrayList<Client> clients = new ArrayList<>();

        String actionButtonFood = request.getParameter("actionFood");
        String actionButtonExercise = request.getParameter("actionExercise");

        String nameOfDish = request.getParameter(PARAM_NAME_OF_DISH);
        String dataOfReceipt = request.getParameter(PARAM_DATA_OF_RECEIPT);
        String timeOfReceipt = request.getParameter(PARAM_TIME_OF_RECEIPT);

        String muscleGroup = request.getParameter(PARAM_MUSCLE_GROUP);
        String nameOfExercises = request.getParameter(PARAM_NAME_OF_EXERCISES);
        String equipment = request.getParameter(PARAM_EQUIPMENT);
        String page;
        List<ClientInf> clientInfList = new ArrayList<>();
        try {
            if (checkbox != null) {
                for (int i = 0; i < checkbox.length; i++) {
                    System.out.println(checkbox[i]);
                    Client currentClient = clientReceiver.findClientByEmail(checkbox[i]);
                    clients.add(currentClient);
                }
            }

            for (int i = 0; i < clients.size(); i++) {
                Food food = new Food();
                Exercises exercises = new Exercises();
                if (!nameOfDish.isEmpty() && !dataOfReceipt.isEmpty() && !timeOfReceipt.isEmpty()) {
                    food = new Food(nameOfDish, Date.valueOf(dataOfReceipt).toLocalDate(), ParserToLocalTime.parse(timeOfReceipt));
                }
                if (!muscleGroup.isEmpty() && !nameOfExercises.isEmpty() && !equipment.isEmpty()) {
                    exercises = new Exercises(muscleGroup, nameOfExercises, equipment);
                }

                List<Appointment> appointment = clientReceiver.findAllAppointmentForClient(clients.get(i).getIdClient());
                //new Appointment(exercises.getIdExercises(), food.getIdFood(), clients.get(i).getIdClient());

                if (actionButtonFood != null) {
                    switch (actionButtonFood) {
                        case "Add food": {
                            food = trainerReceiver.createFoodForClient(food);
                            LOGGER.log(Level.INFO, "Create food for client" + clients.get(i).getEmail());
                            trainerReceiver.createAppointmentsForClient(new Appointment(-1, food.getIdFood(), clients.get(i).getIdClient()));
                            break;
                        }
                        case "Delete food": {
                            food = clientReceiver.findFoodForClient(clients.get(i).getIdClient());
                            for (int ap = 0; ap < appointment.size(); ap++) {
                                if (appointment.get(ap).getAppIdFood() == food.getIdFood()) {
                                    appointment.get(ap).setAppIdFood(-1);
                                }
                            }
                            trainerReceiver.deleteFoodById(food.getIdFood());
                            LOGGER.log(Level.INFO, "Delete food for client" + clients.get(i).getEmail());
                            // trainerReceiver.deleteAppointmentsById(appointment.getAppIdAppointment());//???
                            break;
                        }
                        case "Update food": {
                            //food.setIdFood(appointment.get(0).getAppIdFood());
                            food = clientReceiver.findFoodForClient(clients.get(0).getIdClient());
                            trainerReceiver.updateFood(food);
                            LOGGER.log(Level.INFO, "Update food for client" + clients.get(i).getEmail());
                            break;
                        }
                    }
                }

                if (actionButtonExercise != null) {
                    switch (actionButtonExercise) {
                        case "Add exercises": {
                            exercises = trainerReceiver.createExercisesForClient(exercises);
                            LOGGER.log(Level.INFO, "Create exercises for client" + clients.get(i).getEmail());
                            trainerReceiver.createAppointmentsForClient(new Appointment(exercises.getIdExercises(),
                                    -1, clients.get(i).getIdClient()));
                            break;
                        }
                        case "Delete exercises": {
                            trainerReceiver.deleteExercisesById(exercises.getIdExercises());
                            LOGGER.log(Level.INFO, "Delete exercises for client" + clients.get(i).getEmail());
                            // trainerReceiver.deleteAppointmentsById(appointment.getAppIdAppointment());//???
                            break;
                        }
                        case "Update exercises": {
                            exercises.setIdExercises(appointment.get(0).getAppIdExercises());
                            trainerReceiver.updateExercises(exercises);
                            LOGGER.log(Level.INFO, "Update exercises for client" + clients.get(i).getEmail());
                            break;
                        }
                    }
                }
                List<Food> currentFoods = clientReceiver.findAllFoodForClients(clients.get(i).getIdClient());
                List<Exercises> currentExercises = clientReceiver.findAllExercisesForClients(clients.get(i).getIdClient());
                clientInfList.add(new ClientInf(clients.get(i), currentFoods, currentExercises));
            }
            request.getSession().setAttribute("clientInfList", clientInfList);
            page = TRAINER_CABINET;
            return new CommandPair(CommandPair.DispatchType.REDIRECT, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}