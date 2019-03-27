package it.reply.labcamp.coherence.model.value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;
import it.reply.labcamp.coherence.model.key.ProductKey;

public class OrderValue implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private OrderKey orderKey;
	private Double total;
	private String note;
	private CustomerKey customer;
	private Integer tax;
	private List<ProductKey> products = new ArrayList<>();
	
	public OrderKey getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(OrderKey orderKey) {
		this.orderKey = orderKey;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public CustomerKey getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerKey customer) {
		this.customer = customer;
	}
	public List<ProductKey> getProducts() {
		return products;
	}
	public void setProducts(List<ProductKey> products) {
		this.products = products;
	}
	
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	@Override
	public String toString() {
		return "OrderValue [orderKey=" + orderKey + ", total=" + total + ", note=" + note + ", customer=" + customer
				+ ", tax=" + tax + ", products=" + products + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((orderKey == null) ? 0 : orderKey.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderValue other = (OrderValue) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (orderKey == null) {
			if (other.orderKey != null)
				return false;
		} else if (!orderKey.equals(other.orderKey))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (tax == null) {
			if (other.tax != null)
				return false;
		} else if (!tax.equals(other.tax))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
}
