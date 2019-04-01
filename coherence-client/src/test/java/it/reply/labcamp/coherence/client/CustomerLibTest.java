package it.reply.labcamp.coherence.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.GeoZone;

public class CustomerLibTest {

	private NamedCache<CustomerKey, CustomerValue> customerCache;
	
	@Before
	public void init() {
		this.customerCache = CacheFactory.getCache("CUSTOMERCACHE");
	}
	
	@After
	public void release() {
		this.customerCache.clear();
		this.customerCache.release();
	}
	
	@Test
	public void testPut(){
		CustomerLib customerLib = new CustomerLib();
		CustomerValue customer = this.createCustomerValue();
		
		customerLib.put(customer);
		
		assertTrue(this.customerCache.containsKey(customer.getCustomerKey()));
	}
	
	@Test
	public void testGetCustomerValue(){
		this.initializeCustomerCache();
		
		CustomerLib customerLib = new CustomerLib();
		CustomerValue customer = customerLib.getCustomerValue(200);
		
		assertNotNull(customer);
		assertEquals(new CustomerKey(200), customer.getCustomerKey());
	}
	
	@Test
	public void testGetAllCustomers() {
		this.initializeCustomerCache();
		
		CustomerLib customerLib = new CustomerLib();
		Collection<CustomerValue> customers = customerLib.getAllCustomers();
		
		assertEquals(2, customers.size());
	}
	
	@Test
	public void testGetCustomersByIds() {
		this.initializeCustomerCache();
		
		CustomerLib customerLib = new CustomerLib();
		List<Integer> customerIdList = new ArrayList<>();
		customerIdList.add(200);
		customerIdList.add(999);
		Collection<CustomerValue> customersByIds = customerLib.getCustomersByIds(customerIdList);
		CustomerValue expectedCustomer = createCustomerValue();
		
		assertEquals(1, customersByIds.size());
		assertEquals(expectedCustomer, customersByIds.iterator().next());
	}
	
	@Test
	public void testGetCustomersByArea() {
		this.initializeCustomerCache();
		
		CustomerLib customerLib = new CustomerLib();
		Collection<CustomerValue> customers = customerLib.getCustomersByArea(GeoZone.AFRICA);
		CustomerValue currentCustomer = customers.iterator().next();
		
		CustomerValue expectedCustomer = createCustomerValue2();
		
		assertNotNull(currentCustomer);
		assertEquals(expectedCustomer,currentCustomer);
	}
	
	@Test
	public void testAddListener() throws InterruptedException {
		this.customerCache.put(createCustomerValue().getCustomerKey(), createCustomerValue());
		
		CustomerLib customerLib = new CustomerLib();
		CustomerListener listener = new CustomerListener();
		customerLib.addListener(listener);
		
		assertEquals(0, listener.getDeleteEventsCounter());
		assertEquals(0, listener.getInsertEventsCounter());
		assertEquals(0, listener.getUpdateEventsCounter());
		
		this.customerCache.put(createCustomerValue2().getCustomerKey(), createCustomerValue2());
		
		Thread.sleep(500);
		assertEquals(0, listener.getDeleteEventsCounter());
		assertEquals(1, listener.getInsertEventsCounter());
		assertEquals(0, listener.getUpdateEventsCounter());
		
		this.customerCache.put(createCustomerValue2().getCustomerKey(), createCustomerValue2());
		
		Thread.sleep(500);
		assertEquals(0, listener.getDeleteEventsCounter());
		assertEquals(1, listener.getInsertEventsCounter());
		assertEquals(1, listener.getUpdateEventsCounter());
		
		this.customerCache.remove(createCustomerValue().getCustomerKey());
		
		Thread.sleep(500);
		assertEquals(1, listener.getDeleteEventsCounter());
		assertEquals(1, listener.getInsertEventsCounter());
		assertEquals(1, listener.getUpdateEventsCounter());
	}
	
	private void initializeCustomerCache() {
		this.customerCache.put(createCustomerValue().getCustomerKey(), createCustomerValue());
		this.customerCache.put(createCustomerValue2().getCustomerKey(), createCustomerValue2());
	}
	
	protected CustomerValue createCustomerValue(){
		CustomerValue customer = new CustomerValue();
		customer.setAge(30);
		customer.setArea(GeoZone.EUROPE);
		customer.setCustomerKey(createCustomerKey(200));
		customer.setName("mario");
		customer.setSurname("rossi");
		
		return customer;
	}
	
	protected CustomerValue createCustomerValue2(){
		CustomerValue customer = new CustomerValue();
		customer.setAge(40);
		customer.setArea(GeoZone.AFRICA);
		customer.setCustomerKey(createCustomerKey(300));
		customer.setName("kaled");
		customer.setSurname("dawoud");
		
		return customer;
	}
	
	protected CustomerKey createCustomerKey(Integer id){
		return new CustomerKey(id);
	}
}
