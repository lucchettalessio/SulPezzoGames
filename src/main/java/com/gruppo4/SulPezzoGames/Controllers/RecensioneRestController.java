package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.Recensione;
import com.gruppo4.SulPezzoGames.Services.RecensioneService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Recensione")
public class RecensioneRestController {
    
    @Autowired
    private RecensioneService recensioneService;

    @PostMapping("/add")
    public void addRecensione(@RequestBody Map<String,String> body){
        recensioneService.createRecensione(body);
    }

    @GetMapping("/get-all")
    public List<Recensione> getAllRecensioni(){
        return recensioneService.findAllRecensioni();
    }

    @PostMapping("/update")
    public void updateRecensione(@RequestBody Map<String,String> body){
        recensioneService.updateRecensione(body);
    }

    @PostMapping("/delete")
    public void deleteRecensione(@RequestParam int id){
        recensioneService.deleteRecensione(id);
    }

}
