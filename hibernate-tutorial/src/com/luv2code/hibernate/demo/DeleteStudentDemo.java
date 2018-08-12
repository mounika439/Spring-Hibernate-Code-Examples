package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try{
			int studentId = 1;
			session = factory.getCurrentSession();
			session.beginTransaction();

			//----- retrieve student based on the id : primary key---------------------------------------	
			System.out.println("\n Getting student with id : "+studentId );
			
			Student myStudent = session.get(Student.class, studentId);

			
//			Deleting student 
			
//			System.out.println("Deleting Student");
//			session.delete(myStudent);
//			System.out.println("deleted student :" +myStudent);
//			//commit the transaction
//			session.getTransaction().commit();
			
//			session = factory.getCurrentSession();
//			session.beginTransaction();
			
			//update email for all students
			System.out.println("Delete students id =2 ");
			
			session.createQuery("delete from Student where id = 2").executeUpdate();
			
			session.getTransaction().commit();
 
			System.out.println("Done");
				
		}
		finally{
			factory.close();
			
		}		
		
	}

}
