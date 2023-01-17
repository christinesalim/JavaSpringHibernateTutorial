package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteMaryStudentDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//begin a transaction
			session.beginTransaction();
			
			//get the student Mary from the database
			int id = 2;
			Student student = session.get(Student.class, id);
			System.out.println("\nLoaded student: " + student);
			System.out.println("Courses: " + student.getCourses());
					
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			
			factory.close();
		}

	}

}
