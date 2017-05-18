package Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;


import static org.junit.Assert.*;


public class DbConnectionTest {
    DbConnection dbConnection;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        dbConnection = (DbConnection) context.getBean("connectionH2");
    }


    @Test
    public void TestsIfTheTwoConnectioAreTheSame() throws Exception {
        Connection c = null;

        c = dbConnection.getConnection();


        assertNotNull(c);
    }

    @After
    public void tearDown() throws Exception {
       dbConnection.disconect();
    }

}