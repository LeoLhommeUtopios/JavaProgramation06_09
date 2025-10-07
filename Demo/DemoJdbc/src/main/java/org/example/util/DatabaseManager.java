package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql//localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root";

    public static Connection getConnection (){
        try{
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        }catch (SQLException e){
            System.out.println("Erreure de connection");
            return null;
        }
    }
}
