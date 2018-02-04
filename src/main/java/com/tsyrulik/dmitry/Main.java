package com.tsyrulik.dmitry;


import com.tsyrulik.dmitry.model.util.MD5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {

//    private final static String USERNAME = "root";
//    private final static String PASSWORD = "12345";
    private final static String URL = "jdbc:mysql://localhost:3306/new_fitness_center?use=false&useSSL=false";
//    private static final String PROPERTY_PATH = "src/main/resources/dbConfig.properties";

    private static final String FIND_ALL_USERS_SQL = "SELECT * FROM user ; ";
    private static final String PROPERTY_PATH = "/dbConfig.properties";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";

    public Main() throws SQLException {
    }

    public static void main(String[] args) {

        MD5 encrypt = new MD5();

//        System.out.println("11: " + encrypt.encrypt("password11"));
//        System.out.println("12: " + encrypt.encrypt("password12"));
//        System.out.println("13: " + encrypt.encrypt("password13"));
//        System.out.println("14: " + encrypt.encrypt("password14"));
//        System.out.println("15: " + encrypt.encrypt("password15"));
//        System.out.println("16: " + encrypt.encrypt("password16"));
//        System.out.println("17: " + encrypt.encrypt("password17"));
//        System.out.println("18: " + encrypt.encrypt("password18"));
//        System.out.println("19: " + encrypt.encrypt("password19"));
//        System.out.println("20: " + encrypt.encrypt("password20"));
//        System.out.println("21: " + encrypt.encrypt("password21"));
//        System.out.println("22: " + encrypt.encrypt("password22"));
//
//        System.out.println("23: " + encrypt.encrypt("password23"));
//        System.out.println("24: " + encrypt.encrypt("password24"));
//    System.out.println("25: " + encrypt.encrypt("password25"));
    System.out.println("26: " + encrypt.encrypt("finel@yandex.ru"));

//        List<User> users = null;
//
//        try (Connection connection = ConnectionPool.getInstance().getConnection();
//             Statement statement = connection.createStatement()) {
//            users = new ArrayList<>();
//
//            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SQL);
//            while (resultSet.next()) {
//                User user = new User();
//                user.setIdUser(resultSet.getLong(1));
//                user.setName(resultSet.getString(2));
//                user.setSurname(resultSet.getString(3));
//                user.setYearOld(resultSet.getInt(4));
//                user.setSex(resultSet.getString(5));
//                user.setEmail(resultSet.getString(6));
//                user.setPassword(resultSet.getString(7));
//                user.setRole(resultSet.getString(8));
//                users.add(user);
//            }
//            // return users;
//        } catch (SQLException | PoolFitnessException e) {
//            System.out.println("Exception");
//        }
//
//
//        for (User user : users) {
//            System.out.println(user);
//        }

        try(
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement())
        {


            statement.execute("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole )  " +
                    "values (20, \"Nefhmdhng\", \"Tusfgan\", 23, \"M\", \"tukarin@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 2)");

//            //statement.executeUpdate("UPDATE new_fitness_center.user set user.name = \"Dimasik\" where user.iduser = 12 ");
//
//            statement.addBatch("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
//                    "values (21, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
//
//            statement.addBatch("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
//                    "values (22, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
//
//            statement.executeBatch();
//            statement.clearBatch();
//            statement.executeQuery("SELECT `iduser`,`name`,`surname`,`years_old`,`sex`,`email`,`password`,`role_name` AS `role` " +
//                    "FROM `user` LEFT JOIN `role` ON `user`.`role_idrole` = `role`.`idrole` ORDER BY `user`.`iduser`;");
//            ResultSet resultSet = statement.getResultSet();
//
//            while(resultSet.next()){
//                User user = new User();
//                user.setIdUser(resultSet.getLong(1));
//                user.setName(resultSet.getString(2));
//                user.setSurname(resultSet.getString(3));
//                user.setYearOld(resultSet.getInt(4));
//                user.setSex(resultSet.getString(5));
//                user.setEmail(resultSet.getString(6));
//                user.setPassword(resultSet.getString(7));
//                user.setRole(resultSet.getString(8));
//                System.out.println(user);
//            }

            //      Properties properties = new Properties();
//            //   properties.load(getClass().getClassLoader().getResource(PROPERTY_PATH));

        }
         catch (SQLException e) {
             System.out.println("Exception " + e);
        }


    }





}




