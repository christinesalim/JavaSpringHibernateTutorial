package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)				
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//begin a transaction
			session.beginTransaction();
		
			//Get instructor by primary key
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);
			System.out.println("Found Instructor: " + tempInstructor);
			
			//delete the instructor
			if (tempInstructor != null) {
				System.out.println("Deleting instructor " + tempInstructor);
				
				//Delete will cascade and delete the instructor detail object
				session.delete(tempInstructor);
			}			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}
