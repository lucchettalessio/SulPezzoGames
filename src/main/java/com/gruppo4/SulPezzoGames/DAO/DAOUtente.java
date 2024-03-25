package com.gruppo4.SulPezzoGames.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class DAOUtente implements IDAO{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

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

        try {
            ps = database.getConnection().prepareStatement(query);

            rs = ps.executeQuery();

            while(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("email", rs.getString(2));
                params.put("username", rs.getString(3));
                params.put("password", rs.getString(4));
                params.put("nome", rs.getString(5));
                params.put("cognome", rs.getString(6));
                params.put("tipo_utente", rs.getString(7));

                Utente u = context.getBean(Utente.class, params);
 
                ris.put(u.getId(), u);
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

    public Utente readFromId(int id)
    {

        String query="select * from utenti where id = ?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        Utente u=null;

        try
        {
           ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("email", rs.getString(2));
                params.put("username", rs.getString(3));
                params.put("password", rs.getString(4));
                params.put("nome", rs.getString(5));
                params.put("cognome", rs.getString(6));
                params.put("tipo_utente", rs.getString(7));

                u = context.getBean(Utente.class, params);
 
            }
    }

    catch(SQLException exc){
        System.out.println("Errore in readFromId in DAOUtente");
    }
    finally{
        try{
            ps.close();
            rs.close();
        }
        catch(Exception exc){
            System.out.println("Errore chiusura PreparedStatement in readFromId");
        }
    }
    return u;
}

    


    
}
