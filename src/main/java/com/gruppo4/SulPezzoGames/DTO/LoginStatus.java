package com.gruppo4.SulPezzoGames.DTO;

public class LoginStatus {
    private String token;

    public void setToken(String tipo_utente, boolean isLogin, int idUtente) {
        
        token = tipo_utente + "-" + isLogin + "-" + idUtente;
    }

    public String getRuolo() {
        return token.split("-")[0];
    }

    public boolean getLogin() {
        return Boolean.parseBoolean(token.split("-")[1]);
    }

    public int getIdpersona() {
        return Integer.parseInt(token.split("-")[2]);
    }

    public String getToken() {
        return token;
    }

}
