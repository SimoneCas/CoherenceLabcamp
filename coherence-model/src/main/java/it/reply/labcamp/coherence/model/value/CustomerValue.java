package it.reply.labcamp.coherence.model.value;

import java.io.Serializable;

import it.reply.labcamp.coherence.model.key.CustomerKey;

public class CustomerValue implements Serializable{
	private static final long serialVersionUID = 1L;

	private CustomerKey customerKey;
	private String name;
	private String surname;
	private Integer age;
	private GeoZone area;
	private Integer orderCounter = 0;
	
	public CustomerKey getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(CustomerKey customerKey) {
		this.customerKey = customerKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public GeoZone getArea() {
		return area;
	}
	public void setArea(GeoZone area) {
		this.area = area;
	}
	
	public Integer getOrderCounter() {
		return orderCounter;
	}
	public void setOrderCounter(Integer orderCounter) {
		this.orderCounter = orderCounter;
	}
	public void incrementOrderCounter(){
		this.orderCounter++;
	}
	@Override
	public String toString() {
		return "CustomerValue [customerKey=" + customerKey + ", name=" + name + ", surname=" + surname + ", age=" + age
				+ ", area=" + area + ", orderCounter=" + orderCounter + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((customerKey == null) ? 0 : customerKey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderCounter == null) ? 0 : orderCounter.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		CustomerValue other = (CustomerValue) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (area != other.area)
			return false;
		if (customerKey == null) {
			if (other.customerKey != null)
				return false;
		} else if (!customerKey.equals(other.customerKey))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderCounter == null) {
			if (other.orderCounter != null)
				return false;
		} else if (!orderCounter.equals(other.orderCounter))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
}
