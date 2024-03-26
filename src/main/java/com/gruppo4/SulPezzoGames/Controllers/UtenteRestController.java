package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.Utente;
import com.gruppo4.SulPezzoGames.Services.UtenteService;

import java.util.List;
// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/utente")
public class UtenteRestController {
    
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/add")
    public void addUtente(@RequestBody Utente body){
        utenteService.createUtente(body);
    }

    @GetMapping("/get-all")
    public List<Utente> getAllUtenti(){
        return utenteService.findAllUtenti();
    }

    @PostMapping("/update")
    public void updateUtente(@RequestBody Utente body){
        utenteService.updateUtente(body);
    }

    @PostMapping("/delete")
    public void deleteUtente(@RequestParam int id){
        utenteService.deleteUtente(id);
    }

    @GetMapping("/utente-byId")
    public Utente getStudenteById(@RequestParam("idUtente") int id, @RequestHeader("token") String token){
        //RUOLO-Login-IdPersona
        if(token != null){
            if(!token.equalsIgnoreCase("") && !token.split("-")[0].equalsIgnoreCase("NONE")){
                return utenteService.findUtenteFromId(id);
            }
        }
        return null;
    }

}

