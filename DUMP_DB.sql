Alessio
lucche747
Online
Leonardo Pieramici, Michelle, markito, Mihaela.miky94, zak968

Michelle
 ha aggiunto 
markito
 al gruppo.
 — 20/03/2024 18:48
Alessio
 ha avviato una chiamata che è durata 30 minuti.
 — 21/03/2024 09:42
Michelle — 21/03/2024 09:44
Ragazzi non sento niente
Mihaela.miky94 — 21/03/2024 09:44
vieni in sessione 4
Michelle — 21/03/2024 09:46
Non capisco come funziona.........
zak968 — 21/03/2024 09:46
siamo su zoom
Alessio — 21/03/2024 09:46
su zoom
Michelle — 21/03/2024 09:46
AH
Scusate non avevo capito
Alessio — 21/03/2024 09:47
colpa mia scusa haha
Mihaela.miky94 — 21/03/2024 09:47
scusami
Mihaela.miky94 — 21/03/2024 09:55
sei troppo figo @Alessio!!
Alessio — 21/03/2024 09:58
Mihaela.miky94 — 21/03/2024 09:59
😂
Michelle — 21/03/2024 10:27
https://daily.dev/
daily.dev | Where developers grow together
daily dev is a professional network for developers to learn, collaborate, and grow together. Developers come to daily.dev to discover a wide variety of professional knowledge, create groups where they can collaborate with other developers they appreciate, and discuss the latest trends in the developer ecosystem.
daily.dev | Where developers grow together
Un esempio come ha proposto Mihaela
Mihaela.miky94 — 21/03/2024 10:33
https://github.com/mihaelamiky/SulPezzoGames.git
GitHub
GitHub - mihaelamiky/SulPezzoGames
Contribute to mihaelamiky/SulPezzoGames development by creating an account on GitHub.
GitHub - mihaelamiky/SulPezzoGames
Mihaela.miky94 — 21/03/2024 15:09
https://arstechnica.com/
Ars Technica
Ars Technica
Serving the Technologist for more than a decade. IT news, reviews, and analysis.
Immagine
https://www.theverge.com/
The Verge
The Verge
The Verge is about technology and how it makes us feel. Founded in 2011, we offer our audience everything from breaking news to reviews to award-winning features and investigations, on our site, in video, and in podcasts.
The Verge
https://www.smashingmagazine.com/category/ux/
Smashing Magazine
UX — Smashing Magazine
Magazine on CSS, JavaScript, front-end, accessibility, UX and design. For developers, designers and front-end engineers.
UX — Smashing Magazine
https://thehackernews.com/?m=1
The Hacker News
The Hacker News | #1 Trusted Cybersecurity News Site
The Hacker News is the most trusted and popular cybersecurity publication for information security professionals seeking breaking news, actionable insights and analysis.
The Hacker News | #1 Trusted Cybersecurity News Site
https://alistapart.com/it/
Italian A List Apart
sanketio
Italian A List Apart
Per chi crea il web
Immagine
https://stackoverflow.com/
Stack Overflow
Stack Overflow - Where Developers Learn, Share, & Build Careers
Stack Overflow | The World’s Largest Online Community for Developers
Immagine
markito — 21/03/2024 17:22
stile/ sito modello (colori, font, struttura generale, ...)
dettagli recensioni/ news
news/recensioni a più pagine?
sezioni diverse per news e recensioni?
Da valutare anche un database di videogiochi a cui vengono associate le recensioni(?)
Leonardo Pieramici — 22/03/2024 10:59
CONSEGNA 4: News Nerd
   TRACCIA
Siamo SulPezzoGames, un'azienda giornalistica del settore dei videogiochi.
Abbiamo bisogno di un nuovo CMS per gestire le news e le recensioni.

L'applicazione deve essere in grado di gestire le news.

Una news deve contenere le seguenti informazioni:
il titolo
categoria (Sony, Nintendo, etc)
immagine copertina (bonus)
contenuto
data di pubblicazione
l'autore

Le recensioni sono simili
il titolo del videogioco
la data
la recensione
il punteggio
il recensore
immagini in carrosello (bonus)

DovrÃ  quindi essere anche presente un sistema per gestire le utenze
Da valutare anche un database di videogiochi a cui vengono associate le recensioni.
Queste possono anche essere funzioni da aggiungere in futuro
All'inizio a noi va bene anche solo che sia l'admin ad aggiungere news/recensioni

Il tutto deve essere consultabile come un sito di news.

Aspettiamo dal vostro team una valutazione in termini di tempo per un primo prototipo.
Alessio — 22/03/2024 12:04
Buongiorno Davide, sarebbe possibile avere un confronto con lei sulle fondamenta del progetto SulPezzoGames?
Michelle — 22/03/2024 12:08
https://drawsql.app/teams/workspace-23/diagrams/project-work
DrawSQL
Project Work | DrawSQL
Database schema diagram for Project Work.
Project Work | DrawSQL
Alessio — 22/03/2024 12:09
Risposte alle domande per il cliente

