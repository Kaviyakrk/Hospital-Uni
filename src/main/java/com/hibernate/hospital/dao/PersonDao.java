package com.hibernate.hospital.dao;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.hibernate.hospital.dto.Person;

public class PersonDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner sc = new Scanner(System.in);


	// Save Person
	public static Person savePersonRecords() {
		System.out.println("Enter Person id");
		String personId = sc.next();
		System.out.println("Enter Person Name");
		String personName = sc.next();
		System.out.println("Enter Person Age");
		int personAge = sc.nextInt();
		System.out.println("Enter Person Blood");
		String personBlood = sc.next();
		System.out.println("Enter Person Gender");
		String gender = sc.next();

		Person person = new Person();
		person.setPersonId(personId);
		person.setPersonName(personName);
		person.setPersonAge(personAge);
		person.setPersonBlood(personBlood);
		person.setGender(gender);
		
		entityTransaction.begin();
		entityManager.persist(person);
		entityTransaction.commit();
		return person;
	}
}
