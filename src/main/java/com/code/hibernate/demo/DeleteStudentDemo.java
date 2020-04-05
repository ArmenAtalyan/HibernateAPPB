package com.code.hibernate.demo;

import com.code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 2;

            // get new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve from db Student by Id: primary key
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

//            // delete Student by Id: primary key
//            System.out.println("Delete student");
//            session.delete(myStudent);

            // delete email for all students
            System.out.println("\nDelete student 2");
            session.createQuery("DELETE FROM Student WHERE id=5")
                    .executeUpdate();

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }finally {
            factory.close();
        }
    }
}
