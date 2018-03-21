package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.dao.TrainerDAO;
import com.tsyrulik.dmitry.model.entity.Food;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainerDAOImplTest {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";
    private final static String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";

    private List<Trainer> allTrainers;
    private TrainerDAO trainerDAO = new TrainerDAOImpl();


    @BeforeMethod
    public void setUp() throws Exception {
        allTrainers = new ArrayList<>();

        int poolSize = 5;
        ConnectionPool.getInstance(URL, USERNAME, PASSWORD, poolSize);

        ConnectionPool.getInstance(1);
        allTrainers.add(new Trainer(3, "Kosty", "Pyshyk", 35, "M",
                "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "trainer", 1, "кмс", new BigDecimal(10), 3));

    }

    @Test
    public void testFindAllTrainers() throws DAOFitnessException {
        List<Trainer> actual = trainerDAO.findAllTrainers();
        Assert.assertEquals(actual, allTrainers);
    }


    @Test
    public void testFindTrainerById() throws Exception {
        int id = 1;
        Optional<Trainer> trainer = trainerDAO.findTrainerById(id);
        Assert.assertEquals(allTrainers.get(id -1), trainer.get());
    }

    @Test
    public void testFindTrainerByEmail() throws Exception {
        String email = "pyshhyk@gmail.com";
        Optional<Trainer> trainer = trainerDAO.findTrainerByEmail(email);
        Assert.assertEquals(allTrainers.get(0), trainer.get());
    }

    @Test
    public void testUpdateTrainer() throws Exception {
        Trainer expectedTrainer = new Trainer(3, "KostyKosty", "PyshykPyshyk", 53, "F",
                "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "2", 1, "кмс", new BigDecimal(10), 3);
        Trainer actualTrainer = trainerDAO.updateTrainer(expectedTrainer);
        Assert.assertEquals(actualTrainer, expectedTrainer);
        Trainer expectedTrainerTwo = new Trainer(3, "Kosty", "Pyshyk", 35, "M",
                "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "2", 1, "кмс", new BigDecimal(10), 3);
        Trainer actualTrainerTwo = trainerDAO.updateTrainer(expectedTrainerTwo);
        Assert.assertEquals(actualTrainerTwo, expectedTrainerTwo);
    }

    @Test
    public void testDeleteTrainer() throws Exception {
        Trainer trainer = new Trainer(4, "Kirill", "Pavlov", 23, "M",
                "vavl@gmail.com", "58bad6b697dff48f4927941962f23e90", "2", 2,"мс", new BigDecimal(20), 4);
        allTrainers.add(trainer);
        Trainer expectedTrainer =  allTrainers.get(1);
        trainerDAO.createTrainer(trainer);
        Assert.assertEquals(trainer, expectedTrainer);
        allTrainers.remove(1);
        trainerDAO.deleteTrainer(2);
    }

    @Test
    public void createExercisesForClientTest() throws DAOFitnessException {
        Food exercises = new Food((long)10, "салат 'Царский'", LocalDate.of(2017,7,7), LocalTime.of(15,20));
        new TrainerDAOImpl().createFoodForClient(exercises);

    }
}