retro, grafica nerd, colori luminosi, pacman,
font da vedere
reference da vedere
logo blu elettrico, scuro, svecchiare logo
recensione diversa estetica, anteprima diversa
sistema recensioni commenti, utente legge e commenta e stelle
admin caricano articoli e recensioni e gestiscono commenti molesti
ricerca cronologico con filtro per recensioni
navigazione nella pagina navbar
no infinite scrolling ma pagine
recensioni con immagine del videogioco, news decidono i giornalisti
nella home separate recensioni e news

martedi h 15
Michelle — 22/03/2024 12:12
Immagine
Immagine
Mihaela.miky94 — 22/03/2024 12:12
figo quello a destra
Alessio — 22/03/2024 12:52
bozza a caso con figma
Immagine
zak968 — 22/03/2024 12:53
bello
Leonardo Pieramici — 22/03/2024 14:56
CREATE TABLE News (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    immagine VARCHAR(255),
    data DATE,
    testo TEXT,
    autore INT,
    FOREIGN KEY (autore) REFERENCES Utenti(id)
);


CREATE TABLE Utenti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(100),
    cognome VARCHAR(100),
    tipo_utente ENUM('admin', 'utente', 'autore') NOT NULL
);


CREATE TABLE Recensioni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255) NOT NULL,
    data DATE,
    punteggio INT,
    immagine VARCHAR(255),
    testo TEXT,
    autore INT,
    videogioco INT,
    FOREIGN KEY (autore) REFERENCES Utenti(id),
    FOREIGN KEY (videogioco) REFERENCES Videogiochi(id)
);


CREATE TABLE Videogiochi (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255) NOT NULL,
    data DATE,
    genere VARCHAR(100),
    produzione VARCHAR(100),
    immagine VARCHAR(255)
);
Controllate se ci sono errori o ditemi se devo modificare qualcosa
Leonardo Pieramici — 22/03/2024 15:27
CREATE DATABASE sulpezzogames;
USE sulpezzogames;


CREATE TABLE News (
    id INT PRIMARY KEY AUTO_INCREMENT,
Mostra
sulpezzogames.sql
4 KB
Alessio — 22/03/2024 15:47
@Override
    public Map<Integer, Entity> read() {
    }

    @Override
    public void update(Entity e) {
    }

    @Override
    public void delete(int id) {
    }
Michelle — 22/03/2024 15:56
Torno tra poco ragazzi
Alessio — 22/03/2024 16:45
https://github.com/lucchettalessio/SulPezzoGames
GitHub
GitHub - lucchettalessio/SulPezzoGames
Contribute to lucchettalessio/SulPezzoGames development by creating an account on GitHub.
GitHub - lucchettalessio/SulPezzoGames
Mihaela.miky94 — 22/03/2024 17:20
git config --global user.email "mihaela.cipriano@gmail.com"
git config --global user.name "mihaelamiky"
Michelle — Oggi alle 09:23
calyptes
zak968 — Oggi alle 09:23
ZakariaElhaoudi
Leonardo Pieramici — Oggi alle 10:10
Field newsService in com.gruppo4.SulPezzoGames.Controllers.NewsRestController required a bean of type 'com.gruppo4.SulPezzoGames.Services.NewsService' that could not be found.

The injection point has the following annotations:
        
@org.springframework.beans.factory.annotation.Autowired(required=true)


Action:

Consider defining a bean of type 'com.gruppo4.SulPezzoGames.Services.NewsService' in your configuration.
spring.application.name=SulPezzoGames
spring.datasource.url=jdbc:mysql://localhost:3306/sulpezzogames
spring.datasource.username=root
spring.datasource.password=rootroot00
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
Alessio — Oggi alle 10:17
import org.springframework.stereotype.Service;
Alessio — Oggi alle 10:35
public Docente readFromId(int idDocente){
        String query = "select * from persone p inner join docenti d on p.id = d.id where d.id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Docente e = null;
        try{
Mostra
message.txt
3 KB
Leonardo Pieramici — Oggi alle 10:44
e = context.getBean(Docente.class, params);
                List<Classe> listaClassi = new ArrayList<>();
                Map<Integer, Entity> result = classeDAO.readFromIdDocente(e.getId());

                for(Entity classe : result.values()){
                    listaClassi.add((Classe)classe);
                }

                e.setClassi(listaClassi);
Alessio — Oggi alle 10:51
package com.generation.scuolarest.dto;

public class LoginStatus {
    //RUOLO-IsLogin-IdPersona
    private String token;


    public void setToken(String ruolo, boolean isLogin, int idPersona){
        //ESEMPIO DI TOKEN
        //STUDENTE-true-16
        token = ruolo+"-"+isLogin+"-"+idPersona;
    }

    public String getRuolo(){
        return token.split("-")[0];
    }

    public boolean getLogin(){
        return Boolean.parseBoolean(token.split("-")[1]);
    }

    public int getIdpersona(){
        return Integer.parseInt(token.split("-")[2]);
    }

    public String getToken(){
        return token;
    }


}
Alessio — Oggi alle 11:03
Docente e = context.getBean(Docente.class, params);
Leonardo Pieramici — Oggi alle 11:36
@Autowired
    private ApplicationContext context;

    public Utente readFromId(int id)
    {

        String query="select * from utenti where id = ?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        Utente u=null;

        try
        {
           ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while(rs.next())
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("email", rs.getString(2));
                params.put("username", rs.getString(3));
                params.put("password", rs.getString(4));
                params.put("nome", rs.getString(5));
                params.put("cognome", rs.getString(6));
                params.put("tipo_utente", rs.getString(7));

                u = context.getBean(Utente.class, params);
 
            }
    }

    catch(SQLException exc){
        System.out.println("Errore nella select in SnackDAO");
    }
    finally{
        try{
            ps.close();
            rs.close();
        }
        catch(Exception exc){
            System.out.println("Errore chiusura PreparedStatement");
        }
    }
    return u;
}
Michelle — Oggi alle 14:29
CREATE DATABASE sulpezzogames;
USE sulpezzogames;

CREATE TABLE Utenti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
Mostra
sulpezzogames.sql
4 KB
﻿
CREATE DATABASE sulpezzogames;
USE sulpezzogames;

CREATE TABLE Utenti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(100),
    cognome VARCHAR(100),
    tipo_utente ENUM('admin', 'utente', 'autore') NOT NULL
);

CREATE TABLE News (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    immagine VARCHAR(255),
    data DATE,
    testo TEXT,
    autore INT,
    FOREIGN KEY (autore) REFERENCES Utenti(id)
);

CREATE TABLE Videogiochi (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255) NOT NULL,
    data DATE,
    genere VARCHAR(100),
    produzione VARCHAR(100),
    immagine VARCHAR(255)
);

