package ru.alastor.domain;

import javax.persistence.*;

/**
 * Created on 23.09.17.
 *
 * @author Maxim Goncharov
 */

@Entity
@Table(name = "application_to_user")
public class ApplicationToUser {

    public ApplicationToUser() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private Application application;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}
