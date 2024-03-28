package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import com.gruppo4.SulPezzoGames.DAO.DAONews;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.News;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class NewsService {

    // @Autowired
    // private ApplicationContext context;

    @Autowired
    private DAONews DAONews;

    public void createNews(News n){
        DAONews.create(n);
    }

    public List<News> findAllNews(){
        List<News> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAONews.read();

        for(Entity e : map.values())
            if(e instanceof News){
                ris.add((News)e);
            }

        return ris;        
    }

    public void updateNews(News n){
        DAONews.update(n);
    }

    public void deleteNews(int id){
        DAONews.delete(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    // public Optional<News> findNewsById(int id) {
    //     News news = entityManager.find(News.class, id);
    //     return Optional.ofNullable(news);
    // }

    public News findNewsById(int id){
        return DAONews.readFromId(id);
    }

}
