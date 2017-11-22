package com.compay.entity;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test {
    public static void main(String[] args) {
        User user = new User();
        //user.setId(2);
        user.setName("Roman2");
        user.setLogin("metal2");
        user.setPassword("040593");
        user.setEmail("test@test.test");
        //compayEntity.setLastName("Stanislavovich");

        SessionFactory sessionFactory = buildSessionFactory(User.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);

        session.getTransaction().commit();



        session.close();
        sessionFactory.close();

    }


    private static SessionFactory buildSessionFactory(Class clazz){
        return new Configuration()
                .configure()
                .addAnnotatedClass(clazz)
                .buildSessionFactory();
    }
}
