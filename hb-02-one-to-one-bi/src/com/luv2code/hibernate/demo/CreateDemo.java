package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

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
			
			//Create the objects
			Instructor tempInstructor = 
					new Instructor("Ethan", "Salim", "ethan@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.ethan.youtube.com", "Piano and Minecraft");	
			
			
			//Associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//begin a transaction
			session.beginTransaction();
		
			System.out.println("Saving instructor: " + tempInstructor);
			//Save the instructor
			//Note this will also save the InstructorDetail object because of CascadeType.ALL
			session.save(tempInstructor);			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}
