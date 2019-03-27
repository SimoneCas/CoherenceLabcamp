package it.reply.labcamp.coherence.serializer;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.key.OrderKey;

public class OrderKeySerializer implements PofSerializer<OrderKey> {

	private enum Fields {
		ORDER_ID,
		CUSTOMER_KEY
	}

	@Override
	public OrderKey deserialize(PofReader in) throws IOException {
		OrderKey orderKey = new OrderKey();
		orderKey.setOrderId(in.readObject(Fields.ORDER_ID.ordinal()));
		orderKey.setCustomerKey(in.readObject(Fields.CUSTOMER_KEY.ordinal()));
		in.readRemainder();
		return orderKey;
	}

	@Override
	public void serialize(PofWriter out, OrderKey orderKey) throws IOException {
		out.writeObject(Fields.ORDER_ID.ordinal(), orderKey.getOrderId());
		out.writeObject(Fields.CUSTOMER_KEY.ordinal(), orderKey.getCustomerKey());
		out.writeRemainder(null);
	}
	
}
