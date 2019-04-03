package it.reply.labcamp.coherence.serializer;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.value.ProductValue;
/*
 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/using-portable-object-format.htm#COHDG5182
 * 21.2.2 Implementing the PofSerializer Interface
 */
public class ProductValueSerializer implements PofSerializer<ProductValue> {

	public enum Fields {
		PRODUCT_KEY,
		NAME,
		DESCRIPTION,
		PRICE,
		CATEGORY,
		PRICE_RANGE
	}

	@Override
	public ProductValue deserialize(PofReader in) throws IOException {
		return null;
	}

	@Override
	public void serialize(PofWriter out, ProductValue productValue) throws IOException {
	}

	
	
}
