package it.reply.labcamp.coherence.serializer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tangosol.io.Serializer;
import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.util.Binary;
import com.tangosol.util.ExternalizableHelper;

import it.reply.labcamp.coherence.model.key.ProductKey;
import it.reply.labcamp.coherence.model.value.OrderValue;

public class OrderValueSerializerTest {


	protected OrderValue createOrderValue(){
		OrderValue order = new OrderValue();
		order.setNote("note");
		order.setTax(20);
		order.setOrderKey(new OrderKeySerializerTest().createOrderKey(100));
		order.setTotal(0d);
		return order;
	}
	
	public OrderValue createOrderValueWithProduct() {
		OrderValue order = createOrderValue();
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKeySerializerTest().createProductKey(32));
		order.setProducts(products);
		order.setTotal(576d);
		
		return order;
	}
	
	@Test
	public void testPofCustomerValueSerialization(){
		Serializer serializer = new ConfigurablePofContext("lab-pof-config.xml");
		OrderValue bToBinary= createOrderValueWithProduct();
		
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		OrderValue bFromBinary= (OrderValue) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
	
	@Test
	public void testPofCustomerValueSerializationWithoutProducts(){
		Serializer serializer = new ConfigurablePofContext("lab-pof-config.xml");
		OrderValue bToBinary= createOrderValue();
		Binary binaryValue = ExternalizableHelper.toBinary(bToBinary, serializer);
		OrderValue bFromBinary= (OrderValue) ExternalizableHelper.fromBinary(binaryValue, serializer);
		assertEquals(bToBinary,bFromBinary);
	}
}
