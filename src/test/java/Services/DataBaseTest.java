package Services;

import Conecction.DbConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;


public class DataBaseTest {
    Connection c;
    boolean n;
    private Statement st = null;
    private String sql = "create schema Weather";
    private String use = "use Weather";
    private String table1 ="create table location(" +
            "cod_locat int auto_increment not null," +
            "    city varchar(50)," +
            "    country varchar(50)," +
            "    region varchar(50)," +
            "    title varchar(50)," +
            "    actualday varchar(50)," +
            "    state varchar(50)," +
            "    temp int," +
            "    humidity int," +
            "    pressure float," +
            "    visibility float," +
            "    sunrise varchar(50)," +
            "    sunset varchar(50)," +
            "    constraint pk_cod_locat primary key (cod_locat));";

    private String table2 = "create table forecast(" +
            "cod_fore int auto_increment not null," +
            "    datef varchar(50)," +
            "    dayf varchar(50)," +
            "    state varchar(50)," +
            "    high float," +
            "    low float," +
            "    cod_locat int not null," +
            "    constraint pk_cod_fore primary key (cod_fore)," +
            "    constraint fk_cod_locat foreign key (cod_locat) " +
            "    references location (cod_locat));";
    @Before
    public void setUp() throws Exception {

            Class.forName("org.h2.Driver");
            c = DriverManager.getConnection("jdbc:h2:~/test", "test", "test");


    }




    @Test
    public void setDataBase() throws Exception {
        try{
            st = c.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate(use);
            st.executeUpdate(table1);
            st.executeUpdate(table2);
            n=true;
        }catch (SQLException ex){
            n =false;
        }

        assertTrue(n);



    }


    @After
    public void tearDown() throws Exception {
        st.executeUpdate("Drop schema Weather");
        c.close();
        st.close();

    }

}