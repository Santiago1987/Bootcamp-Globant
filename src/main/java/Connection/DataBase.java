package Connection;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@Component
public class DataBase {
    private Statement st;
    private String sql = "create database Weather";
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




    public void setDataBase(){
        Connection c = DbConnection.getDbConnection();

        try {

            st = c.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate(use);
            st.executeUpdate(table1);
            st.executeUpdate(table2);
            st.close();
            c.close();


        } catch (SQLException e) {

            System.out.println("Data base creation fail");
        }


    }
}
