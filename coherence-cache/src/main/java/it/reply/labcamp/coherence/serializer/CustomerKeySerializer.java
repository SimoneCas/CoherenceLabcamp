package it.reply.labcamp.coherence.serializer;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.key.CustomerKey;
/*
 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/using-portable-object-format.htm#COHDG5182
 */
public class CustomerKeySerializer implements PofSerializer<CustomerKey> {

	private enum Fields {
		CUSTOMER_ID
	}

	@Override
	public CustomerKey deserialize(PofReader in) throws IOException {
		CustomerKey customerKey = new CustomerKey();
		customerKey.setCustomerId(in.readObject(Fields.CUSTOMER_ID.ordinal()));
		in.readRemainder();
		return customerKey;
	}

	@Override
	public void serialize(PofWriter out, CustomerKey customerKey) throws IOException {
		out.writeObject(Fields.CUSTOMER_ID.ordinal(), customerKey.getCustomerId());
		out.writeRemainder(null);
	}
	
}
