package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.News;
import com.gruppo4.SulPezzoGames.Services.NewsService;

import java.util.List;
// import java.util.Map;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/news")
public class NewsRestController {
    
    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public boolean addNews(@RequestBody Map<String, String> body, @RequestHeader("token") String token){
        if(token.split("-")[0].equalsIgnoreCase("admin")){
            newsService.createNews(body);
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
    public List<News> getAllNews(){
        return newsService.findAllNews();
    }

    @GetMapping("/all-ordered")
    public List<News> getAllNewsOderBy(){
        return newsService.findAllNewsOrderBy();
    }

    @PostMapping("/update")
    public boolean updateNews(@RequestBody Map<String, String> body, @RequestHeader("token") String token){
        if(token.split("-")[0].equalsIgnoreCase("admin")){
            newsService.updateNews(body);
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

    @GetMapping("/delete")
    public boolean deleteNews(@RequestParam int id, @RequestHeader("token") String token){
        if(token.split("-")[0].equalsIgnoreCase("admin")){
            newsService.deleteNews(id);
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

    // @GetMapping("/{id}")
    // public ResponseEntity<News> getNewsById(@PathVariable("id") int id){
    //     Optional<News> newsOptional = newsService.findNewsById(id);
    //     if(newsOptional.isPresent()) {
    //         return new ResponseEntity<>(newsOptional.get(), HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    
    @GetMapping("/{id}")
    public News getNewsById(@PathVariable("id") int id){
        return newsService.findNewsById(id);
    }

}
