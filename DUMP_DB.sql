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
    immagine2 VARCHAR(255),
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
    ('Nuovo DLC per Super Mario Odyssey', 'Gaming', 'https://fs-prod-cdn.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_SuperMarioOdyssey.jpg', '2024-03-20', 'Nintendo ha annunciato un nuovo DLC per Super Mario Odyssey.', 1),
    ('Annunciato nuovo videogioco Rockstar Games', 'Gaming', 'https://sm.ign.com/t/ign_it/screenshot/default/ga_fwmt.1280.jpg', '2024-03-21', 'Rockstar Games ha svelato il suo prossimo grande titolo.', 1),
    ('Aggiornamento per The Legend of Zelda: Breath of the Wild', 'Gaming', 'https://cdn.sortiraparis.com/images/80/66131/789722-the-legend-of-zelda-tears-of-the-kingdom-breath-of-the-wild-2-bande-annonce-et-date-de-sortie.jpg', '2024-03-22', 'Nintendo ha rilasciato un nuovo aggiornamento per Breath of the Wild.', 1);

INSERT INTO Videogiochi (titolo, data, genere, produzione, immagine)
VALUES 
    ('Red Dead Redemption 2', '2018-10-26', 'Action-Adventure', 'Rockstar Games', 'https://cdn1.epicgames.com/b30b6d1b4dfd4dcc93b5490be5e094e5/offer/RDR2476298253_Epic_Games_Wishlist_RDR2_2560x1440_V01-2560x1440-2a9ebe1f7ee202102555be202d5632ec.jpg'),
    ('Super Mario Odyssey', '2017-10-27', 'Platform', 'Nintendo', 'https://fs-prod-cdn.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_SuperMarioOdyssey.jpg'),
    ('P3: Persona 3: Reload [Shin Megami Tensei]','2024-03-20','Platform','Rockstar Games','https://www.pcgamesn.com/wp-content/sites/pcgamesn/2023/10/persona-3-reload-release-date.jpg'),
    ('Call of Duty: Modern Warfare III','2018-11-24','Action-Adventure','Rockstar Games','https://sm.ign.com/t/ign_it/screenshot/default/ga_fwmt.1280.jpg'),
    ('Suicide Squad: Kill the Justice League','2014-09-17','Action-Adventure','Nintendo','https://orgoglionerd.it/wp-content/uploads/2024/03/Suicide-Squad-Kill-the-Justice-League-Stagione-1.jpg'),
    ('The Legend of Zelda: Breath of the Wild', '2017-03-03', 'Action-Adventure', 'Nintendo', 'https://cdn.sortiraparis.com/images/80/66131/789722-the-legend-of-zelda-tears-of-the-kingdom-breath-of-the-wild-2-bande-annonce-et-date-de-sortie.jpg');
INSERT INTO Recensioni (titolo, data, punteggio, immagine, testo, autore, videogioco)
VALUES 
('Super Mario Odyssey', '2024-03-20', 9.0, 'https://fs-prod-cdn.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_SuperMarioOdyssey.jpg', 'Eccellente gioco di piattaforme con tante sorprese.', 2, 1),
('The Legend of Zelda: Breath of the Wild', '2024-03-21', 9.5, 'https://cdn.sortiraparis.com/images/80/66131/789722-the-legend-of-zelda-tears-of-the-kingdom-breath-of-the-wild-2-bande-annonce-et-date-de-sortie.jpg', 'Un capolavoro assoluto, imperdibile per ogni appassionato di giochi d''avventura.', 2, 2),
('Red Dead Redemption 2', '2024-03-22', 9.3, 'https://cdn1.epicgames.com/b30b6d1b4dfd4dcc93b5490be5e094e5/offer/RDR2476298253_Epic_Games_Wishlist_RDR2_2560x1440_V01-2560x1440-2a9ebe1f7ee202102555be202d5632ec.jpg', "Un'avventura western incredibile con una storia coinvolgente.", 3, 3),
('Call of Duty: Modern Warfare III', '2024-03-20', 6, 'https://sm.ign.com/t/ign_it/screenshot/default/ga_fwmt.1280.jpg', 'Eccellente gioco sparatutto', 2, 3),
('Suicide Squad: Kill the Justice League', '2024-03-21', 8, 'https://orgoglionerd.it/wp-content/uploads/2024/03/Suicide-Squad-Kill-the-Justice-League-Stagione-1.jpg', 'Un capolavoro assoluto, imperdibile per ogni appassionato di giochi d''avventura.', 2, 2),
('P3: Persona 3: Reload [Shin Megami Tensei]', '2024-03-22', 2, 'https://www.pcgamesn.com/wp-content/sites/pcgamesn/2023/10/persona-3-reload-release-date.jpg', 'Multiple Video Game', 3, 1);

