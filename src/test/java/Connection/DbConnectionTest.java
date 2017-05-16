package Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;


public class DbConnectionTest extends DbConnection {
    @Before
    public void setUp() throws Exception {
        driver = "org.h2.Driver";
        usr = "test";
        pwd = "test";
        url = "jdbc:h2:~/test";


    }


    @Test
    public void cnxTest() throws Exception {

        Connection c1 = getDbConnection();
        Connection c2 = getDbConnection();

        assertSame(c1, c2);
    }

    @After
    public void tearDown() throws Exception {
        getDbConnection().close();
    }

}