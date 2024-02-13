package com.hibernate.hospital.dao;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.hibernate.hospital.dto.Branch;
import com.hibernate.hospital.dto.Encounter;
import com.hibernate.hospital.dto.Item;
import com.hibernate.hospital.dto.MedOrder;
import com.hibernate.hospital.dto.Person;

public class EncounterDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner sc = new Scanner(System.in);

	// save Encounter data
	public static Encounter saveEncounter() {
		System.out.println("Enter Encounter id");
		String encounterId = sc.next();
		System.out.println("Enter Encounter Type");
		String encounterType = sc.next();
		System.out.println("Enter Bed Number");
		int bedNumber = sc.nextInt();
		System.out.println("Enter Doctor Name");
		String doctorName = sc.next();
		System.out.println("Enter Reason");
		String reason = sc.next();

		Encounter encounter1 = new Encounter();
		encounter1.setEncounterId(encounterId);
		encounter1.setEncounterType(encounterType);
		encounter1.setBedNumber(bedNumber);
		encounter1.setDoctorName(doctorName);
		encounter1.setReason(reason);
		return encounter1;
	}

	// save Encounter records
	public void saveEncounterRecords(String branchId, Encounter encounter) {
		Branch branch = entityManager.find(Branch.class, branchId);
		if (branch != null) {
			System.out.println("Select Choice");
			System.out.println("1.New Patient");
			System.out.println("2.Already appointed ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:// new User
				Person person = PersonDao.savePersonRecords();
				Encounter encounter1 = saveEncounter();
				List<Encounter> encounters = branch.getEncounters();
				encounters.add(encounter1);
				branch.setEncounters(encounters);
				encounter1.setPerson(person);

				entityTransaction.begin();
				entityManager.persist(encounter1);
				entityTransaction.commit();

				entityTransaction.begin();
				entityManager.merge(branch);
				entityTransaction.commit();
				break;

			case 2:
				System.out.println("Enter Person Id");
				String personId = sc.next();
				Person person1 = entityManager.find(Person.class, personId);
				if (person1 != null) {
					Encounter encounter2 = saveEncounter();
					List<Encounter> encounters2 = branch.getEncounters();
					encounters2.add(encounter2);
					branch.setEncounters(encounters2);
					encounter2.setPerson(person1);
					
					entityTransaction.begin();
					entityManager.persist(encounter2);
					entityTransaction.commit();

					entityTransaction.begin();
					entityManager.merge(branch);
					entityTransaction.commit();
					break;
				} else {
					System.out.println("Person not Exists..!");
				}
			}
		} else {
			System.out.println("Branch Not found..!");
		}
	}

	// Find Encounder Records
	public void findEncounterRecords(String branchId, String encounterId, String medOrderId) {
		System.out.println("Enter 1 for Encounder records");
		System.out.println("Enter 2 for Person Records");
		System.out.println("Enter 3 for Medical Order Records");
		System.out.println("Enter 4 for Item Records");
		int choice = sc.nextInt();
		switch (choice) {

		case 1:
			// Encounter Records
			Branch branch = entityManager.find(Branch.class, branchId);
			if (branch != null) {
				List<Encounter> encounters = branch.getEncounters();
				for (Encounter encounter : encounters) {
					System.out.println("===============Encounter Records================");
					System.out.println(encounter.getEncounterId());
					System.out.println(encounter.getEncounterType());
					System.out.println(encounter.getReason());
					System.out.println(encounter.getBedNumber());
					System.out.println(encounter.getDoctorName());
					System.out.println("=================================");
				}
			} else {
				System.out.println("Provide Valid Branch Id...!");
			}
			break;

		case 2:// Person Records
			Encounter encounter1 = entityManager.find(Encounter.class, encounterId);
			if (encounter1 != null) {
				Person person = encounter1.getPerson();
				System.out.println("===============Person Records================");
				System.out.println(person.getPersonId());
				System.out.println(person.getPersonName());
				System.out.println(person.getPersonAge());
				System.out.println(person.getPersonBlood());
				System.out.println(person.getGender());
				System.out.println("=================================");
			} else {
				System.out.println("Provide Valid encounter Id....!");
			}
			break;

		case 3:// MedOrder Records
			Encounter encounter2 = entityManager.find(Encounter.class, encounterId);
			if (encounter2 != null) {
				List<MedOrder> medOrders = encounter2.getMedOrders();
				for (MedOrder medOrder : medOrders) {
					System.out.println("===============MedOrder Records================");
					System.out.println(medOrder.getMedOrderId());
					System.out.println(medOrder.getInvoiceNumber());
					System.out.println(medOrder.getQuantity());
					System.out.println(medOrder.getPaymentMode());
					System.out.println("=================================");
				}

			} else {
				System.out.println("Provide Valid Encounter Id..!");
			}
			break;

		case 4:// Item records
			MedOrder medOrder = entityManager.find(MedOrder.class, medOrderId);
			if (medOrderId != null) {
				List<Item> items = medOrder.getItems();
				for (Item item : items) {
					System.out.println("==================Item Records===================");
					System.out.println(item.getItemId());
					System.out.println(item.getItemName());
					System.out.println(item.getItemType());
					System.out.println(item.getItemPrice());
					System.out.println(item.getExpireDate());
					System.out.println("=================================");
				}
			} else {
				System.out.println("Medical Order Item Doesn't Exist...!");
			}
		}
	}
}