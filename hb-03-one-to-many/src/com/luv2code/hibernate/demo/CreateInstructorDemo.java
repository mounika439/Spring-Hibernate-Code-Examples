package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			// Create a object
			System.out.println("Creating a new Student Object");
			Instructor tempInstructor = new Instructor("Chad","Darby","Chad@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube.com/luv2code"," Games");
						
			// Associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			
			// begin the transaction
			session.beginTransaction();
			
			//save the instructor object 
			//Note : this will ALSO save the details object 
			// because of CascadeType.ALL
			//
			System.out.println("Saving the instructor obj && instructorDetails" + tempInstructor);
			session.save(tempInstructor);

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
