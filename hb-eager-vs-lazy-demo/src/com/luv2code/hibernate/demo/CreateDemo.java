package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			//-------use the session object to save Java Project--------
			
			// Create a object
			System.out.println("Creating a new Student Object");
			Instructor tempInstructor = new Instructor("Chad","Darby","Chad@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube.com/luv2code"," Luv 2 code");
			
			Instructor tempInstructor1 = new Instructor("Mouni","Uppala","mouni@luv2code.com");
			InstructorDetail tempInstructorDetail1 = new InstructorDetail("www.youtube.com/"," Luv 2 code");
			
			// Associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			tempInstructor1.setInstructorDetail(tempInstructorDetail1);

			
			// begin the transaction
			session.beginTransaction();
			
			//save the instructor object 
			//Note : this will ALSO save the details object 
			// because of CascadeType.ALL
			//
			System.out.println("Saving the instructor obj && instructorDetails" + tempInstructor);
			session.save(tempInstructor);
			session.save(tempInstructor1);

			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
				
		}
		finally{
			factory.close();
			
		}		
		
	}

}
