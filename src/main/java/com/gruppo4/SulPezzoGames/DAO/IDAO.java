package com.gruppo4.SulPezzoGames.DAO;

import java.util.Map;

import com.gruppo4.SulPezzoGames.Entities.Entity;

public interface IDAO {

    public void create(Entity e);
    public Map<Integer,Entity> read();
    public void update(Entity e);
    public void delete(int id);
    
}
