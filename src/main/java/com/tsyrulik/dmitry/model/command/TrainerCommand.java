package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Appointment;
import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Exercises;
import com.tsyrulik.dmitry.model.entity.Food;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import com.tsyrulik.dmitry.model.util.ParserToLocalTime;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TrainerCommand implements Command {
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
    public String execute(HttpServletRequest request) throws CommandFitnessException {
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
        try {
            if(checkbox != null){
                for (int i = 0; i < checkbox.length; i++){
                    System.out.println(checkbox[i]);
                    Client currentClient = clientReceiver.findClientByEmail(checkbox[i]);
                    clients.add(currentClient);
                }
            }

            for(int i = 0; i < clients.size(); i++) {
                Food food = new Food();
                Exercises exercises = new Exercises();
                if (!nameOfDish.isEmpty() && !dataOfReceipt.isEmpty() && !timeOfReceipt.isEmpty()){
                    food = new Food(nameOfDish, Date.valueOf(dataOfReceipt).toLocalDate(), ParserToLocalTime.parse(timeOfReceipt));
                }
               if (!muscleGroup.isEmpty() && !nameOfExercises.isEmpty() && !equipment.isEmpty()){
                   exercises = new Exercises(muscleGroup, nameOfExercises, equipment);
               }

                List<Appointment> appointment = clientReceiver.findAllAppointmentForClient(clients.get(i).getIdClient());
                        //new Appointment(exercises.getIdExercises(), food.getIdFood(), clients.get(i).getIdClient());

                if (actionButtonFood != null) {
                    switch (actionButtonFood) {
                        case "Add Food": {
                            food = trainerReceiver.createFoodForClient(food);
                            trainerReceiver.createAppointmentsForClient(new Appointment(exercises.getIdExercises(), food.getIdFood(), clients.get(i).getIdClient()));
                            break;
                        }
                        case "Delete Food": {
                            trainerReceiver.deleteFoodById(food.getIdFood());
                            // trainerReceiver.deleteAppointmentsById(appointment.getAppIdAppointment());//???
                            break;
                        }
                        case "Update Food": {
                            food.setIdFood(appointment.get(0).getAppIdFood());
                            trainerReceiver.updateFood(food);
                            break;
                        }
                    }
                }

                if (actionButtonExercise != null) {
                    switch (actionButtonExercise) {
                        case "Add Exercise": {
                            exercises = trainerReceiver.createExercisesForClient(exercises);
                            trainerReceiver.createAppointmentsForClient(new Appointment(exercises.getIdExercises(),
                                    food.getIdFood(), clients.get(i).getIdClient()));
                            break;
                        }
                        case "Delete Exercise": {
                            trainerReceiver.deleteExercisesById(exercises.getIdExercises());
                           // trainerReceiver.deleteAppointmentsById(appointment.getAppIdAppointment());//???
                            break;
                        }
                        case "Update Exercise": {
                            exercises.setIdExercises(appointment.get(0).getAppIdExercises());
                            trainerReceiver.updateExercises(exercises);
                            break;
                        }
                    }
                }
                request.getSession().setAttribute(PARAM_EXERCISES, clientReceiver.findAllExercisesForClients(clients.get(i).getIdClient()));
                request.getSession().setAttribute(PARAM_FOOD, clientReceiver.findAllFoodForClients(clients.get(i).getIdClient()));
                request.getSession().setAttribute(PARAM_CLIENTS, clientReceiver.findAllClients());
            }
            page = TRAINER_CABINET;
            return page;
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}