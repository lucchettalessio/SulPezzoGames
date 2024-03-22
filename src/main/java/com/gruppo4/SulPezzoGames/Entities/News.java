package com.gruppo4.SulPezzoGames.Entities;

public class News extends Entity{

    private String titolo;
    private String categoria;
    private String immagine;
    private String data;
    private String testo;
    private int autore;
    
    public News(int id, String titolo, String categoria, String immagine, String data, String testo, int autore) {
        super(id);
        this.titolo = titolo;
        this.categoria = categoria;
        this.immagine = immagine;
        this.data = data;
        this.testo = testo;
        this.autore = autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "News [titolo=" + titolo + ", categoria=" + categoria + ", immagine=" + immagine + ", data=" + data
                + ", testo=" + testo + ", autore=" + autore + "]";
    }
    
}
