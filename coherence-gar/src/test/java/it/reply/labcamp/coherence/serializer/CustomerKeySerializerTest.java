package it.reply.labcamp.coherence.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tangosol.io.Serializer;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import it.reply.labcamp.coherence.model.key.CustomerKey;

public class CustomerKeySerializerTest {

	private static final Integer ID = 10;

	protected CustomerKey createCustomerKey(Integer id){
		return new CustomerKey(id);
	}
	
	@Test
	public void testPofCustomerKeySerialization(){
		Serializer serializer = new ConfigurablePofContext("pof-config.xml");
		CustomerKey bToBinary= createCustomerKey(ID);
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		CustomerKey bFromBinary= (CustomerKey) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
}
