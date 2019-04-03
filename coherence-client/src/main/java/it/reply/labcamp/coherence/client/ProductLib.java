package it.reply.labcamp.coherence.client;

import java.util.Collection;
import java.util.List;

import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;

public class ProductLib {

	private static final String PRODUCTCACHE = "PRODUCTCACHE";
	
	/*
	 * Put ProductValue in cache (put method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public void put(ProductValue value) {
	}

	/*
	 * Get product by product id
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public ProductValue getProductValue(Integer productId) {
		return null;
	}
	
	/*
	 * Get all products
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<ProductValue> getAllProducts() {
		return null;
	}
	
	/*
	 * Get all products by product id list
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<ProductValue> getProductsByIds(List<Integer> productId) {
		return null;
	}
	
	/*
	 * Get all products by price (GreaterFilter filter)
	 * https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm
	 */
	public Collection<ProductValue> getProductsWithPriceGreaterThan(Double price) {
		return null;
	}
	
	/*
	 * Get all products by Category and Price Range (Filter multi values)
	 * - Definire un MultiExtractor sui due campi da valutare ed utilizzarlo nella definizione dell'EqualsFilter
	 * https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm
	 */
	public Collection<ProductValue> getProductsByCategoryAndPriceRange(Category category, PriceRange priceRange) {
		return null;
	}
}
