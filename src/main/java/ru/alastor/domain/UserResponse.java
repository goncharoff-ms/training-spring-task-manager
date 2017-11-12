package ru.alastor.domain;

/**
 * Created on 10.11.17.
 *
 * @author Maxim Goncharov
 */
public class UserResponse {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String aboutUser;

    public UserResponse(String login, String email, String name, String surname, String aboutUser) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.aboutUser = aboutUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }
}
