package ru.alastor.domain;

/**
 * Created on 03.11.17.
 *
 * @author Maxim Goncharov
 */


public class ResponseToken {

    public ResponseToken() {
    }

    private String userLogin;
    private String token;

    public ResponseToken(Token token) {
        this.userLogin = token.getOwner().getLogin();
        this.token = token.getToken();
    }

    public ResponseToken(String userLogin, String token) {
        this.userLogin = userLogin;
        this.token = token;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
