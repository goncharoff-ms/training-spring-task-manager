package ru.alastor.domain;

import javax.persistence.*;

/**
 * Created on 30.11.17.
 *
 * @author Maxim Goncharov
 */
@Entity
@Table(name = "application_sign")
public class ApplicationSign  {

    public ApplicationSign(Long applicationId, RoleUser userRole) {
        this.applicationId = applicationId;
        this.userRole = userRole;
    }

    public ApplicationSign() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private RoleUser userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public RoleUser getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleUser userRole) {
        this.userRole = userRole;
    }
}
