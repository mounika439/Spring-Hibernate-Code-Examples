package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			
			// begin the transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudents(theStudents);
			
			// query students
			theStudents = session.createQuery("from Student s where s.lastName = 'Duck'").getResultList();
			
			//display students
			System.out.println("\n Students who last name is duck");
			displayStudents(theStudents);
			

			// query students
			theStudents = session.createQuery("from Student s where s.lastName = 'Duck' or s.firstName = 'Wall3'").getResultList();
			
			//display students
			System.out.println("\n Students who last name is duck and first nae is duck");
			displayStudents(theStudents);
		
			
			// query students
			theStudents = session.createQuery("from Student s where s.email like '%luv2code.com' ").getResultList();
			
			//display students
			System.out.println("\n Students who last name is duck and first name is duck");
			displayStudents(theStudents);

			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
				
		}
		finally{
			factory.close();
			
		}		
		
	}

	private static void displayStudents(List<Student> displayStudents) {
		for(Student tempStudent : displayStudents){
			System.out.println(tempStudent);
		}
	}

}
