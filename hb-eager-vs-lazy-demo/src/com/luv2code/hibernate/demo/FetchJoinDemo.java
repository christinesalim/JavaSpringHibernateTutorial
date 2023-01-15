package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//begin a transaction
			session.beginTransaction();
			
			//get the instructor from the db
			int id = 1;
						
			Query<Instructor> query = 
					session.createQuery("select i from Instructor as i "
										+ "JOIN FETCH i.courses "
										+ "where i.id=:theInstructorId", 
									Instructor.class);
			
			//Set the parameter on the query
			query.setParameter("theInstructorId", id);
			
			//execute the query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			
			System.out.println("luv2code: Instructor: " + tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			System.out.println("Session is now closed");
			
			//Session is closed, but we already loaded the data while the session was open
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			
			
			System.out.println("luv2code: Done!");
		}
		finally {
			session.close();
			
			factory.close();
		}

	}

}
