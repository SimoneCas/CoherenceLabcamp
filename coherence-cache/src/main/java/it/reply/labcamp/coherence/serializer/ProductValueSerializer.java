package it.reply.labcamp.coherence.serializer;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofSerializer;
import com.tangosol.io.pof.PofWriter;

import it.reply.labcamp.coherence.model.value.ProductValue;
/*
 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/using-portable-object-format.htm#COHDG5182
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
		ProductValue productValue = new ProductValue();
		productValue.setProductKey(in.readObject(Fields.PRODUCT_KEY.ordinal()));
		productValue.setName(in.readString(Fields.NAME.ordinal()));
		productValue.setDescription(in.readString(Fields.DESCRIPTION.ordinal()));
		productValue.setPrice(in.readDouble(Fields.PRICE.ordinal()));
		productValue.setCategory(in.readObject(Fields.CATEGORY.ordinal()));
		productValue.setPriceRange(in.readObject(Fields.PRICE_RANGE.ordinal()));
		in.readRemainder();
		return productValue;
	}

	@Override
	public void serialize(PofWriter out, ProductValue productValue) throws IOException {
		out.writeObject(Fields.PRODUCT_KEY.ordinal(), productValue.getProductKey());
		out.writeString(Fields.NAME.ordinal(), productValue.getName());
		out.writeString(Fields.DESCRIPTION.ordinal(), productValue.getDescription());
		out.writeDouble(Fields.PRICE.ordinal(), productValue.getPrice());
		out.writeObject(Fields.CATEGORY.ordinal(), productValue.getCategory());
		out.writeObject(Fields.PRICE_RANGE.ordinal(), productValue.getPriceRange());
		out.writeRemainder(null);
	}

	
	
}
