package ru.alastor.domain.builder;

import ru.alastor.domain.Application;

/**
 * Created on 01.10.17.
 *
 * @author Maxim Goncharov
 */
public class ApplicationBuilder  {

    private static ApplicationBuilder ourInstance = new ApplicationBuilder();

    public static ApplicationBuilder getInstance() {
        return ourInstance;
    }

    private static Application application;

    public ApplicationBuilder createApplication() {
        application = new Application();
        return ourInstance;
    }


    public ApplicationBuilder setName(String name) {
        application.setName(name);
        return ourInstance;
    }

    public ApplicationBuilder setOrder(String order) {
        application.setOrder(order);
        return ourInstance;
    }

    public ApplicationBuilder setInfo(String info) {
        application.setInfo(info);
        return ourInstance;
    }

    public ApplicationBuilder setUserId(Long id) {
        application.setUserId(id);
        return ourInstance;
    }

    public ApplicationBuilder setDateReview(String time) {
        application.setDateReview(time);
        return ourInstance;
    }

    public Application getApplication() {
        return application;
    }

    private ApplicationBuilder() {
    }
}
