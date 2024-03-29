package com.gruppo4.SulPezzoGames.Entities;

public class Recensione extends Entity{
    
    private String titolo;
    private String data;
    private int punteggio;
    private String immagine;
    private String testo;
    private Utente autore;
    private Videogioco videogioco;
    
    public Recensione(int id, String titolo, String data, int punteggio, String immagine, String testo) {
        super(id);
        this.titolo = titolo;
        this.data = data;
        this.punteggio = punteggio;
        this.immagine = immagine;
        this.testo = testo;
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

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    public Videogioco getVideogioco() {
        return videogioco;
    }

    public void setVideogioco(Videogioco videogioco) {
        this.videogioco = videogioco;
    }

    @Override
    public String toString() {
        return "Recensione [titolo=" + titolo + ", data=" + data + ", punteggio=" + punteggio + ", immagine=" + immagine
                + ", testo=" + testo + ", autore=" + autore + ", videogioco=" + videogioco + "]";
    }

}
