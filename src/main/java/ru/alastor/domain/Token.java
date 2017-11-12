package ru.alastor.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created on 23.10.17.
 *
 * @author Maxim Goncharov
 */
@Entity
@Table(name = "token_active")
public class Token {

    public Token() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "date_end")
    private Timestamp timestamp;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (!id.equals(token1.id)) return false;
        if (!token.equals(token1.token)) return false;
        if (!owner.equals(token1.owner)) return false;
        return timestamp.equals(token1.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + token.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
