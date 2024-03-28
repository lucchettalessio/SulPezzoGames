package com.gruppo4.SulPezzoGames.Services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAOLogin;
import com.gruppo4.SulPezzoGames.DAO.DAOUtente;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class LoginService {
    
    @Autowired
    private DAOLogin DAOLogin;

    @Autowired
    private DAOUtente DAOUtente;

    public Utente findUtente(String username, String password){
        return DAOLogin.readFromUsernameAndPassword(username, password);
    }

    public void createUtente(Map<String,String> map){
        
        Utente u = new Utente(-1, map.get("email"), map.get("usernameR"), map.get("passwordR"), map.get("nome"), map.get("cognome"), "utente");
        DAOUtente.create((Entity)u);
    }

}
