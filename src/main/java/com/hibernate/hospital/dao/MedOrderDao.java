package com.hibernate.hospital.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.hospital.dto.Encounter;
import com.hibernate.hospital.dto.MedOrder;

public class MedOrderDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner sc = new Scanner(System.in);

	// save person
	public static MedOrder saveMedOrder() {
		System.out.println("Enter Medical Order id");
		String orderId = sc.next();
		System.out.println("Enter invoice");
		String invoice = sc.next();
		System.out.println("Enter Quantity");
		int quantity = sc.nextInt();
		System.out.println("Enter Payment Mode");
		String paymentMode = sc.next();

		MedOrder medOrder = new MedOrder();
		medOrder.setMedOrderId(orderId);
		medOrder.setInvoiceNumber(invoice);
		medOrder.setQuantity(quantity);
		medOrder.setPaymentMode(paymentMode);
		return medOrder;
	}

	// Save MedOrder Records
	public void saveMedOrderRecords(String encounterId) {
		Encounter encounter = entityManager.find(Encounter.class, encounterId);
		if (encounter != null) {
			MedOrder medOrder1 = saveMedOrder();

			List<MedOrder> medOrders = new ArrayList<MedOrder>();
			medOrders.add(medOrder1);
			encounter.setMedOrders(medOrders);

			entityTransaction.begin();
			entityManager.persist(medOrder1);
			entityTransaction.commit();
			
			entityTransaction.begin();
			entityManager.merge(encounter);
			entityTransaction.commit();
		}
		else {
			System.out.println("Encounter Id does not match..!");
		}
	}
}
