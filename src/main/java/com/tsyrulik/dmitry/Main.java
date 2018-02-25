package com.tsyrulik.dmitry;


import com.tsyrulik.dmitry.model.entity.UserType;

import java.sql.SQLException;
import java.time.LocalTime;


public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/new_fitness_center?use=false&useSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";

    public Main() throws SQLException {
    }

    public static LocalTime parse(String string){
        String[] strings = string.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        return localTime;

    }
    public static void main(String[] args) {

       //user.getRole().equals(UserType.ADMIN.getTypeName()
        System.out.println("trainer".equals(UserType.TRAINER.getTypeName()));
      //    MD5 encrypt = new MD5();
       // System.out.println("1: " + encrypt.encrypt("password1"));
//        System.out.println("2: " + encrypt.encrypt("password2"));
//        System.out.println("3: " + encrypt.encrypt("password3"));
//        System.out.println("4: " + encrypt.encrypt("password4"));
//        System.out.println("5: " + encrypt.encrypt("password5"));
//        System.out.println("6: " + encrypt.encrypt("password6"));
//        System.out.println("7: " + encrypt.encrypt("password7"));
//        System.out.println("8: " + encrypt.encrypt("password8"));
//        System.out.println("9: " + encrypt.encrypt("password9"));
//        System.out.println("10: " + encrypt.encrypt("password10"));
//
//        System.out.println("11: " + encrypt.encrypt("password11"));
//        System.out.println("12: " + encrypt.encrypt("password12"));
//        System.out.println("13: " + encrypt.encrypt("password13"));
//        System.out.println("14: " + encrypt.encrypt("password14"));
//        System.out.println("15: " + encrypt.encrypt("password15"));
//        System.out.println("16: " + encrypt.encrypt("password16"));
//        System.out.println("17: " + encrypt.encrypt("password17"));
//
//       System.out.println("18: " + encrypt.encrypt("password18"));
//        System.out.println("19: " + encrypt.encrypt("password19"));
//        System.out.println("20: " + encrypt.encrypt("password20"));
//        System.out.println("21: " + encrypt.encrypt("password21"));
//        System.out.println("22: " + encrypt.encrypt("password22"));
//
//        System.out.println("23: " + encrypt.encrypt("password23"));
//        System.out.println("24: " + encrypt.encrypt("password24"));
//    System.out.println("25: " + encrypt.encrypt("password25"));
//    System.out.println("26: " + encrypt.encrypt("finel@yandex.ru"));



//            statement.execute("insert into user(iduser, name, surname, years_old, sex, email, password, role_idrole )  " +
//                    "values (19, \"Федор\", \"Емельянов\", 43, \"M\", \"emelynov@mail.ru\", \"e532ae6f28f4c2be70b500d3d34724eb\", 2)");

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






    }
}




