package com.gruppo4.SulPezzoGames.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class DAOLogin {

    @Autowired
    private Database database;

    @Autowired
    private DAOUtente DAOUtente;
    
    public Utente readFromUsernameAndPassword(String username, String password){
        String query = "SELECT id FROM utenti WHERE username = ? AND password = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Utente ris = null;


        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt(1);
                Utente u = DAOUtente.readFromId(id);
                ris = u;
            }

        } catch (SQLException exc) {
            System.out.println("Errore nel metodo in DAOLogin." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura di ps in DAOLogin" + ex.getMessage());
            }
        }
        return ris;
    }

}
