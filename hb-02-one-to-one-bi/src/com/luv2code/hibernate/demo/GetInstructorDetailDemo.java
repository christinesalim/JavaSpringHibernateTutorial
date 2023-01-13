package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

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
		
			// get the instructor detail object
			int id = 2;
			//print the instructor detail
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, id);
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
						
			//print the associated instructor
			System.out.println("The associated instructor: " 
					+ tempInstructorDetail.getInstructor());
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}
