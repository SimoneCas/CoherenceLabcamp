package it.reply.labcamp.coherence.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tangosol.io.Serializer;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import it.reply.labcamp.coherence.model.key.ProductKey;

public class ProductKeySerializerTest {

	private static final Integer ID = 15;

	protected ProductKey createProductKey(Integer id){
		return new ProductKey(id);
	}
	
	@Test
	public void testPofProductKeySerialization(){
		Serializer serializer = new ConfigurablePofContext("pof-config.xml");
		ProductKey bToBinary= createProductKey(ID);
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		ProductKey bFromBinary= (ProductKey) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
}
