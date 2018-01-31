package com.tsyrulik.dmitry.model.pool;


import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

//    private final static String USERNAME = "root";
//    private final static String PASSWORD = "12345";
//    private final static String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
//    private static final String PROPERTY_PATH = "src/main/resources/dbConfig.properties";

    public static void main(String[ ] args)  {
        System.out.println("start connection pool");
        ConnectionPool pool = ConnectionPool.getInstance();
        System.out.println("start proxyconnection");
        ProxyConnection connection = (ProxyConnection) pool.getConnection();
        System.out.println("We have got connection from the connection class");
        PreparedStatement statement = null;
        try{
            statement = (PreparedStatement) connection.prepareStatement("SELECT `name`, `surname` FROM new_fitness_center.user ");
            System.out.println("statement select");
            ResultSet rs = statement.executeQuery();
            System.out.println("result set сформирован");
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString("surname") + " " + rs.getString("name"));
            }

            if (rs != null) {
                rs.close();
            }
            if(statement != null){
                statement.close();
            }
            pool.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("SQLExcepstion!!!");
        }



    }

//    MD5 encrypt = new MD5();
//        System.out.println("1: " + encrypt.encrypt("password1"));
//        System.out.println("2: " + encrypt.encrypt("password2"));
//        System.out.println("3: " +  encrypt.encrypt("password3"));
//        System.out.println("4: " + encrypt.encrypt("password4"));
//        System.out.println("5: " + encrypt.encrypt("password5"));
//        System.out.println("6: " + encrypt.encrypt("password6"));
//        System.out.println("7: " + encrypt.encrypt("password7"));
//        System.out.println("8: " + encrypt.encrypt("password8"));
//        System.out.println("9: " + encrypt.encrypt("password9"));
//        System.out.println("10: " + encrypt.encrypt("password10"));
//        System.out.println("11: " + encrypt.encrypt("password11"));


}
