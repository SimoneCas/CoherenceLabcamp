package it.reply.labcamp.coherence.serializer;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.key.ProductKey;

public class ProductKeySerializer implements PofSerializer<ProductKey> {

	private enum Fields {
		PRODUCT_ID
	}

	@Override
	public ProductKey deserialize(PofReader in) throws IOException {
		ProductKey productKey = new ProductKey();
		productKey.setProductId(in.readObject(Fields.PRODUCT_ID.ordinal()));
		in.readRemainder();
		return productKey;
	}

	@Override
	public void serialize(PofWriter out, ProductKey productKey) throws IOException {
		out.writeObject(Fields.PRODUCT_ID.ordinal(), productKey.getProductId());
		out.writeRemainder(null);
	}
	
}
