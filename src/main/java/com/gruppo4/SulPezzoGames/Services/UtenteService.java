package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAOUtente;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class UtenteService {

    // @Autowired
    // private ApplicationContext context;

    @Autowired
    private DAOUtente DAOUtente;

    public void createUtente(Utente u){
        DAOUtente.create(u);
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

    public void updateUtente(Utente u){
        DAOUtente.update(u);
    }

    public void deleteUtente(int id){
        DAOUtente.delete(id);
    }

    public Utente findUtenteFromId(int id){
        return DAOUtente.readFromId(id);
    }
}
