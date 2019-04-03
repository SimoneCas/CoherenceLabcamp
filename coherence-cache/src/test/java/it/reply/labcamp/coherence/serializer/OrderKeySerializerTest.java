package it.reply.labcamp.coherence.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tangosol.io.Serializer;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import it.reply.labcamp.coherence.model.key.OrderKey;

public class OrderKeySerializerTest {

	private static final Integer ID = 10542;

	protected OrderKey createOrderKey(Integer id){
		OrderKey orderKey = new OrderKey();
		orderKey.setOrderId(id);
		orderKey.setCustomerKey(new CustomerKeySerializerTest().createCustomerKey(id+100));
		return orderKey;
	}
	
	@Test
	public void testPofCustomerKeySerialization(){
		Serializer serializer = new ConfigurablePofContext("lab-pof-config.xml");
		OrderKey bToBinary= createOrderKey(ID);
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		OrderKey bFromBinary= (OrderKey) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
}
