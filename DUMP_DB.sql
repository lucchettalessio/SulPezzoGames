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