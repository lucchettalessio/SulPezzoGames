package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAONews;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.News;

@Service
public class NewsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DAONews DAONews;

    public void createNews(Map<String,String> m){
        News n = context.getBean(News.class,m);
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

    public void updateNews(Map<String,String> m){
        News n = context.getBean(News.class,m);
        DAONews.update(n);
    }

    public void deleteNews(int id){
        DAONews.delete(id);
    }

}
