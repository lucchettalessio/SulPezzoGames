package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAOUtente;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class UtenteService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DAOUtente DAOUtente;

    public void createUtente(Map<String,String> m){
        Utente n = context.getBean(Utente.class,m);
        DAOUtente.create(n);
    }

    public List<Utente> findAllUtenti(){
        List<Utente> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAOUtente.read();

        for(Entity e : map.values())
            if(e instanceof Utente){
                ris.add((Utente)e);
            }

        return ris;        
    }

    public void updateUtente(Map<String,String> m){
        Utente n = context.getBean(Utente.class,m);
        DAOUtente.update(n);
    }

    public void deleteUtente(int id){
        DAOUtente.delete(id);
    }
}
