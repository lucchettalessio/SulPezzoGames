package com.gruppo4.SulPezzoGames.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class DAOUtente implements IDAO{

    @Autowired
    private Database database;

    @Override
    public void create(Entity e) {
        String query = "INSERT INTO utenti (email,username,password,nome,cognome,tipo_utente) VALUES '?','?','?','?','?','?'";
        PreparedStatement ps = null;
        Utente u = null;

        try {
            u = (Utente)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getNome());
            ps.setString(5, u.getCognome());
            ps.setString(6, u.getTipo_utente());

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nel create di DAOUtente." + exc.getMessage());
            
        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura di ps nel create di DAOUtente." + ex.getMessage());
                
            }

        }
        


    }

    @Override
    public Map<Integer, Entity> read() {

        String query = "SELECT * FROM utenti";
        Map<Integer,Entity> ris = new HashMap<Integer,Entity>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Utente u = null;

        try {
            ps = database.getConnection().prepareStatement(query);

            rs = ps.executeQuery();

            while(rs.next()){
                u.setId(rs.getInt(1));
                u.setEmail(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setNome(rs.getString(5));
                u.setCognome(rs.getString(6));
                u.setTipo_utente(rs.getString(7));

                ris.put(u.getId(), (Entity)u);
            }

        } catch (SQLException exc) {
            System.out.println("Errore nella read di DAOUtente. " + exc.getMessage());

        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura di ps o rs nella read di DAOUtente." + ex.getMessage());
            }
        }

        return ris;

    }

    @Override
    public void update(Entity e) {

        String query = "UPDATE utenti SET (email,username,password,nome,cognome,tipo_utente) VALUES ('?','?','?','?','?','?') WHERE id = '?'";
        PreparedStatement ps = null;
        Utente n = null;

        try {
            n = (Utente)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getEmail());
            ps.setString(2, n.getUsername());
            ps.setString(3, n.getPassword());
            ps.setString(4, n.getNome());
            ps.setString(5, n.getCognome());
            ps.setString(6, n.getTipo_utente());
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nell'update in DAOUtente." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps nell'update di DAOUtente." + ex.getMessage());
            }
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM utenti WHERE id = '?'";
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella delete di DAOUtente." + exc.getMessage());

        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps in delete di DAOUtente." + ex.getMessage());
            }
        }

    }

    


    
}
