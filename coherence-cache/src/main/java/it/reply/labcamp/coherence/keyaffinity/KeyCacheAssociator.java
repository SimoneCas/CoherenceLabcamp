package it.reply.labcamp.coherence.keyaffinity;

import com.tangosol.net.PartitionedService;
import com.tangosol.net.partition.KeyAssociator;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;

/*
 * Implementare l'interfaccia KeyAssociator per configurare la KeyAssociation di Coherence,
 * al fine di posizionare nella stessa partizione l'entry del Customer e di tutti i suoi Order
 * https://docs.oracle.com/middleware/12213/coherence/COHDG/working-partitions.htm#COHDG131
 */
public class KeyCacheAssociator implements KeyAssociator{

	@Override
	public Object getAssociatedKey(Object key) {
		return null;
	}

	@Override
	public void init(PartitionedService arg0) {
		// Nothing to do here
	}
}