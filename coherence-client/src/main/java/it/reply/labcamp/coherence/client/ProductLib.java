package it.reply.labcamp.coherence.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.GreaterFilter;

import it.reply.labcamp.coherence.extractor.ExtractorRegister;
import it.reply.labcamp.coherence.model.key.ProductKey;
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
		CacheFactory.getCache(PRODUCTCACHE).put(value.getProductKey(), value);
	}

	/*
	 * Get product by product id
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public ProductValue getProductValue(Integer productId) {
		NamedCache<ProductKey, ProductValue> productCache = CacheFactory.getCache(PRODUCTCACHE);
		
		ProductKey key = new ProductKey(productId);
		return productCache.get(key);
	}
	
	/*
	 * Get all products
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<ProductValue> getAllProducts() {
		NamedCache<ProductKey, ProductValue> productCache = CacheFactory.getCache(PRODUCTCACHE);
		
		return productCache.values();
	}
	
	/*
	 * Get all products by product id list
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<ProductValue> getProductsByIds(List<Integer> productId) {
		NamedCache<ProductKey, ProductValue> productCache = CacheFactory.getCache(PRODUCTCACHE);
		
		List<ProductKey> productKeys = productId.stream().map(id -> new ProductKey(id)).collect(Collectors.toList());
		return productCache.getAll(productKeys).values();
	}
	
	/*
	 * Get all products by price (GreaterFilter filter)
	 * https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm
	 */
	public Collection<ProductValue> getProductsWithPriceGreaterThan(Double price) {
		NamedCache<ProductKey, ProductValue> productCache = CacheFactory.getCache(PRODUCTCACHE);
		
		ValueExtractor<ProductValue, Double> extractor = new ReflectionExtractor<>("getPrice");
		return productCache.values(new GreaterFilter<ProductValue, Double>(extractor, price));
	}
	
	/*
	 * Get all products by Category and Price Range (Filter multi values)
	 * - Definire un MultiExtractor sui due campi da valutare ed utilizzarlo nella definizione dell'EqualsFilter
	 * https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm
	 */
	public Collection<ProductValue> getProductsByCategoryAndPriceRange(Category category, PriceRange priceRange) {
		NamedCache<ProductKey, ProductValue> productCache = CacheFactory.getCache(PRODUCTCACHE);
		
		Filter productFilter = new EqualsFilter(ExtractorRegister.CATEGORY_AND_PRICE_RANGE_EXTRACTOR,
					Arrays.asList(category, priceRange));
		
		Collection<ProductValue> values = productCache.values(productFilter);
		
		return values;
	}
}
