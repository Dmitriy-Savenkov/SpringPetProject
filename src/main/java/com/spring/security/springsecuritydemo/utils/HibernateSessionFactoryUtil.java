package com.spring.security.springsecuritydemo.utils;

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.model.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // объект конфигураций Configuration, и передаем ему те классы, которые он должен воспринимать
                //как сущности — User и Post
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Post.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e){
                System.out.println("Exception! " + e);
            }
        }
        return sessionFactory;
    }
}
