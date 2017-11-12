package ru.alastor.domain;

import javax.persistence.*;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "_order")
    private String order;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_review")
    private String dateReview;

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

    public String getDateReview() {
        return dateReview;
    }

    public void setDateReview(String dateReview) {
        this.dateReview = dateReview;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!info.equals(that.info)) return false;
        if (!order.equals(that.order)) return false;
        if (!userId.equals(that.userId)) return false;
        return dateReview.equals(that.dateReview);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + info.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + dateReview.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", order='" + order + '\'' +
                ", userId=" + userId +
                ", dateReview='" + dateReview + '\'' +
                '}';
    }
}
