package ru.alastor.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created on 18.09.17.
 *
 * @author Maxim Goncharov
 */
@Entity
@Table(name = "role_user")
public class RoleUser {

    public RoleUser() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public Set<User> getTenants() {
        return tenants;
    }

    public void setTenants(Set<User> tenants) {
        this.tenants = tenants;
    }
    */
}
