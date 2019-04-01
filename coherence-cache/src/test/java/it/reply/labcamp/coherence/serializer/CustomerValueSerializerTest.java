package it.reply.labcamp.coherence.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tangosol.io.Serializer;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.GeoZone;

public class CustomerValueSerializerTest {

	public CustomerValue createCustomerValue(){
		CustomerValue customer = new CustomerValue();
		customer.setAge(30);
		customer.setArea(GeoZone.EUROPE);
		customer.setCustomerKey(new CustomerKeySerializerTest().createCustomerKey(200));
		customer.setName("mario");
		customer.setSurname("rossi");
		
		return customer;
	}
	
	@Test
	public void testPofCustomerValueSerialization(){
		Serializer serializer = new ConfigurablePofContext("pof-config.xml");
		CustomerValue bToBinary= createCustomerValue();
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		CustomerValue bFromBinary= (CustomerValue) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
}
