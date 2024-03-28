package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.gruppo4.SulPezzoGames.DAO.DAORecensione;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Recensione;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RecensioneService {

    // @Autowired
    // private ApplicationContext context;

    @Autowired
    private DAORecensione DAORecensione;

    public void createRecensione(Recensione r){
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

    public void updateRecensione(Recensione r){
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

}
