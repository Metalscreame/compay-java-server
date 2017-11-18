package com.compay.entity;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test {
    public static void main(String[] args) {
        User compayEntity = new User();
        compayEntity.setId(1);
        compayEntity.setName("Roman");
        compayEntity.setLogin("metal");
        compayEntity.setPassword("040593");
        compayEntity.setEmail("test@test.test");
        //compayEntity.setLastName("Stanislavovich");

        SessionFactory sessionFactory = buildSessionFactory(User.class);
        Session session = sessionFactory.openSession();
        session.save(compayEntity);
        User savedEntity =  session.get(User.class,1);


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
