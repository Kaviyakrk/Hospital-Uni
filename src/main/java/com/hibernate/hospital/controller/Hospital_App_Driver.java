package com.hibernate.hospital.controller;

import java.util.Scanner;
import com.hibernate.hospital.dao.EncounterDao;
import com.hibernate.hospital.dao.HospitalDao;
import com.hibernate.hospital.dao.ItemDao;
import com.hibernate.hospital.dao.MedOrderDao;
import com.hibernate.hospital.dao.PersonDao;
import com.hibernate.hospital.dto.Address;
import com.hibernate.hospital.dto.Branch;
import com.hibernate.hospital.dto.Encounter;
import com.hibernate.hospital.dto.Hospital;

public class Hospital_App_Driver {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		boolean loop=true;
		while(loop) {
			int choice=getChoiceFromUser();
		switch (choice) {
		case 1:
			Hospital hospital1 = new Hospital();
			new HospitalDao().saveHospitalRecords(hospital1);
			break;

		case 2:
			saveBranch();
			break;

		case 3:
			saveAddress();
			break;

		case 4:
			findHospitalRecords();
			break;

		case 5:
			saveEncounter();
			break;

		case 6:
			PersonDao.savePersonRecords();
			break;

		case 7:
			saveMedOrder();
			break;

		case 8:
			saveItem();
			break;
			
		case 9:
			findEncounterRecords();
			break;
		
		case 10:
			loop=false;
			break;
		}
	}
	}

	// save branch
	public static void saveBranch() {
		System.out.println("Enter Hospital Id");
		String hospitalId = sc.next();
		Branch branch = new Branch();
		new HospitalDao().saveBranchRecords(branch, hospitalId);
	}

	// save address
	public static void saveAddress() {
		System.out.println("Enter Branch Id");
		String branchId = sc.next();
		Address address = new Address();
		new HospitalDao().saveAddressRecords(address, branchId);
	}

	// find records
	public static void findHospitalRecords() {
		System.out.println("Enter Hospital Id");
		String hospitalId = sc.next();
		new HospitalDao().findHospitalRecords(hospitalId);
	}

	// save encounters
	public static void saveEncounter() {
		System.out.println("Enter Branch Id");
		String branchId = sc.next();

		Encounter encounter = new Encounter();
		new EncounterDao().saveEncounterRecords(branchId, encounter);
	}

	

	// save MedOrder
	public static void saveMedOrder() {
		System.out.println("Enter Encounter Id");
		String encounterId = sc.next();
		new MedOrderDao().saveMedOrderRecords(encounterId);
	}

	// save item
	public static void saveItem() {
		System.out.println("Enter Medical Order Id");
		String medorderId1 = sc.next();
		new ItemDao().saveItemRecords(medorderId1);
	}

	// find Encounter
	public static void findEncounterRecords() {
		System.out.println("Enter Encounter Id");
		String encounterId = sc.next();
		System.out.println("Enter Medical Order Id");
		String medOrderId = sc.next();
		System.out.println("Enter Branch Id");
		String branchId = sc.next();

		new EncounterDao().findEncounterRecords(branchId, encounterId, medOrderId);
	}
	
	//get choice from user
	public static int getChoiceFromUser() {
		System.out.println("Enter 1 for save Hospital");
		System.out.println("Enter 2 for save Branch");
		System.out.println("Enter 3 for save Address");
		System.out.println("Enter 4 for find Hospital records");
		System.out.println("Enter 5 for save Encounter");
		System.out.println("Enter 6 for save Person");
		System.out.println("Enter 7 for save MedOrder");
		System.out.println("Enter 8 for Save item");
		System.out.println("Enter 9 for Find Encounter Records");
		System.out.println("Enter 10 for Exit");
		int choice = sc.nextInt();
		return choice;
	}
}
