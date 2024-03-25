package com.gruppo4.SulPezzoGames.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.DTO.LoginStatus;
import com.gruppo4.SulPezzoGames.Entities.Utente;
import com.gruppo4.SulPezzoGames.Services.LoginService;

@RestController
@RequestMapping("api/")
public class LoginRestController {

    @Autowired
    private LoginService loginService;
    
    @PostMapping("/login")
    public LoginStatus signin(@RequestBody Map<String,String> body){
        Utente u = loginService.findUtente(body.get("username"), body.get("password"));
        LoginStatus ls = new LoginStatus();
        //utente / admin / autore
        ls.setToken(u.getTipo_utente(), true, u.getId());

        // if(u.getTipo_utente().equalsIgnoreCase("admin"))
        //     return "redirect:/areaAdmin";
        
        return ls;
    }

    @GetMapping("/checklogin")
    public boolean checklogin(@RequestHeader("token") String token){
        if(token != null){
            if(!token.equalsIgnoreCase("") && !token.split("-")[0].equalsIgnoreCase("NONE")){
                return true;
            }
        }
        return false;
    }

}
