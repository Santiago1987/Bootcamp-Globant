package Conecction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;


public class DbConnectionTest {


    @Before
    public void setUp() throws Exception {
        DbConnection.setDriver("org.h2.Driver");
        DbConnection.setUsr("test");
        DbConnection.setPwd("test");
        DbConnection.setUrl("jdbc:h2:~/test");
    }

    @Test
    public void cnxTest() throws Exception {

        Connection c1 = DbConnection.getDbConnection();
        Connection c2 = DbConnection.getDbConnection();

        assertSame(c1, c2);
    }

    @After
    public void tearDown() throws Exception {
        DbConnection.getDbConnection().close();
    }

}