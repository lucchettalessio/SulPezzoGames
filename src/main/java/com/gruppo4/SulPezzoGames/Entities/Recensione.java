package com.gruppo4.SulPezzoGames.Entities;

public class Recensione extends Entity{
    
    private String titolo;
    private String data;
    private int punteggio;
    private String immagine;
    private String immagine2;
    private String testo;
    private Utente autore;
    private Videogioco videogioco;
    
    public Recensione(int id, String titolo, String data, int punteggio, String immagine, String immagine2, String testo) {
        super(id);
        this.titolo = titolo;
        this.data = data;
        this.punteggio = punteggio;
        this.immagine = immagine;
        this.immagine2 = immagine2;
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

    public String getImmagine2(){
        return immagine2;
    }

    public void setImmagine2(String immagine2){
        this.immagine2 = immagine2;
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
                + ", immagine2=" + immagine2 + ", testo=" + testo + ", autore=" + autore + ", videogioco=" + videogioco + "]";
    }

}
