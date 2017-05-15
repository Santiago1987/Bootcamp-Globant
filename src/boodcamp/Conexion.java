/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boodcamp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    
   private Connection cnx = null;
   private Statement comando = null;
   private ResultSet registro;
    
   public Connection MySQLConnect(){
       
       try{
           Class.forName("com.mysql.jdbc.Driver");
           
           String servidor ="jdbc:mysql://localhost:3306/boodcamp2";
           String usuario = "root";
           String pass = "AbU422696";
           
           cnx = DriverManager.getConnection(servidor,usuario,pass);
           
       }catch(ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
           cnx = null;
       }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            cnx = null;
       }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            cnx = null; 
       }finally {
            
            return cnx;
       }    
       }
   
   
       public boolean insertSQL(String sql){
           
           MySQLConnect();
           
           try{
               comando = cnx.createStatement();
               comando.executeUpdate(sql);
               cnx.close();
               comando.close();
           }catch (SQLException ex){
               ex.printStackTrace();
               
               return false;
           }
           
           
           return true;
           
       }
       
       public ResultSet selectSQL(String sql){
           
        MySQLConnect();
        try{
            Statement comando = cnx.createStatement();
            registro = comando.executeQuery(sql);                
            
                      
       }catch(SQLException ex){
           ex.printStackTrace();
           return null;
       }
       return registro;   
       
       
       
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
    
    
}
