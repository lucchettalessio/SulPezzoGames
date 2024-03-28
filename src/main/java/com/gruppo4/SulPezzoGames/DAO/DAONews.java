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
import com.gruppo4.SulPezzoGames.Entities.News;
// import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class DAONews implements IDAO {

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "INSERT INTO News (titolo,categoria,immagine,data,testo,autore) VALUES (?,?,?,?,?,?)";
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
        String query = "select * from news";
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
                params.put("categoria", rs.getString(3));
                params.put("immagine", rs.getString(4));
                params.put("data", rs.getString(5));
                params.put("testo", rs.getString(6));
                params.put("autore", rs.getString(7)+"");

                News n = context.getBean(News.class, params);
                
                ris.put(n.getId(), n);
            }

        } catch (SQLException exc) {
            System.out.println("Errore nella read di DAONews. " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps o rs in read di DAONews" + ex.getMessage());
            }
        }

        return ris;

    }

    @Override
    public void update(Entity e) {

        String query = "UPDATE news SET (titolo,categoria,immagine,data,testo,autore) VALUES (?,?,?,?,?,?) WHERE id = ?";
        PreparedStatement ps = null;
        News n = null;

        try {
            n = (News)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getCategoria());
            ps.setString(3, n.getImmagine());
            ps.setString(4, n.getData());
            ps.setString(5, n.getTesto());
            ps.setInt(6, n.getAutore());
            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nell'update in DAONews." + exc.getMessage());
        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps nell'update di DAONews." + ex.getMessage());
            }
        }

    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM news WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException exc) {
            System.out.println("Errore nella delete di DAONews." + exc.getMessage());

        }
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps in delete di DAONews." + ex.getMessage());
            }
        }

    }
    
    
    
    public News readFromId(int id) {
        String query = "select * from news where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        News n = null;
        
        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", id + "");
                params.put("titolo", rs.getString(2));
                params.put("categoria", rs.getString(3));
                params.put("immagine", rs.getString(4));
                params.put("data", rs.getString(5));
                params.put("testo", rs.getString(6));
                params.put("autore", rs.getString(7)+"");
                
                n = context.getBean(News.class, params);
            }
            
        } catch (SQLException exc) {
            System.out.println("Errore nella read di DAONews. " + exc.getMessage());
        }
        finally{
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Errore nella chiusura del ps o rs in read di DAONews" + ex.getMessage());
            }
        }
        
        return n;
        
    }
    
}