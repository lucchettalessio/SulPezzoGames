package com.gruppo4.SulPezzoGames.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAOLogin;
import com.gruppo4.SulPezzoGames.Entities.Utente;

@Service
public class LoginService {
    
    @Autowired
    private DAOLogin DAOLogin;

    public Utente findUtente(String username, String password){
        return DAOLogin.readFromUsernameAndPassword(username, password);
    }

}
