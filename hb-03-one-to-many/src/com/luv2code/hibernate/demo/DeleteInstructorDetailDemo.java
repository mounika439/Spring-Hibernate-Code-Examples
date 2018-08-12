package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
		
			//start a transaction
			session.beginTransaction();
			
			int theId = 3;
			
			// get the instructor detail object
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail object
			System.out.println("tempInstructorDetail : "+tempInstructorDetail);
			
			// print associated instructor
			System.out.println("the associacted Instructor " + tempInstructorDetail.getInstructor());
			
			System.out.println("deleting object "+ tempInstructorDetail);
			
			session.delete(tempInstructorDetail);
			
			//remove the associated object reference 
			// break bi-directional link 
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
				
		}catch(Exception e){
			e.printStackTrace(); // handle the leak issue
			
		}
		finally{
			factory.close();
			
		}		
		
	}

}
