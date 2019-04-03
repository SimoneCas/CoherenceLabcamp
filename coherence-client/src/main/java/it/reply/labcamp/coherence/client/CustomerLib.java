package it.reply.labcamp.coherence.client;

import java.util.Collection;
import java.util.List;

import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.GeoZone;

public class CustomerLib {

	private static final String CUSTOMERCACHE = "CUSTOMERCACHE";

	/*
	 * Put CustomerValue in cache (put method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public void put(CustomerValue value) {

	}
	
	/*
	 * Get customer by customer id (get method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public CustomerValue getCustomerValue(Integer customerId) {
		return null;
		
	}
	
	/*
	 * Get all customers 
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<CustomerValue> getAllCustomers() {
		return null;
		
	}
	
	/*
	 * Get all customers by customer id list
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<CustomerValue> getCustomersByIds(List<Integer> customerId) {
		return null;
		
	}
	
	/*
	 * Get all customers by area (Equals filter)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<CustomerValue> getCustomersByArea(GeoZone area) {
		return null;
		
	}
	
	/*
	 * Add Listener on Customer cache
	 */
	public void addListener(CustomerListener listener) {
		
	}

}
