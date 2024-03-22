package com.gruppo4.SulPezzoGames.Entities;

public class Utente extends Entity {
    
    private String email;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String tipo_utente;
    
    public Utente(int id, String email, String username, String password, String nome, String cognome,
            String tipo_utente) {
        super(id);
        this.email = email;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.tipo_utente = tipo_utente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTipo_utente() {
        return tipo_utente;
    }

    public void setTipo_utente(String tipo_utente) {
        this.tipo_utente = tipo_utente;
    }

    @Override
    public String toString() {
        return "Utente [email=" + email + ", username=" + username + ", password=" + password + ", nome=" + nome
                + ", cognome=" + cognome + ", tipo_utente=" + tipo_utente + "]";
    }

}
