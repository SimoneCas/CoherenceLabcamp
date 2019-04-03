package it.reply.labcamp.coherence.client;

import java.util.Collection;
import java.util.List;

import it.reply.labcamp.coherence.model.value.OrderValue;

public class OrderLib {

	private static final String ORDERCACHE = "ORDERCACHE";
	
	/*
	 * Put CustomerValue in cache (put method)
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public void put(OrderValue value) {
	}

	/*
	 * Get order by order id
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/performing-basic-cache-operations.htm#COHDG5975
	 */
	public OrderValue getOrder(Integer orderId, Integer customerId) {
		return null;
	}
	
	/*
	 * Get all orders
	 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/querying-data-cache.htm#COHDG136
	 */
	public Collection<OrderValue> getAllOrders() {
		return null;
	}
	
	/*
	 * Get all orders products (In Filter)
	 * https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm
	 */
	public Collection<OrderValue> getOrdersByTax(List<Integer> taxes) {
		return null;
	}
	
	/*
	 * Calculate total average on all orders (data storage processing)
	 * https://docs.oracle.com/middleware/12213/coherence/COHDG/processing-data-cache.htm#COHDG5201
	 */
	public double calculateTotalAverage(){
		return 0;
	}
	
	/*
	 * Update Order by Entry Processor
	 */
	public void updateOrder(OrderValue newValue) {
	}
}