CREATE TABLE Recensioni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255) NOT NULL,
    data DATE,
    punteggio INT,
    immagine VARCHAR(255),
    testo TEXT,
    autore INT,
    videogioco INT,
    FOREIGN KEY (autore) REFERENCES Utenti(id),
    FOREIGN KEY (videogioco) REFERENCES Videogiochi(id)
);

INSERT INTO Utenti (email, username, password, nome, cognome, tipo_utente)
VALUES 
    ('admin@example.com', 'admin', 'admin123', 'Admin', 'Admin', 'admin'),
    ('autore1@example.com', 'autore1', 'password1', 'Mario', 'Rossi', 'autore'),
    ('autore2@example.com', 'autore2', 'password2', 'Luigi', 'Verdi', 'autore'),
    ('utente1@example.com', 'utente1', 'password1', 'Luca', 'Neri', 'utente');

INSERT INTO News (titolo, categoria, immagine, data, testo, autore)
VALUES 
    ('Nuovo DLC per Super Mario Odyssey', 'Gaming', 'dlc_super_mario.jpg', '2024-03-20', 'Nintendo ha annunciato un nuovo DLC per Super Mario Odyssey.', 1),
    ('Annunciato nuovo videogioco Rockstar Games', 'Gaming', 'rockstar_game.jpg', '2024-03-21', 'Rockstar Games ha svelato il suo prossimo grande titolo.', 1),
    ('Aggiornamento per The Legend of Zelda: Breath of the Wild', 'Gaming', 'update_zelda.jpg', '2024-03-22', 'Nintendo ha rilasciato un nuovo aggiornamento per Breath of the Wild.', 1);

INSERT INTO Videogiochi (titolo, data, genere, produzione, immagine)
VALUES 
    ('Super Mario Odyssey', '2017-10-27', 'Platform', 'Nintendo', 'super_mario_odyssey.jpg'),
    ('The Legend of Zelda: Breath of the Wild', '2017-03-03', 'Action-Adventure', 'Nintendo', 'breath_of_the_wild.jpg'),
    ('Red Dead Redemption 2', '2018-10-26', 'Action-Adventure', 'Rockstar Games', 'red_dead_redemption_2.jpg');

INSERT INTO Recensioni (titolo, data, punteggio, immagine, testo, autore, videogioco)
VALUES 
    ('Recensione di Super Mario Odyssey', '2024-03-20', 9.0, 'recensione_super_mario.jpg', 'Eccellente gioco di piattaforme con tante sorprese.', 2, 1),
    ('Recensione di The Legend of Zelda: Breath of the Wild', '2024-03-21', 9.5, 'recensione_zelda.jpg', 'Un capolavoro assoluto, imperdibile per ogni appassionato di giochi d''avventura.', 2, 2),
    ('Recensione di Red Dead Redemption 2', '2024-03-22', 9.3, 'recensione_red_dead.jpg', "Un'avventura western incredibile con una storia coinvolgente.", 3, 3);
sulpezzogames.sql
4 KB