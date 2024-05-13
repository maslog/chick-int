/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chick_int_dtr_system.Components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ronald
 */
public class Database {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mysql";
    private static final String root = "root";
    private static final String pass = "root";
    private static Connection connection = null;

    private Database() {

    }
    
    public static Connection getConnection() throws SQLException{
        if(connection == null){
            connection = DriverManager.getConnection(url,root,pass);
            System.out.println("Connected");
        }
        
        return  connection;
    }
    
    public static void closeConnection() throws SQLException {
    if (connection != null) {
      connection.close();
      System.out.println("Connection closed!");
      connection = null;
    }
  }

    public void getConne() {

        String query = "SELECT * FROM db_chick_int.employee;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql", "root", "root");
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            
//            while(rs.next()){
//                String name = rs.getString("firstname");
//                System.out.println(name);
//            }

            System.out.println(connection + "connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
