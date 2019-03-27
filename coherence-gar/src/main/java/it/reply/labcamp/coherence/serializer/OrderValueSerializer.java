package it.reply.labcamp.coherence.serializer;

import java.io.IOException;
import java.util.ArrayList;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.value.OrderValue;

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
		OrderValue orderValue = new OrderValue();
		orderValue.setOrderKey(in.readObject(Fields.ORDER_KEY.ordinal()));
		orderValue.setTotal(in.readDouble(Fields.TOTAL.ordinal()));
		orderValue.setNote(in.readString(Fields.NOTE.ordinal()));
		orderValue.setProducts(in.readCollection(Fields.PRODUCTS.ordinal(), new ArrayList<>()));
		orderValue.setTax(in.readObject(Fields.TAX.ordinal()));
		in.readRemainder();
		return orderValue;
	}

	@Override
	public void serialize(PofWriter out, OrderValue orderValue) throws IOException {
		out.writeObject(Fields.ORDER_KEY.ordinal(), orderValue.getOrderKey());
		out.writeDouble(Fields.TOTAL.ordinal(), orderValue.getTotal());
		out.writeString(Fields.NOTE.ordinal(), orderValue.getNote());
		out.writeCollection(Fields.PRODUCTS.ordinal(), orderValue.getProducts());
		out.writeObject(Fields.TAX.ordinal(), orderValue.getTax());
		out.writeRemainder(null);
	}

	
}
