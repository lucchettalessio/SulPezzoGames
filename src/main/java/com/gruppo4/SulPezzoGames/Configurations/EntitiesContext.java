package com.gruppo4.SulPezzoGames.Configurations;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gruppo4.SulPezzoGames.Entities.News;
import com.gruppo4.SulPezzoGames.Entities.Recensione;
import com.gruppo4.SulPezzoGames.Entities.Utente;
import com.gruppo4.SulPezzoGames.Entities.Videogioco;

@Configuration
public class EntitiesContext {
    
    @Bean
    @Scope("prototype")
    public News newNews(Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String titolo = params.get("titolo");
        String categoria = params.get("categoria");
        String immagine = params.get("immagine");
        String data = params.get("data");
        String testo = params.get("testo");

        return new News(id,titolo,categoria,immagine,data,testo);
    }

    @Bean
    @Scope("prototype")
    public Utente newUtente(Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String email = params.get("email");
        String username = params.get("username");
        String password = params.get("password");
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        String tipo_utente = params.get("tipo_utente");

        return new Utente(id,email,username,password,nome,cognome,tipo_utente);
    }

    @Bean
    @Scope("prototype")
    public Recensione newRecensione(Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String titolo = params.get("titolo");
        String data = params.get("data");
        int punteggio = Integer.parseInt(params.get("punteggio"));
        String immagine = params.get("immagine");
        String immagine2 = params.get("immagine2");
        String testo = params.get("testo");


        return new Recensione(id, titolo, data, punteggio, immagine, immagine2, testo);
    }

    @Bean
    @Scope("prototype")
    public Videogioco newVideogioco(Map<String,String> params){
        int id = Integer.parseInt(params.get("id"));
        String titolo = params.get("titolo");
        String data = params.get("data");
        String genere = params.get("genere");
        String produzione = params.get("produzione");
        String immagine = params.get("immagine");

        return new Videogioco(id, titolo, data, genere, produzione, immagine);
    }

}
