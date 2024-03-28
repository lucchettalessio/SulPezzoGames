package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.gruppo4.SulPezzoGames.Entities.Recensione;
import com.gruppo4.SulPezzoGames.Services.RecensioneService;

import java.util.List;
// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Recensione")
@CrossOrigin(origins = "http://localhost:4200")
public class RecensioneRestController {
    
    @Autowired
    private RecensioneService recensioneService;

    @PostMapping("/add")
    public void addRecensione(@RequestBody Recensione body){
        recensioneService.createRecensione(body);
    }


    @GetMapping("/get-all")
    public List<Recensione> getAllRecensioni(){
        return recensioneService.findAllRecensioni();
    }

    @PostMapping("/update")
    public void updateRecensione(@RequestBody Recensione body){
        recensioneService.updateRecensione(body);
    }

    @GetMapping("/delete")
    public boolean deleteRecensione(@RequestParam int id, @RequestHeader("token") String token){
        if(token.split("-")[0].equalsIgnoreCase("admin")){
            recensioneService.deleteRecensione(id);
            return true;
        }
        else {
            // Handle the case where the token is not "admin"
            // For example, you can return an error response, log the event, or perform any other appropriate action.
            // Here's an example of logging the event:
            System.out.println("Unauthorized access attempt: Token is not admin.");
            return false;
        }
    }
    
    @GetMapping("/{id}")
    public Recensione getRecensioneById(@PathVariable("id") int id){
        return recensioneService.findRecensioneById(id);
    }

}
