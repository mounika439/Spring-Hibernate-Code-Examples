package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

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
			
			int studentId = 2;
			Student tempStudent = session.get(Student.class,studentId);
			
			//get student mary from database
			System.out.println("\n Loaded student:"+ tempStudent);
			System.out.println("Courses:" + tempStudent.getCourses() +"\n");
			
			//create more courses
			Course tempCourse1 = new Course("Runiks Cube - How to Speed Cube");
			Course tempCourse2 = new Course("Atari 2600 - Game Development");

			//add student to coourses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
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
