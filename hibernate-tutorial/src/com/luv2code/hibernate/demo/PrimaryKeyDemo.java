package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create session factory
				SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
				
				//create session
				Session session = factory.getCurrentSession();
				
				try{
					//-------use the session object to save Java Project--------
					
					// Create 3 student objects
					System.out.println("Creating 3 Student Object");
					Student tempStudent1 = new Student("Paul1","Wall1","paul@luv2code.com");
					Student tempStudent2 = new Student("Paul2","Wall2","paul@luv2code.com");
					Student tempStudent3 = new Student("Paul3","Wall3","paul@luv2code.com");

					// begin the transaction
					session.beginTransaction();
					
					//save the student object
					System.out.println("Saving the student obj");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					
					//commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done");
						
				}
				finally{
					factory.close();
					
				}		
	}

}
