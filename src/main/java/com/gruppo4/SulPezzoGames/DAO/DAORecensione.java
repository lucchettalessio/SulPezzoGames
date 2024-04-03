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
import com.gruppo4.SulPezzoGames.Entities.Recensione;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class DAORecensione implements IDAO {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Database database;

    @Autowired
    private DAOUtente DAOutente;

    @Autowired
    private DAOVideogioco DAOvideogioco;

    @Override
    public void create(Entity e) {
        String query = "INSERT INTO Recensioni (titolo,data,punteggio,immagine,immagine2,testo,autore,videogioco) VALUES (?,?,?,?,?,?,?,?)";
        Recensione n = null;
        PreparedStatement ps = null;

        try{
            n = (Recensione)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getData());
            ps.setString(3, n.getPunteggio()+"");
            ps.setString(4, n.getImmagine());
            ps.setString(5, n.getImmagine2());
            ps.setString(6, n.getTesto());
            ps.setString(7, n.getAutore().getId() + "");
            ps.setString(8, n.getVideogioco().getId() + "");

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
        String query = "select r.* from Recensioni r join utenti u on r.autore = u.id";
        Map<Integer, Entity> ris = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getString(1));
                params.put("titolo", rs.getString(2));
                params.put("data", rs.getString(3));
                params.put("punteggio", rs.getString(4));
                params.put("immagine", rs.getString(5));
                params.put("immagine2", rs.getString(6));
                params.put("testo", rs.getString(7));

                Recensione r = context.getBean(Recensione.class, params);
                r.setAutore(DAOutente.readFromId(rs.getInt(8)));
                r.setVideogioco(DAOvideogioco.readFromId(rs.getInt(9)));

                ris.put(r.getId(), r);
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

        String query = "UPDATE Recensioni SET titolo = ?, data = ?, punteggio = ?, immagine = ?, immagine2 = ?, testo = ?, autore = ?, videogioco = ? WHERE id = ?";
        PreparedStatement ps = null;
        Recensione n = null;

        try {
            n = (Recensione)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, n.getTitolo());
            ps.setString(2, n.getData());
            ps.setString(3, n.getPunteggio()+"");
            ps.setString(4, n.getImmagine());
            ps.setString(5, n.getImmagine2());
            ps.setString(6, n.getTesto());
            ps.setString(7, n.getAutore().getId() + "");
            ps.setString(8, n.getVideogioco().getId() + "");
            ps.setString(9, n.getId() + "");
            System.out.println(ps);
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
        String query = "DELETE FROM Recensioni WHERE id = ?";
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

    public Recensione readFromId(int id) {
        String query = "select * from recensioni where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Recensione r = null;
        
        try {
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", id + "");
                params.put("titolo", rs.getString(2));
                params.put("data", rs.getString(3));
                params.put("punteggio", rs.getString(4));
                params.put("immagine", rs.getString(5));
                params.put("immagine2", rs.getString(6));
                params.put("testo", rs.getString(7));
                params.put("autore", rs.getString(8));
                params.put("punteggio", rs.getString(9)+"");

                
                r = context.getBean(Recensione.class, params);
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
        
        return r;
        
    }

    public Map<Integer, Entity> getAutori(){
        String query = "select * from utenti where tipo_utente = 'autore'";
        Map<Integer, Entity> ris = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getString(1));
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
    
}

