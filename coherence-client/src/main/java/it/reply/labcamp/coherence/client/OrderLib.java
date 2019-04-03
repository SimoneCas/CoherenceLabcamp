package it.reply.labcamp.coherence.client;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.net.cache.TypeAssertion;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.InFilter;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;
import it.reply.labcamp.coherence.model.value.OrderValue;
import it.reply.labcamp.coherence.processor.UpdateOrderProcessor;

public class OrderLib {

	private static final String ORDERCACHE = "ORDERCACHE";
	
	/*
	 * Put CustomerValue in cache (put method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public void put(OrderValue value) {
		CacheFactory.getTypedCache(ORDERCACHE,TypeAssertion.withTypes(OrderKey.class, OrderValue.class)).put(value.getOrderKey(), value);
	}

	/*
	 * Get order by order id
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public OrderValue getOrder(Integer orderId, Integer customerId) {
		NamedCache<OrderKey, OrderValue> orderCache = CacheFactory.getTypedCache(ORDERCACHE,TypeAssertion.withTypes(OrderKey.class, OrderValue.class));
		
		OrderKey key = new OrderKey(orderId, new CustomerKey(customerId));
		return orderCache.get(key);
	}
	
	/*
	 * Get all orders
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<OrderValue> getAllOrders() {
		NamedCache<OrderKey, OrderValue> orderCache = CacheFactory.getTypedCache(ORDERCACHE,TypeAssertion.withTypes(OrderKey.class, OrderValue.class));
		
		return orderCache.values();
	}
	
	/*
	 * Get all orders products (In Filter)
	 * https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm
	 */
	public Collection<OrderValue> getOrdersByTax(List<Integer> taxes) {
		NamedCache<OrderKey, OrderValue> orderCache = CacheFactory.getTypedCache(ORDERCACHE,TypeAssertion.withTypes(OrderKey.class, OrderValue.class));
		
		Set<Integer> keySet = taxes.stream().collect(Collectors.toSet());
		ValueExtractor<OrderValue, Integer> extractor = new ReflectionExtractor<>("getTax");
		return orderCache.values(new InFilter<OrderValue, Integer>(extractor, keySet));
	}
	
	/*
	 * Calculate total average on all orders (data storage processing)
	 * https://docs.oracle.com/middleware/12213/coherence/COHDG/processing-data-cache.htm#COHDG5201
	 */
	public double calculateTotalAverage(){
		NamedCache<OrderKey, OrderValue> orderCache = CacheFactory.getTypedCache(ORDERCACHE,TypeAssertion.withTypes(OrderKey.class, OrderValue.class));
		
		ValueExtractor<OrderValue, Double> totalExtractor = OrderValue::getTotal;
		double totalAverage = orderCache.stream()
				.mapToDouble(entry -> entry.extract(totalExtractor))
				.average()
				.getAsDouble();
		return totalAverage;
	}
	
	/*
	 * Update Order by Entry Processor
	 */
	public void updateOrder(OrderValue newValue) {
		NamedCache<OrderKey, OrderValue> orderCache = CacheFactory.getTypedCache(ORDERCACHE,TypeAssertion.withTypes(OrderKey.class, OrderValue.class));
		
		orderCache.invoke(newValue.getOrderKey(), new UpdateOrderProcessor(newValue));
	}
}
