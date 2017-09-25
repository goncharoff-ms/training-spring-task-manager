package ru.alastor.domain;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Set;

/**
 * Created on 18.09.17.
 *
 * @author Maxim Goncharov
 */
@Entity
@Table(name = "user")
public class User {

    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String login;

    private String password;

    private String email;

    @Column(name = "about_user")
    private String aboutUser;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private RoleUser role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

   /* public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }*/
}
