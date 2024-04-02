package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import com.gruppo4.SulPezzoGames.DAO.DAONews;
import com.gruppo4.SulPezzoGames.DAO.DAOUtente;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.News;
import com.gruppo4.SulPezzoGames.Entities.Utente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class NewsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DAONews DAONews;

    @Autowired
    private DAOUtente DAOutente;

    public void createNews(Map<String, String> params){
        params.put("id","0");
        News n = context.getBean(News.class, params);
        Utente u = DAOutente.readFromId(Integer.parseInt(params.get("autore")));
        n.setAutore(u);
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

    public List<News> findAllNewsOrderBy(){
        List<News> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAONews.readOrderBy();

        for(Entity e : map.values())
            if(e instanceof News){
                ris.add((News)e);
            }

        return ris;  
    }

    public void updateNews(Map<String, String> params){
        News n = context.getBean(News.class, params);
        Utente u = DAOutente.readFromId(Integer.parseInt(params.get("autore")));
        n.setAutore(u);
        DAONews.update(n);
    }

    public void deleteNews(int id){
        DAONews.delete(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public News findNewsById(int id){
        return DAONews.readFromId(id);
    }

}
