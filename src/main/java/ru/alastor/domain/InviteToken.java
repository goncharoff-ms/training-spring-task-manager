package ru.alastor.domain;

import javax.persistence.*;

/**
 * Created on 03.11.17.
 *
 * @author Maxim Goncharov
 */

@Entity
@Table(name = "token_invite")
public class InviteToken {

    public InviteToken() {
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleUser roleUser;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InviteToken that = (InviteToken) o;

        if (!id.equals(that.id)) return false;
        if (!token.equals(that.token)) return false;
        return roleUser.equals(that.roleUser);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + token.hashCode();
        result = 31 * result + roleUser.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }
}
