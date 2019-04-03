package it.reply.labcamp.coherence.serializer;

import java.io.IOException;
import java.util.ArrayList;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.value.OrderValue;
/*
 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/using-portable-object-format.htm#COHDG5182
 * 21.2.2 Implementing the PofSerializer Interface
 */
public class OrderValueSerializer implements PofSerializer<OrderValue> {

	public enum Fields {
		ORDER_KEY,
		TOTAL,
		NOTE,
		PRODUCTS,
		TAX
	}

	@Override
	public OrderValue deserialize(PofReader in) throws IOException {
		return null;
	}

	@Override
	public void serialize(PofWriter out, OrderValue orderValue) throws IOException {
	}

	
}
