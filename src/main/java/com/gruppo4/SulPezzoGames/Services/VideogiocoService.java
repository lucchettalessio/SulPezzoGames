package com.gruppo4.SulPezzoGames.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gruppo4.SulPezzoGames.DAO.DAOVideogioco;
import com.gruppo4.SulPezzoGames.Entities.Entity;
import com.gruppo4.SulPezzoGames.Entities.Videogioco;

@Service
public class VideogiocoService {
    
    // @Autowired
    // private ApplicationContext context;

    @Autowired
    private DAOVideogioco DAOVideogioco;

    public void createVideogioco(Videogioco v){
        DAOVideogioco.create(v);
    }

    public List<Videogioco> findAllVideogiochi(){
        List<Videogioco> ris = new ArrayList<>();
        Map<Integer,Entity> map = DAOVideogioco.read();

        for(Entity e : map.values())
            if(e instanceof Videogioco){
                ris.add((Videogioco)e);
            }

        return ris;        
    }

    public void updateVideogioco(Videogioco v){
        DAOVideogioco.update(v);
    }

    public void deleteVideogioco(int id){
        DAOVideogioco.delete(id);
    }

}