alter table recensioni
add column immagine2 varchar(300);

select * from news;

UPDATE `sulpezzogames`.`recensioni` SET `immagine2` = 'https://images.kinguin.net/g/carousel-main-mobile/media/category/s/u/super-mario-odyssey_1509187305_1_1.jpg' WHERE (`id` = '1');
UPDATE `sulpezzogames`.`recensioni` SET `immagine2` = 'https://assets.nintendo.com/image/upload/c_fill,w_1200/q_auto:best/f_auto/dpr_2.0/ncom/software/switch/70010000000025/7137262b5a64d921e193653f8aa0b722925abc5680380ca0e18a5cfd91697f58' WHERE (`id` = '2');
UPDATE `sulpezzogames`.`recensioni` SET `immagine2` = 'https://sm.ign.com/t/ign_it/screenshot/default/rdr2-copertina_ucy9.1280.jpg' WHERE (`id` = '3');
UPDATE `sulpezzogames`.`recensioni` SET `immagine2` = 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2023/12/call-of-duty-modern-warfare-3-season-1.jpg?w=1000&h=600&crop=1' WHERE (`id` = '4');
UPDATE `sulpezzogames`.`recensioni` SET `immagine2` = 'https://sm.ign.com/t/ign_it/preview/s/suicide-sq/suicide-squad-kill-the-justice-league-preview-we-played-it-a_qw51.1280.jpg' WHERE (`id` = '5');
UPDATE `sulpezzogames`.`recensioni` SET `immagine2` = 'https://www.digitaltrends.com/wp-content/uploads/2023/01/Persona-3-Portable.jpg?resize=1200%2C630&p=1' WHERE (`id` = '6');

UPDATE `sulpezzogames`.`news` SET `testo` = 'Nintendo ha rilasciato un nuovo aggiornamento per Breath of the Wild. Breath of the Wild potrebbe essere considerato persino lo Zelda originale, quello da cui scaturiscono i tasselli che comporranno le varie versioni di Hyrule (Sempre più martoriate, e oscure, annegate, e poi così fragili da diventare aeree).' WHERE (`id` = '3');
UPDATE `sulpezzogames`.`news` SET `titolo` = 'Annunciato nuovo videogioco Activision', `testo` = 'Activision ha svelato il suo prossimo grande titolo. Call of Duty Modern Warfare 3 è il nuovo capitolo dell\'iconica serie sparatutto targata Activision. Il titolo, il cui sviluppo è guidato da Sledgehammer Games in collaborazione con Treyarch e Raven Software. COD Modern Warfare 3 esce il 10 novembre 2023 su PC, PS4, Xbox One, PlayStation 5 e Xbox Series X|S. ' WHERE (`id` = '2');
UPDATE `sulpezzogames`.`news` SET `testo` = 'Nintendo ha annunciato un nuovo DLC per Super Mario Odyssey. Questa volta Bowser fa sul serio. Non si è limitato a rapire la soave Peach, ma è intenzionato addirittura a sposarla. A dirla tutta, quando Mario lo affronta all\'inizio del gioco è già imbellettato nel suo vestito bianco, pronto a rendere ufficiale l\'unione con la principessa del Regno dei Funghi.' WHERE (`id` = '1');


