package com.hibernate.hospital.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class MedOrder {
	@Id
	private String medOrderId;
	private int quantity;
	private String invoiceNumber;
	private String paymentMode;

	@ManyToMany
	private List<Item> items;

	public String getMedOrderId() {
		return medOrderId;
	}

	public void setMedOrderId(String medOrderId) {
		this.medOrderId = medOrderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
