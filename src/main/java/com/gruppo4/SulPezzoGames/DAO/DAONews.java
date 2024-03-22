package com.gruppo4.SulPezzoGames.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.News;

public class DAONews implements IDAO {

    @Autowired
    private Database database;

    

    @Override
    public void create(Entity e) {
        String query = "INSERT INTO News (titolo,categoria,immagine,data,testo,autore) VALUES ('?','?','?','?','?','?')";
        News n = null;
        PreparedStatement ps = null;

        try{
            n = (News)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getCategoria());
            ps.setString(3, n.getImmagine());
            ps.setString(4, n.getData());
            ps.setString(5, n.getTesto());
            ps.setString(6, n.getAutore() + "");

            ps.executeUpdate();

        } catch (SQLException exc) {

            System.out.println("Errore nel create di DAONews." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Errore nella chiusura di PreparedStatement nel create di DAONews.");
            }
        }

    }

    @Override
    public Map<Integer, Entity> read() {

    }

    @Override
    public void update(Entity e) {
    }

    @Override
    public void delete(int id) {
    }
    
}
