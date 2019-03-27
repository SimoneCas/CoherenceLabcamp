package it.reply.labcamp.coherence.model.value;

import java.io.Serializable;

import it.reply.labcamp.coherence.model.key.ProductKey;

public class ProductValue implements Serializable{
	private static final long serialVersionUID = 1L;

	private ProductKey productKey;
	private String name;
	private String description;
	private Double price;
	private Category category;
	private PriceRange priceRange;
	
	public ProductKey getProductKey() {
		return productKey;
	}
	public void setProductKey(ProductKey productKey) {
		this.productKey = productKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PriceRange getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(PriceRange priceRange) {
		this.priceRange = priceRange;
	}
	@Override
	public String toString() {
		return "ProductValue [productKey=" + productKey + ", name=" + name + ", description=" + description + ", price="
				+ price + ", category=" + category + ", priceRange=" + priceRange + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priceRange == null) ? 0 : priceRange.hashCode());
		result = prime * result + ((productKey == null) ? 0 : productKey.hashCode());
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
		ProductValue other = (ProductValue) obj;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceRange != other.priceRange)
			return false;
		if (productKey == null) {
			if (other.productKey != null)
				return false;
		} else if (!productKey.equals(other.productKey))
			return false;
		return true;
	}
}
