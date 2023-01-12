package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create student object
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");
			
			//begin a transaction
			session.beginTransaction();
		
			//save the student
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//Retrieving object from the database
			System.out.println("Saved student. Generated ID: " + tempStudent.getId());
			
			//get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student by the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}
