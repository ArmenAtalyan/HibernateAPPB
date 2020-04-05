package com.code.hibernate.demo;

import com.code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("FROM Student").list();

            // display the students
            displayStudents(theStudents);

            // query students: lastName = 'Chan'
            theStudents = session.createQuery("FROM Student s WHERE s.lastName='Chan'").list();

            // display the students
            System.out.println("\nStudents, with last name is Chan");
            displayStudents(theStudents);

            // query students: lastName = 'Chan' Or first name = 'Jimmy
            theStudents = session.createQuery("FROM Student WHERE lastName='Chan' OR firstName='Jimmy'").list();

            // display the students
            System.out.println("\nStudents, with last name is Chan or first name is Jimmy");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");

        }finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student newStudent : theStudents) {
            System.out.println(newStudent);
        }
    }
}
