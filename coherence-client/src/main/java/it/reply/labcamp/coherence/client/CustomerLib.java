package it.reply.labcamp.coherence.client;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.GeoZone;

public class CustomerLib {

	private static final String CUSTOMERCACHE = "CUSTOMERCACHE";

	/*
	 * Put CustomerValue in cache (put method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public void put(CustomerValue value) {
		CacheFactory.getCache(CUSTOMERCACHE).put(value.getCustomerKey(), value);
	}
	
	/*
	 * Get customer by customer id (get method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public CustomerValue getCustomerValue(Integer customerId) {
		NamedCache<CustomerKey, CustomerValue> customerCache = CacheFactory.getCache(CUSTOMERCACHE);
		
		CustomerKey key = new CustomerKey(customerId);
		return customerCache.get(key);
	}
	
	/*
	 * Get all customers 
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<CustomerValue> getAllCustomers() {
		NamedCache<CustomerKey, CustomerValue> customerCache = CacheFactory.getCache(CUSTOMERCACHE);
		
		return customerCache.values();
	}
	
	/*
	 * Get all customers by customer id list
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<CustomerValue> getCustomersByIds(List<Integer> customerId) {
		NamedCache<CustomerKey, CustomerValue> customerCache = CacheFactory.getCache(CUSTOMERCACHE);
		
		List<CustomerKey> customerKeys = customerId.stream().map(id -> new CustomerKey(id)).collect(Collectors.toList());
		return customerCache.getAll(customerKeys).values();
	}
	
	/*
	 * Get all customers by area (Equals filter)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<CustomerValue> getCustomersByArea(GeoZone area) {
		NamedCache<CustomerKey, CustomerValue> customerCache = CacheFactory.getCache(CUSTOMERCACHE);
		
		ValueExtractor<CustomerValue, GeoZone> extractor = new ReflectionExtractor<>("getArea");
		return customerCache.values(new EqualsFilter<CustomerValue, GeoZone>(extractor, area));
	}
	
	/*
	 * Add Listener on Customer cache
	 */
	public void addListener(CustomerListener listener) {
		NamedCache<CustomerKey, CustomerValue> customerCache = CacheFactory.getCache(CUSTOMERCACHE);
		customerCache.addMapListener(listener);
	}

}
