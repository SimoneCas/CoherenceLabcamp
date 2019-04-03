package it.reply.labcamp.coherence.serializer;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.value.CustomerValue;
/*
 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/using-portable-object-format.htm#COHDG5182
 */
public class CustomerValueSerializer implements PofSerializer<CustomerValue> {

	public enum Fields {
		CUSTOMER_KEY,
		NAME,
		SURNAME,
		AGE,
		AREA	,
		ORDER_COUNT
	}

	@Override
	public CustomerValue deserialize(PofReader in) throws IOException {
		CustomerValue customerValue = new CustomerValue();
		customerValue.setCustomerKey(in.readObject(Fields.CUSTOMER_KEY.ordinal()));
		customerValue.setName(in.readString(Fields.NAME.ordinal()));
		customerValue.setSurname(in.readString(Fields.SURNAME.ordinal()));
		customerValue.setAge(in.readObject(Fields.AGE.ordinal()));
		customerValue.setArea(in.readObject(Fields.AREA.ordinal()));
		customerValue.setOrderCounter(in.readObject(Fields.ORDER_COUNT.ordinal()));
		in.readRemainder();
		return customerValue;
	}

	@Override
	public void serialize(PofWriter out, CustomerValue customerValue) throws IOException {
		out.writeObject(Fields.CUSTOMER_KEY.ordinal(), customerValue.getCustomerKey());
		out.writeString(Fields.NAME.ordinal(), customerValue.getName());
		out.writeString(Fields.SURNAME.ordinal(), customerValue.getSurname());
		out.writeObject(Fields.AGE.ordinal(), customerValue.getAge());
		out.writeObject(Fields.AREA.ordinal(), customerValue.getArea());
		out.writeObject(Fields.ORDER_COUNT.ordinal(), customerValue.getOrderCounter());
		out.writeRemainder(null);
	}

	
	
}
