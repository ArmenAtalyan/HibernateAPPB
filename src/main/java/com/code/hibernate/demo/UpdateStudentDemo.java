package com.code.hibernate.demo;

import com.code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            // get new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve from db Student by Id: primary key
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            // update Student by Id: primary key
            System.out.println("Update student");
            myStudent.setFirstName("Ayvaz");

            // commit the transaction
            session.getTransaction().commit();

            // create new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("\nUpdate email for all students");
            session.createQuery("UPDATE Student SET email='foo@gmail.com'")
                    .executeUpdate();

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }finally {
            factory.close();
        }
    }
}
