package ru.alastor.domain;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created on 18.09.17.
 * @author Maxim Goncharov
 */
@Entity
@Table(name = "application")
public class Application {

    public Application() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String info;

    private String order;


    private Long userId;

    @Column(name = "date_review")
    private Timestamp dateReview;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Timestamp getDateReview() {
        return dateReview;
    }

    public void setDateReview(Timestamp dateReview) {
        this.dateReview = dateReview;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
