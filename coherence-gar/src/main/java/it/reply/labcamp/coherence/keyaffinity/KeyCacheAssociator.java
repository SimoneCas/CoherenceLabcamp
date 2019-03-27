package it.reply.labcamp.coherence.keyaffinity;

import com.tangosol.net.PartitionedService;
import com.tangosol.net.partition.KeyAssociator;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;
import it.reply.labcamp.coherence.model.key.ProductKey;

public class KeyCacheAssociator implements KeyAssociator{

	@Override
	public Object getAssociatedKey(Object key) {
		if (key instanceof CustomerKey) {
			return ((CustomerKey) key).getCustomerId().toString();
		} else if (key instanceof OrderKey) {
			return ((OrderKey) key).getCustomerKey().getCustomerId().toString();
		} else if (key instanceof ProductKey) {
			return ((ProductKey) key).getProductId().toString();
		}
		return key;
	}

	@Override
	public void init(PartitionedService arg0) {
		// Nothing to do here
	}
}