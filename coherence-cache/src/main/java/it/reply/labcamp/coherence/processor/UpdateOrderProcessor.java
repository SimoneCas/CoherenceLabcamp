package it.reply.labcamp.coherence.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.BackingMapContext;
import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.util.Binary;
import com.tangosol.util.BinaryEntry;
import com.tangosol.util.Converter;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;
import it.reply.labcamp.coherence.model.key.ProductKey;
import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.OrderValue;

/*
 * Implementare la classe astratta AbstractProcessor implementando la seguente logica:
 * 1) Eseguire update dell'ordine ricevuto non sovrascrivendo i prodotti ma sommando i nuovi prodotti ai precedenti gi� preenti.
 * 2) Dopo aver implementato la KeyAssociation incrementare il customer counter (che conteggia il numero di ordini di cui � l'intestatario), 
 * solo nel caso in cui sia un primo inserimento dell'ordine.
 */
@Portable
public class UpdateOrderProcessor  extends AbstractProcessor<OrderKey, OrderValue, Object>{
	private static final Logger logger = LoggerFactory.getLogger(UpdateOrderProcessor.class);
	private static final long serialVersionUID = 1L;
	
	private OrderValue newOrder;
	
	public UpdateOrderProcessor() {
		
	}
	
	public UpdateOrderProcessor(OrderValue order) {
		this.newOrder = order;
	}
	
	public OrderValue getNewOrder() {
		return newOrder;
	}
	
	@PortableProperty(0)
	public void setNewOrder(OrderValue newOrder) {
		this.newOrder = newOrder;
	}

	@Override
	public Object process(Entry<OrderKey, OrderValue> entry) {
		logger.info("Received invocation by UpdateOrderProcessor");
		if (entry.isPresent()) {
			logger.info("key {}, is present in cache", entry.getKey());
			List<ProductKey> aggregatedProducts = newOrder.getProducts();
			aggregatedProducts.addAll(entry.getValue().getProducts());
			newOrder.setProducts(aggregatedProducts);
			
			entry.setValue(newOrder);
		} else {
			logger.info("key {}, is not present in cache", entry.getKey());
			
			incrementCustomerCounter(entry);
			
			entry.setValue(newOrder);
		}
		return null;
	}

	private void incrementCustomerCounter(Entry<OrderKey, OrderValue> entry) {
		BackingMapManagerContext context = ((BinaryEntry<OrderKey, OrderValue>)entry).getContext();
		BackingMapContext customerMapContext = context.getBackingMapContext("CUSTOMERCACHE");
		logger.info("{} CustomerMapContext: {}","incrementCustomerCounter",customerMapContext);
		Converter<CustomerKey,Binary> keyConverter = (Converter<CustomerKey,Binary>)context.getKeyToInternalConverter();
		Entry customerMapEntry = customerMapContext.getBackingMapEntry(keyConverter.convert(entry.getKey().getCustomerKey()));
		CustomerValue customerValue = (CustomerValue) customerMapEntry.getValue();
		customerValue.incrementOrderCounter();
		customerMapEntry.setValue(customerValue);
		logger.debug("Related customer updated");
	}

}