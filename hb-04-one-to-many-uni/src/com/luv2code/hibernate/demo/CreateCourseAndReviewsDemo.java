package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		//Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//begin a transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
						
			//add some reviews
			tempCourse.addReview(new Review ("Great course...loved it!"));
			tempCourse.addReview(new Review ("Cool course. Job well done"));
			tempCourse.addReview(new Review ("What a dumb course, you are an idiot!"));
			
			
			//save the course...and leverage the cascade all
			System.out.println("Saving the course: " + tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
			
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
