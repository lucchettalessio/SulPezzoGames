package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAORecensione;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Recensione;

@Service
public class RecensioneService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DAORecensione DAORecensione;

    public void createRecensione(Map<String,String> m){
        Recensione n = context.getBean(Recensione.class,m);
        DAORecensione.create(n);
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

    public void updateRecensione(Map<String,String> m){
        Recensione n = context.getBean(Recensione.class,m);
        DAORecensione.update(n);
    }

    public void deleteRecensione(int id){
        DAORecensione.delete(id);
    }
}
