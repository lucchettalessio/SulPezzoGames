package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAONews;
import com.gruppo4.SulPezzoGames.DAO.DAORecensione;
import com.gruppo4.SulPezzoGames.DAO.DAOUtente;
import com.gruppo4.SulPezzoGames.DAO.DAOVideogioco;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.News;
import com.gruppo4.SulPezzoGames.Entities.Recensione;
import com.gruppo4.SulPezzoGames.Entities.Utente;
import com.gruppo4.SulPezzoGames.Entities.Videogioco;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RecensioneService {

    // @Autowired
    // private ApplicationContext context;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DAORecensione DAORecensione;

    @Autowired
    private DAOUtente DAOutente;

    @Autowired
    private DAOVideogioco DAOvideogioco;

    public void createRecensione(Map<String, String> params){
        params.put("id","0");
        Recensione r = context.getBean(Recensione.class, params);
        Utente c = DAOutente.readFromId(Integer.parseInt(params.get("autore")));
        Videogioco v = DAOvideogioco.readFromId(Integer.parseInt(params.get("videogioco")));
        r.setAutore(c);
        r.setVideogioco(v);
        DAORecensione.create(r);
    }

    public List<Recensione> findAllRecensioni(){
        List<Recensione> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAORecensione.read();

        for(Entity e : map.values())
            if(e instanceof Recensione){
                ris.add((Recensione)e);
            }

        return ris;        
    }

    public List<Recensione> findAllNewsOrderBy(){
        List<Recensione> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAORecensione.readOrderBy();

        for(Entity e : map.values())
            if(e instanceof Recensione){
                ris.add((Recensione)e);
            }

        return ris;  
    }

    public void updateRecensione(Map<String, String> params){
        Recensione r = context.getBean(Recensione.class, params);
        Utente c = DAOutente.readFromId(Integer.parseInt(params.get("autore")));
        Videogioco v = DAOvideogioco.readFromId(Integer.parseInt(params.get("videogioco")));
        r.setAutore(c);
        r.setVideogioco(v);
        DAORecensione.update(r);
    }

    public void deleteRecensione(int id){
        DAORecensione.delete(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Recensione findRecensioneById(int id){
        return DAORecensione.readFromId(id);
    }


    public List<Utente> getAutori(){
        List<Utente> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAORecensione.getAutori();

        for(Entity e : map.values())
            if(e instanceof Utente){
                ris.add((Utente)e);
            }

        return ris;        
    }

}
