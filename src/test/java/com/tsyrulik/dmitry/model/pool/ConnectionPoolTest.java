package com.tsyrulik.dmitry.model.pool;

import com.ibatis.common.jdbc.ScriptRunner;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

public class ConnectionPoolTest {
    private static final String URL_TO_CREATE_OR_DROP_SCHEMA = "jdbc:mysql://localhost:3306/?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String CREATE_SCHEMA_PATH = "/test/create_test_schema.sql";
    private static final String DROP_SCHEMA_PATH = "/test/drop_test_schema.sql";
    private static final String PROPERTY_PATH = "/dbConfig.properties";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";

    @BeforeMethod
    public void setUp() throws PoolFitnessException, IOException, SQLException {
        Properties properties = new Properties();
        properties.load(ConnectionPoolTest.class.getResourceAsStream(PROPERTY_PATH));
        Reader reader = new InputStreamReader(ConnectionPoolTest.class.getResourceAsStream(CREATE_SCHEMA_PATH));
        DriverManager.registerDriver(new FabricMySQLDriver());
        Connection connection = DriverManager.getConnection(URL_TO_CREATE_OR_DROP_SCHEMA, USERNAME, PASSWORD);
        ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
        scriptRunner.runScript(reader);

        ConnectionPool.getInstance(5).getConnection();//1
    }

    @AfterMethod
    public void tearDown() throws PoolFitnessException, IOException, SQLException {
        ConnectionPool.getInstance().closeConnections();
        Reader reader = new InputStreamReader(ConnectionPoolTest.class.getResourceAsStream(DROP_SCHEMA_PATH));
        DriverManager.registerDriver(new FabricMySQLDriver());
        Connection connection = DriverManager.getConnection(URL_TO_CREATE_OR_DROP_SCHEMA, USERNAME, PASSWORD);
        ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
        scriptRunner.runScript(reader);

    }

    @Test
    public void getConnectionPoolTest() throws PoolFitnessException {
        ConnectionPool.getInstance().getConnection();//2
        assertTrue(ConnectionPool.getInstance().poolSize() == 3);
    }

//    @Test
//    public void initializeConnectionPoolTest() throws PoolFitnessException {
//        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            Statement statement = connection.createStatement()){
//
//            statement.execute("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
//                    "values (20, \"Alex\", \"Tugarin\", 23, \"M\", \"tukarin@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
//
//            //statement.executeUpdate("UPDATE new_fitness_center.user set user.name = \"Dimasik\" where user.iduser = 12 ");
//
////            statement.addBatch("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
////                   "values (21, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
////
////            statement.addBatch("insert into new_fitness_center.user(iduser, name, surname, years_old, sex, email, password, role_idrole) " +
////                    "values (22, \"Alex\", \"Zapatylok\", 26, \"M\", \"zapatylok@mail.ru\", \"c24a542f884e144451f9063b79e7994e\", 3 )");
////
////            statement.executeBatch();
////            statement.clearBatch();
//
//            //ResultSet resultSet = statement.executeQuery("SELECT * FROM new_fitness_center.user");
////            Properties properties = new Properties();
////            //   properties.load(getClass().getClassLoader().getResource(PROPERTY_PATH));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//

}