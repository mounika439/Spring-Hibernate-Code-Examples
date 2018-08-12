package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			
			
			// begin the transaction
			session.beginTransaction();

			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Luv 2 Code : Instructor"+tempInstructor);
			System.out.println("Luv 2 code : Courses"+ tempInstructor.getCourses());
			
			
			//commit transaction
			session.getTransaction().commit();
			// option 1 : for Lazy Loading :: calling getter method before session close
			System.out.println("Luv 2 code : Courses"+ tempInstructor.getCourses());
			
			System.out.println("Done");
				
		}
		finally{
			session.close();
			factory.close();
			
		}		
		
	}

}
