package com.gruppo4.SulPezzoGames.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gruppo4.SulPezzoGames.Entities.News;
import com.gruppo4.SulPezzoGames.Services.NewsService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/news")
public class NewsRestController {
    
    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public void addNews(@RequestBody Map<String,String> body){
        newsService.createNews(body);
    }

    @GetMapping("/get-all")
    public List<News> getAllNews(){
        return newsService.findAllNews();
    }

    @PostMapping("/update")
    public void updateNews(@RequestBody Map<String,String> body){
        newsService.updateNews(body);
    }

    @PostMapping("/delete")
    public void deleteNews(@RequestParam int id){
        newsService.deleteNews(id);
    }

}
