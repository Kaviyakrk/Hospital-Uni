package com.hibernate.hospital.dao;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.hibernate.hospital.dto.Item;
import com.hibernate.hospital.dto.MedOrder;

public class ItemDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner sc = new Scanner(System.in);

	// Save Branch
	public static Item saveItem() {
		System.out.println("Enter Item id");
		String itemId = sc.next();
		System.out.println("Enter Item Name");
		String itemName = sc.next();
		System.out.println("Enter Item Type");
		String itemType = sc.next();
		System.out.println("Enter Item Price");
		double itemPrice = sc.nextDouble();
		System.out.println("Enter Expire Date");
		String expireDate = sc.next();

		Item item = new Item();
		item.setItemId(itemId);
		item.setItemName(itemName);
		item.setItemType(itemType);
		item.setItemPrice(itemPrice);
		item.setExpireDate(expireDate);
		return item;
	}

	public void saveItemRecords(String medOrderId1) {
		MedOrder medOrder= entityManager.find(MedOrder.class, medOrderId1);
		if(medOrder!=null) {
		Item item1=saveItem();
		List<Item> items =medOrder.getItems();
		items.add(item1);
		
		medOrder.setItems(items);
		
		entityTransaction.begin();
		entityManager.persist(item1);
		entityTransaction.commit();
		
		entityTransaction.begin();
		entityManager.merge(medOrder);
		entityTransaction.commit();
		}
		else {
			System.out.println("Medical Order is Doesn't match");
		}
	}
}
