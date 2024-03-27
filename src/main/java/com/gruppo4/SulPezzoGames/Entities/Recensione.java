package com.gruppo4.SulPezzoGames.Entities;

public class Recensione extends Entity{
    
    private String titolo;
    private String data;
    private int punteggio;
    private String immagine;
    private String testo;
    private int autore;
    private int videogioco;
    private String nome;
    private String cognome;
    
    public Recensione(int id, String titolo, String data, int punteggio, String immagine, String testo, int autore,
            int videogioco, String nome, String cognome) {
        super(id);
        this.titolo = titolo;
        this.data = data;
        this.punteggio = punteggio;
        this.immagine = immagine;
        this.testo = testo;
        this.autore = autore;
        this.videogioco = videogioco;
        this.nome=nome;
        this.cognome=cognome;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getAutore() {
        return autore;
    }

    public void setAutore(int autore) {
        this.autore = autore;
    }

    public int getVideogioco() {
        return videogioco;
    }

    public void setVideogioco(int videogioco) {
        this.videogioco = videogioco;
    }
    public String getNome(){
        return nome;
    }
    public void setCognome(String cognome){this.cognome=cognome;}
    public String getCognome(){
        return cognome;
    }
    public void setNome(String nome){this.nome=nome;}

    @Override
    public String toString() {
        return "Recensione [titolo=" + titolo + ", data=" + data + ", punteggio=" + punteggio + ", immagine=" + immagine
                + ", testo=" + testo + ", autore=" + autore + ", videogioco=" + videogioco +  nome + cognome +"]";
    }

}
