package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.Videogioco;
import com.gruppo4.SulPezzoGames.Services.VideogiocoService;

import java.util.List;
// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/Videogioco")
public class VideogiocoRestController {
    
    @Autowired
    private VideogiocoService videogiocoService;

    @PostMapping("/add")
    public boolean addVideogioco(@RequestBody Videogioco body, @RequestHeader("token") String token){
        if(token.split("-")[0].equalsIgnoreCase("admin")){
            videogiocoService.createVideogioco(body);
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

    @GetMapping("/get-all")
    public List<Videogioco> getAllVideogioco(){
        return videogiocoService.findAllVideogiochi();
    }

    @PostMapping("/update")
    public void updateVideogioco(@RequestBody Videogioco body){
        videogiocoService.updateVideogioco(body);
    }

    @GetMapping("/delete")
    public boolean deleteVideogioco(@RequestParam int id, @RequestHeader("token") String token){
        if(token.split("-")[0].equalsIgnoreCase("admin")){
            videogiocoService.deleteVideogioco(id);
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

}

