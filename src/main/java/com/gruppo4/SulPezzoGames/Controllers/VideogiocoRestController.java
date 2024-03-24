package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.Videogioco;
import com.gruppo4.SulPezzoGames.Services.VideogiocoService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/Videogioco")
public class VideogiocoRestController {
    
    @Autowired
    private VideogiocoService videogiocoService;

    @PostMapping("/add")
    public void addVideogioco(@RequestBody Map<String,String> body){
        videogiocoService.createVideogioco(body);
    }

    @GetMapping("/get-all")
    public List<Videogioco> getAllVideogioco(){
        return videogiocoService.findAllVideogiochi();
    }

    @PostMapping("/update")
    public void updateVideogioco(@RequestBody Map<String,String> body){
        videogiocoService.updateVideogioco(body);
    }

    @PostMapping("/delete")
    public void deleteVideogioco(@RequestParam int id){
        videogiocoService.deleteVideogioco(id);
    }

}

