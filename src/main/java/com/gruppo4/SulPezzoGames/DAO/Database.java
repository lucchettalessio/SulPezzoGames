package com.gruppo4.SulPezzoGames.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Database {
    private final String username = "root";
	private final String password = "root";
	private final String timezone = "?useSSL=false&serverTimezone=UTC";
	private final String path = "jdbc:mysql://localhost:3306/";
    private String nomeDb = "sulpezzogames";
    
    private Connection connection;

    public Database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path + nomeDb + timezone, username, password);
        }
        catch(SQLException e){
            System.out.println("Errore connesione: " + e.getMessage());
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver non trovato");
        }
    }


    public void closeConnection(){
        try{
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Errore chiusura connessione");
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
