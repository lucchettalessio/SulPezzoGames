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
// import com.gruppo4.SulPezzoGames.Entities.Utente;
import com.gruppo4.SulPezzoGames.Entities.Videogioco;

@Service
public class DAOVideogioco implements IDAO {

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "INSERT INTO Videogiochi (titolo,data,genere,produzione,immagine) VALUES (?,?,?,?,?)";
        Videogioco n = null;
        PreparedStatement ps = null;

        try{
            n = (Videogioco)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getData());
            ps.setString(3, n.getGenere());
            ps.setString(4, n.getProduzione());
            ps.setString(5, n.getImmagine());

            ps.executeUpdate();

        } catch (SQLException exc) {

            System.out.println("Errore nel create di DAOVideogioco." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Errore nella chiusura di PreparedStatement nel create di DAOVideogioco.");
            }
        }

    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from Videogiochi";
        Map<Integer, Entity> ris = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("titolo", rs.getString(2));
                params.put("data", rs.getString(3));
                params.put("genere", rs.getString(4));
                params.put("produzione", rs.getString(5));
                params.put("immagine", rs.getString(6));

                Videogioco v = context.getBean(Videogioco.class, params);

                ris.put(v.getId(), v); 
            }

        } catch (SQLException exc) {
            System.out.println("Errore nella read di DAOVideogioco. " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps o rs in read di DAOVideogioco" + ex.getMessage());
            }
        }

        return ris;

    }

    @Override
    public void update(Entity e) {

        String query = "UPDATE Videogiochi SET (titolo,data,genere,produzione,immagine) VALUES (?,?,?,?,?) WHERE id = ?";
        PreparedStatement ps = null;
        Videogioco n = null;

        try {
            n = (Videogioco)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getData());
            ps.setString(3, n.getGenere());
            ps.setString(4, n.getProduzione());
            ps.setString(5, n.getImmagine());
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nell'update in DAOVideogioco." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps nell'update di DAOVideogioco." + ex.getMessage());
            }
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Videogiochi WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella delete di DAOVideogioco." + exc.getMessage());

        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps in delete di DAOVideogioco." + ex.getMessage());
            }
        }

    }
    
}

