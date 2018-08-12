package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			
			
			// begin the transaction
			session.beginTransaction();
			
			// create course
			Course tempCourse = new Course("Pacman - How To Score One Million Points");
			
			// saving the course
			System.out.println("\n Saving the course \n");
			session.save(tempCourse);
			System.out.println("\n Saved the course: " + tempCourse);

			// create students
			Student tempStudent1 = new Student("John","Deo","john@luv@code.com");
			Student tempStudent2 = new Student("Mary","Public","mary@luv@code.com");

			//add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);

			// saving the course
			System.out.println("\n Saving the students \n");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\n Saved the student1: " + tempCourse.getStudents());

			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
				
		}
		finally{
			session.close();
			factory.close();
			
		}		
		
	}

}
