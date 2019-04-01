package it.reply.labcamp.coherence.keyaffinity;

import org.junit.Assert;
import org.junit.Test;

import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.OrderValue;
import it.reply.labcamp.coherence.serializer.CustomerValueSerializerTest;
import it.reply.labcamp.coherence.serializer.OrderValueSerializerTest;

public class KeyCacheAssociatorTest {

	@Test
	public void testCustomerAssociatedKey() {
		CustomerValue customer = new CustomerValueSerializerTest().createCustomerValue();
		
		KeyCacheAssociator keyCacheAssociator = new KeyCacheAssociator();
		Object associatedCustomerKey = keyCacheAssociator.getAssociatedKey(customer.getCustomerKey());
		
		Assert.assertEquals(200, associatedCustomerKey);
	}
	
	@Test
	public void testOrderAssociatedKey() {
		OrderValue order = new OrderValueSerializerTest().createOrderValueWithProduct();
		
		KeyCacheAssociator keyCacheAssociator = new KeyCacheAssociator();
		Object associatedOrderKey = keyCacheAssociator.getAssociatedKey(order.getOrderKey());
		
		Assert.assertEquals(200, associatedOrderKey);
	}
}
