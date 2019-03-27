package it.reply.labcamp.coherence.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tangosol.io.Serializer;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;

public class ProductValueSerializerTest {

	private static final Integer ID = 15;

	protected ProductValue createProductValue(){
		ProductValue product = new ProductValue();
		product.setCategory(Category.MUSIC);
		product.setDescription("Vinile Pink Floyd");
		product.setName("Pink Floyd");
		product.setPrice(28d);
		product.setPriceRange(PriceRange.CHEAP);
		product.setProductKey(new ProductKeySerializerTest().createProductKey(ID));
		return product;
	}
	
	@Test
	public void testPofProductValueSerialization(){
		Serializer serializer = new ConfigurablePofContext("pof-config.xml");
		ProductValue bToBinary= createProductValue();
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		ProductValue bFromBinary= (ProductValue) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
}
