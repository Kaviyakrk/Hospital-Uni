package com.hibernate.hospital.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.hospital.dto.Address;
import com.hibernate.hospital.dto.Branch;
import com.hibernate.hospital.dto.Hospital;

public class HospitalDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner sc = new Scanner(System.in);

	// method for saveHospital
	public static Hospital saveHospital() {
		System.out.println("Enter Hospital id");
		String hospitalId = sc.next();
		System.out.println("Enter Hospital Name");
		String hospitalName = sc.next();
		System.out.println("Enter Hospital Type");
		sc.nextLine();
		String hospitalType = sc.nextLine();
		System.out.println("Enter Hospital Email");
		String hospitalemail = sc.next();
		System.out.println("Enter hospitalContactNo");
		long hospitalContactNo = sc.nextLong();

		Hospital hospital1 = new Hospital();
		hospital1.setHospitalId(hospitalId);
		hospital1.setHospitalName(hospitalName);
		hospital1.setHospitalType(hospitalType);
		hospital1.setHospitalemail(hospitalemail);
		hospital1.setHospitalContactNo(hospitalContactNo);
		return hospital1;
	}

	// method for save hospital
	public Hospital saveHospitalRecords(Hospital hospital) {
		Hospital hospital1 = saveHospital();
		entityTransaction.begin();
		entityManager.persist(hospital1);
		entityTransaction.commit();
		return hospital1;
	}

	// Save Branch
	public static Branch saveBranch() {
		System.out.println("Enter branch id");
		String branchId = sc.next();
		System.out.println("Enter branch Name");
		String branchName = sc.next();
		System.out.println("Enter branch City");
		String branchCity = sc.next();
		System.out.println("Enter branch Email");
		String branchEmail = sc.next();
		System.out.println("Enter branch Contact Number");
		long branchContactNo = sc.nextLong();

		Branch branch1 = new Branch();
		branch1.setBranchId(branchId);
		branch1.setBranchName(branchName);
		branch1.setBranchCity(branchCity);
		branch1.setBranchEmail(branchEmail);
		branch1.setBranchContactNo(branchContactNo);
		return branch1;
	}

	public void saveBranchRecords(Branch branch, String id) {
		Hospital hospitalId = entityManager.find(Hospital.class, id);
		if (hospitalId != null) {
			Branch branch1 = saveBranch();
			List<Branch> branches = hospitalId.getBranches();
			branches.add(branch1);
			hospitalId.setBranches(branches);
			
			entityTransaction.begin();
			entityManager.persist(branch1);
			entityTransaction.commit();
			
			entityTransaction.begin();
			entityManager.merge(hospitalId);
			entityTransaction.commit();	
		} else {
			System.out.println("Hospital Id doesn't exits ...Kindly Create Hospital First..!");
		}
	}

	/// Save Address
	public static Address saveAddress() {
		System.out.println("Enter Adddress id");
		String addressId = sc.next();
		System.out.println("Enter Adddress Door Number");
		int adressDoorNo = sc.nextInt();
		System.out.println("Enter Address Street");
		sc.nextLine();
		String addressStreet = sc.nextLine();
		System.out.println("Enter Address City");
		String addressCity = sc.next();
		System.out.println("Enter Address State");
		String addressState = sc.next();
		System.out.println("Enter Pincode Number");
		int addressPincode = sc.nextInt();

		Address address = new Address();
		address.setAddressId(addressId);
		address.setAdressDoorNo(adressDoorNo);
		address.setAddressStreet(addressStreet);
		address.setAddressCity(addressCity);
		address.setAddressState(addressState);
		address.setAddressPincode(addressPincode);
		return address;
	}

	public void saveAddressRecords(Address address, String id) {
		Branch branchId = entityManager.find(Branch.class, id);
		if (branchId != null) {
			Address address1 = saveAddress();
			branchId.setAddress(address1);

			entityTransaction.begin();
			entityManager.persist(address1);
			entityTransaction.commit();
		} else {
			System.out.println("Branch Not Found..!");
		}
	}

	// find hospitalRecord details
	public void findHospitalRecords(String id) {
		System.out.println("Enter 1 for hospital records");
		System.out.println("Enter 2 for Branch Records");
		System.out.println("Enter 3 for Addrtess Records");
		int choice = sc.nextInt();
		Hospital hospital = entityManager.find(Hospital.class, id);
		switch (choice) {

		case 1:
			if (hospital != null) {
				// Hospital Records
				System.out.println("===============Hospital================");
				System.out.println(hospital.getHospitalId());
				System.out.println(hospital.getHospitalName());
				System.out.println(hospital.getHospitalType());
				System.out.println(hospital.getHospitalemail());
				System.out.println(hospital.getHospitalContactNo());
				System.out.println("----------------------------");

			} else {
				System.out.println("Hospital Not Found..!");
			}
			break;
		case 2:// Branch records
			if (hospital != null) {
				List<Branch> branches = hospital.getBranches();
				for (Branch branch : branches) {
					System.out.println("===============Branch================");
					System.out.println(branch.getBranchId());
					System.out.println(branch.getBranchName());
					System.out.println(branch.getBranchCity());
					System.out.println(branch.getBranchEmail());
					System.out.println(branch.getBranchContactNo());
					System.out.println("----------------------------");
				}
			} else {
				System.out.println("Branch Id doesn't exist..!");
			}
			break;
		case 3:// Address records
			System.out.println("Enter Branch id");
			String branchId = sc.next();
			Branch branch = entityManager.find(Branch.class, branchId);
			if (branch != null) {
				Address address = branch.getAddress();
				System.out.println("==============Address==============");
				System.out.println(address.getAddressId());
				System.out.println(address.getAdressDoorNo());
				System.out.println(address.getAddressStreet());
				System.out.println(address.getAddressCity());
				System.out.println(address.getAddressState());
				System.out.println(address.getAddressPincode());
				System.out.println("----------------------------");
			} else {
				System.out.println("Branch Not Found...!");
			}
		}
	}
}