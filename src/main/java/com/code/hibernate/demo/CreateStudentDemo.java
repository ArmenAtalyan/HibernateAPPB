package com.code.hibernate.demo;

import com.code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the Student object
            System.out.println("Creating new Student object");
            Student theStudent = new Student("Sahak", "Karapetyan", "SK@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the Student object
            System.out.println("Saving the student.");
            session.save(theStudent);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");

        }finally {
            factory.close();
        }
    }
}
