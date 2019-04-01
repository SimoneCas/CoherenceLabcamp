package it.reply.labcamp.coherence.keyaffinity;

import com.tangosol.net.PartitionedService;
import com.tangosol.net.partition.KeyAssociator;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;

/*
 * Implementare l'interfaccia KeyAssociator per configurare la KeyAssociation di Coherence,
 * al fine di posizionare nella stessa partizione l'entry del Customer e di tutti i suoi Order
 */
public class KeyCacheAssociator implements KeyAssociator{

	@Override
	public Object getAssociatedKey(Object key) {
		if (key instanceof CustomerKey) {
			return ((CustomerKey) key).getCustomerId();
		} else if (key instanceof OrderKey) {
			return ((OrderKey) key).getCustomerKey().getCustomerId();
		}
		return key;
	}

	@Override
	public void init(PartitionedService arg0) {
		// Nothing to do here
	}
}