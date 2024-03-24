package com.gruppo4.SulPezzoGames.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Recensione;

@Service
public class DAORecensione implements IDAO {

    @Autowired
    private Database database;

    @Override
    public void create(Entity e) {
        String query = "INSERT INTO Recensioni (titolo,data,punteggio,immagine,testo,autore,videogioco) VALUES ('?','?','?','?','?','?','?')";
        Recensione n = null;
        PreparedStatement ps = null;

        try{
            n = (Recensione)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getData());
            ps.setString(3, n.getPunteggio()+"");
            ps.setString(4, n.getImmagine());
            ps.setString(5, n.getTesto());
            ps.setString(6, n.getAutore() + "");
            ps.setString(7, n.getVideogioco() + "");

            ps.executeUpdate();

        } catch (SQLException exc) {

            System.out.println("Errore nel create di DAORecensione." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Errore nella chiusura di PreparedStatement nel create di DAORecensione.");
            }
        }

    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from Recensioni";
        Map<Integer, Entity> ris = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Recensione n = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                n.setId(Integer.parseInt(rs.getString(1)));
                ps.setString(2, n.getTitolo());
                ps.setString(3, n.getData());
                ps.setString(4, n.getPunteggio()+"");
                ps.setString(5, n.getImmagine());
                ps.setString(6, n.getTesto());
                ps.setString(7, n.getAutore() + "");
                ps.setString(8, n.getVideogioco() + "");

                ris.put(n.getId(), (Entity)n);

            }

        } catch (SQLException exc) {
            System.out.println("Errore nella read di DAORecensione. " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps o rs in read di DAORecensione" + ex.getMessage());
            }
        }

        return ris;

    }

    @Override
    public void update(Entity e) {

        String query = "UPDATE Recensioni SET (titolo,data,punteggio,immagine,testo,autore,videogioco) VALUES ('?','?','?','?','?','?','?') WHERE id = '?'";
        PreparedStatement ps = null;
        Recensione n = null;

        try {
            n = (Recensione)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getData());
            ps.setString(3, n.getPunteggio()+"");
            ps.setString(4, n.getImmagine());
            ps.setString(5, n.getTesto());
            ps.setString(6, n.getAutore() + "");
            ps.setString(7, n.getVideogioco() + "");
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nell'update in DAORecensione." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps nell'update di DAORecensione." + ex.getMessage());
            }
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Recensioni WHERE id = '?'";
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella delete di DAORecensione." + exc.getMessage());

        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps in delete di DAORecensione." + ex.getMessage());
            }
        }

    }
    
}
