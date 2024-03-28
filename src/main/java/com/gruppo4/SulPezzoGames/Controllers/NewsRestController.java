package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.News;
import com.gruppo4.SulPezzoGames.Services.NewsService;

import java.util.List;
// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void addNews(@RequestBody News body){
        newsService.createNews(body);
    }

    @GetMapping("/get-all")
    public List<News> getAllNews(){
        return newsService.findAllNews();
    }

    @PostMapping("/update")
    public void updateNews(@RequestBody News body){
        newsService.updateNews(body);
    }

    @PostMapping("/delete")
    public void deleteNews(@RequestParam int id){
        newsService.deleteNews(id);
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
