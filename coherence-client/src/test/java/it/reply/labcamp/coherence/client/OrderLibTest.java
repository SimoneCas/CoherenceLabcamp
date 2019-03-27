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

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;
import it.reply.labcamp.coherence.model.key.ProductKey;
import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.OrderValue;
import it.reply.labcamp.coherence.processor.UpdateOrderProcessor;

public class OrderLibTest {

	private NamedCache<OrderKey, OrderValue> orderCache;
	
	@Before
	public void init() {
		this.orderCache = CacheFactory.getCache("ORDERCACHE");
	}
	
	@After
	public void release() {
		this.orderCache.clear();
		this.orderCache.release();
	}
	
	@Test
	public void testPut() {
		OrderLib orderLib = new OrderLib();
		OrderValue order = this.createOrderValue();
		
		orderLib.put(order);
		
		assertTrue(this.orderCache.containsKey(order.getOrderKey()));
	}
	
	@Test
	public void testGetOrder() {
		this.initializeOrderCache();
		
		OrderLib orderLib = new OrderLib();
		OrderValue order = orderLib.getOrder(10, 200);
		
		assertNotNull(order);
		assertEquals(new OrderKey(10, new CustomerKey(200)), order.getOrderKey());
	}
	
	@Test
	public void testGetAllOrders() {
		this.initializeOrderCache();
		
		OrderLib orderLib = new OrderLib();
		Collection<OrderValue> orders = orderLib.getAllOrders();
		
		assertEquals(3, orders.size());
	}
	
	@Test
	public void testGetOrdersByTax() {
		this.initializeOrderCache();
		
		OrderLib orderLib = new OrderLib();
		List<Integer> taxes = new ArrayList<>();
		taxes.add(10);
		taxes.add(14);
		Collection<OrderValue> ordersByProducts = orderLib.getOrdersByTax(taxes);
		OrderValue expectedOrder = createOrderValue2();
		OrderValue expectedOrder2 = createOrderValue3();
		
		assertEquals(2, ordersByProducts.size());
		assertTrue(ordersByProducts.contains(expectedOrder));
		assertTrue(ordersByProducts.contains(expectedOrder2));	
	}
	
	@Test
	public void testCalculateTotalAverage() {
		this.initializeOrderCache();
		
		OrderLib orderLib = new OrderLib();
		double averageTotalCost = orderLib.calculateTotalAverage();
		assert(332.0 == averageTotalCost);
	}
	
	@Test
	public void testUpdateOrder() {
		this.initializeOrderCache();
		
		OrderLib orderLib = new OrderLib();
		OrderValue orderUpdated = createOrderValue();
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(101));
		products.add(new ProductKey(102));
		orderUpdated.setProducts(products);
		
		orderLib.updateOrder(orderUpdated);
		
		assertEquals(3, this.orderCache.get(orderUpdated.getOrderKey()).getProducts().size());
	}
	
	@Test
	public void testInsertOrderAndUpdateCustomerCounter() {
		NamedCache<CustomerKey, CustomerValue> customerCache = CacheFactory.getCache("CUSTOMERCACHE");
		CustomerValue customer = new CustomerValue();
		CustomerKey customerKey = new CustomerKey(150);
		customer.setCustomerKey(customerKey);
		customerCache.put(customer.getCustomerKey(), customer);
		
		this.initializeOrderCacheWithProcessor();
		
		OrderLib orderLib = new OrderLib();
		OrderValue newOrder = createOrderValue();
		newOrder.setOrderKey(new OrderKey(80, customerKey));
		Collection<CustomerValue> values = customerCache.values();
		Collection<OrderValue> values2 = this.orderCache.values();
		
		orderLib.updateOrder(newOrder);
		
		assertTrue(2 == customerCache.get(customerKey).getOrderCounter());
		assertTrue(this.orderCache.containsKey(newOrder.getOrderKey()));
		assertTrue(this.orderCache.containsKey(createOrderValue().getOrderKey()));
	}
	
	private void initializeOrderCacheWithProcessor() {
		this.orderCache.invoke(createOrderValue3().getOrderKey(), new UpdateOrderProcessor(createOrderValue3()));
	}

	private void initializeOrderCache() {
		this.orderCache.put(createOrderValue().getOrderKey(), createOrderValue());
		this.orderCache.put(createOrderValue2().getOrderKey(), createOrderValue2());
		this.orderCache.put(createOrderValue3().getOrderKey(), createOrderValue3());
	}

	private  OrderValue createOrderValue() {
		OrderValue order = new OrderValue();
		order.setCustomer(new CustomerKey(200));
		order.setNote("note");
		order.setTax(20);
		order.setOrderKey(new OrderKey(10, new CustomerKey(200)));
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(32));
		order.setProducts(products);
		order.setTotal(576d);
		
		return order;
	}
	
	private  OrderValue createOrderValue2() {
		OrderValue order = new OrderValue();
		order.setCustomer(new CustomerKey(200));
		order.setNote("note 2");
		order.setTax(14);
		order.setOrderKey(new OrderKey(11, new CustomerKey(200)));
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(35));
		order.setProducts(products);
		order.setTotal(200d);
		
		return order;
	}
	
	private  OrderValue createOrderValue3() {
		OrderValue order = new OrderValue();
		order.setCustomer(new CustomerKey(150));
		order.setNote("note 3");
		order.setTax(10);
		order.setOrderKey(new OrderKey(12, new CustomerKey(150)));
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(50));
		order.setProducts(products);
		order.setTotal(220d);
		
		return order;
	}
}
