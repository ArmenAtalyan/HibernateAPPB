package com.code.hibernate.demo;

import com.code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the Students objects
            System.out.println("Creating new Student object");
            Student theStudent1 = new Student("Nil", "Smith", "NS@gmail.com");
            Student theStudent2 = new Student("Jimmy", "Morgan", "JM@gmail.com");
            Student theStudent3 = new Student("Nensy", "Langy", "NenL@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the Student object
            System.out.println("Saving the student.");
            session.save(theStudent1);
            session.save(theStudent2);
            session.save(theStudent3);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");

        }finally {
            factory.close();
        }
    }
}
