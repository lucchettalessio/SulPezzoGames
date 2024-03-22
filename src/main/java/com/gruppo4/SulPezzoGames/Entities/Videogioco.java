package com.gruppo4.SulPezzoGames.Entities;

public class Videogioco extends Entity{
    
    private String titolo;
    private String data;
    private String genere;
    private String produzione;
    private String immagine;
    
    public Videogioco(int id, String titolo, String data, String genere, String produzione, String immagine) {
        super(id);
        this.titolo = titolo;
        this.data = data;
        this.genere = genere;
        this.produzione = produzione;
        this.immagine = immagine;
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

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getProduzione() {
        return produzione;
    }

    public void setProduzione(String produzione) {
        this.produzione = produzione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    @Override
    public String toString() {
        return "Videogioco [titolo=" + titolo + ", data=" + data + ", genere=" + genere + ", produzione=" + produzione
                + ", immagine=" + immagine + "]";
    }
    
}
