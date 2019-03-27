package it.reply.labcamp.coherence.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import it.reply.labcamp.coherence.model.key.ProductKey;
import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;

public class ProductLibTest {

	private NamedCache<ProductKey, ProductValue> productCache;
	
	@Before
	public void init() {
		this.productCache = CacheFactory.getCache("PRODUCTCACHE");
	}
	
	@After
	public void release() {
		this.productCache.clear();
		this.productCache.release();
	}
	
	@Test
	public void testPut() {
		ProductLib productLib = new ProductLib();
		ProductValue product = this.createProductValue();
		
		productLib.put(product);
		
		assertTrue(this.productCache.containsKey(product.getProductKey()));
	}
	
	@Test
	public void testGetProductValue() {
		this.initializeCustomerCache();
		
		ProductLib productLib = new ProductLib();
		ProductValue product = productLib.getProductValue(10);
		
		assertNotNull(product);
		assertEquals(new ProductKey(10), product.getProductKey());
	}
	
	@Test
	public void testGetAllProducts() {
		this.initializeCustomerCache();
		
		ProductLib productLib = new ProductLib();
		Collection<ProductValue> products = productLib.getAllProducts();
		
		assertEquals(3, products.size());
	}
	
	@Test
	public void testGetProductsByIds() {
		this.initializeCustomerCache();
		
		ProductLib productLib = new ProductLib();
		List<Integer> productIdList = new ArrayList<>();
		productIdList.add(10);
		productIdList.add(90);
		Collection<ProductValue> productByIds = productLib.getProductsByIds(productIdList);
		ProductValue expectedProduct = createProductValue();
		ProductValue expectedProduct2 = createProductValue3();
		
		assertEquals(2, productByIds.size());
		assertTrue(productByIds.contains(expectedProduct));
		assertTrue(productByIds.contains(expectedProduct2));
	}
	
	@Test
	public void testGetProductsByPrice() {
		this.initializeCustomerCache();
		
		ProductLib productLib = new ProductLib();
		Collection<ProductValue> products = productLib.getProductsWithPriceGreaterThan(40d);
		ProductValue currentProduct= products.iterator().next();
		
		ProductValue expectedProduct = createProductValue2();
		
		assertNotNull(currentProduct);
		assertEquals(expectedProduct, currentProduct);
	}
	
	@Test
	public void testGetProductsByCategoryAndPriceRange() {
		this.initializeCustomerCache();
		
		ProductLib productLib = new ProductLib();
		Collection<ProductValue> currentProducts = productLib.getProductsByCategoryAndPriceRange(Category.SPORT, PriceRange.LOW_COST);
		
		ProductValue expectedValue = createProductValue3();
		
		assertNotNull(currentProducts);
		assertEquals(expectedValue, currentProducts.iterator().next());
		
	}
	
	private void initializeCustomerCache() {
		this.productCache.put(createProductValue().getProductKey(), createProductValue());
		this.productCache.put(createProductValue2().getProductKey(), createProductValue2());
		this.productCache.put(createProductValue3().getProductKey(), createProductValue3());
	}

	private ProductValue createProductValue(){
		ProductValue product = new ProductValue();
		product.setCategory(Category.MUSIC);
		product.setDescription("Vinile Pink Floyd");
		product.setName("Pink Floyd");
		product.setPrice(28d);
		product.setPriceRange(PriceRange.CHEAP);
		product.setProductKey(createProductKey(10));
		return product;
	}
	
	private ProductValue createProductValue2(){
		ProductValue product = new ProductValue();
		product.setCategory(Category.SPORT);
		product.setDescription("T-shirt Nike");
		product.setName("T-shirt");
		product.setPrice(60d);
		product.setPriceRange(PriceRange.EXPENSIVE);
		product.setProductKey(createProductKey(75));
		return product;
	}
	
	private ProductValue createProductValue3(){
		ProductValue product = new ProductValue();
		product.setCategory(Category.SPORT);
		product.setDescription("Ball Adidas");
		product.setName("Ball");
		product.setPrice(10d);
		product.setPriceRange(PriceRange.LOW_COST);
		product.setProductKey(createProductKey(90));
		return product;
	}
	
	private ProductKey createProductKey(Integer id){
		return new ProductKey(id);
	}
}
