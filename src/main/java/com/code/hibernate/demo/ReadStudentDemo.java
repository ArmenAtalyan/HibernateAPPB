package com.code.hibernate.demo;

import com.code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
            Student theStudent = new Student("Jacky", "Chan", "JackCh@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the Student object
            System.out.println("Saving the student.");
            System.out.println(theStudent);
            session.save(theStudent);

            // commit transaction
            session.getTransaction().commit();

            // find out student's id; primary key
            System.out.println("Generated id: " + theStudent.getId());

            // get new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve Student based on the id: primary key
            System.out.println("\nGetting student with id: " + theStudent.getId());
            Student myStudent = session.get(Student.class, theStudent.getId());
            System.out.println("Get complete: " + myStudent);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");

        }finally {
            factory.close();
        }
    }
}
