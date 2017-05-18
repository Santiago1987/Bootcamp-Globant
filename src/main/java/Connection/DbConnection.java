package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private Connection connection;
    private String driver;
    private String url;
    private String pwd;
    private String usr;

    public void disconect() throws Exception{
        if(connection != null){
            connection.close();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,usr,pwd);
        }catch (SQLException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }
}
