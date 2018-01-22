package com.tsyrulik.dmitry.model.pool;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Properties;

public class Main {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";
    private final static String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    private static final String PROPERTY_PATH = "src/main/resources/dbConfig.properties";

    public static void main(String[ ] args) {

       Driver driver = null;
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Error of registration driver");
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){

//            statement.execute("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
//                    "values (20, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");

            //statement.executeUpdate("UPDATE new_fitness_center.user set user.name = \"Dimasik\" where user.iduser = 12 ");

//            statement.addBatch("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
//                   "values (21, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
//
//            statement.addBatch("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
//                    "values (22, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
//
//            statement.executeBatch();
//            statement.clearBatch();

            //ResultSet resultSet = statement.executeQuery("SELECT * FROM new_fitness_center.user");
            Properties properties = new Properties();
         //   properties.load(getClass().getClassLoader().getResource(PROPERTY_PATH));
            System.out.println(PROPERTY_PATH);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}
