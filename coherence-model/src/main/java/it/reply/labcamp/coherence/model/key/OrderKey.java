package it.reply.labcamp.coherence.model.key;

import java.io.Serializable;

public class OrderKey implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private CustomerKey customerKey;
	
	public OrderKey() {
	}

	public OrderKey(Integer orderId) {
		this.orderId = orderId;
	}
	
	public OrderKey(Integer orderId, CustomerKey customerKey) {
		this.orderId = orderId;
		this.customerKey = customerKey;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public CustomerKey getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(CustomerKey customerKey) {
		this.customerKey = customerKey;
	}

	@Override
	public String toString() {
		return "OrderKey [orderId=" + orderId + ", customerKey=" + customerKey + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerKey == null) ? 0 : customerKey.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		OrderKey other = (OrderKey) obj;
		if (customerKey == null) {
			if (other.customerKey != null)
				return false;
		} else if (!customerKey.equals(other.customerKey))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}
}
