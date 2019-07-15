package name.hampton.mike;

import java.util.Iterator;
import java.util.ServiceLoader;

import name.hampton.mike.internal.BusinessObject;
import org.junit.Assert;
import org.junit.Test;

public class IBusinessFunctionalityTest {

	@Test
	public void testGetSimpleItem() {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);
		
		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();
		
		if (!impls.hasNext()) Assert.fail("No implementations found to test!");
		while (impls.hasNext()) {
			IBusinessFunctionality instance;
			instance = impls.next();
			String actual = instance.getSimpleItem();
			
			Assert.assertEquals(String.format("Implementation is %s", instance.getClass().getName()), "simple", actual);
		}		
	}

	@Test
	public void testSetSimpleItem() {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);
		
		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();
		
		if (!impls.hasNext()) Assert.fail("No implementations found to test!");
		while (impls.hasNext()) {
			IBusinessFunctionality instance;
			instance = impls.next();
			instance.setSimpleItem("testSet");
			String actual = instance.getSimpleItem();
			
			Assert.assertEquals(String.format("Implementation is %s", instance.getClass().getName()), "testSet", actual);
		}		
	}

	@Test
	public void testGetComplexItem() {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);
		
		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();
		
		if (!impls.hasNext()) Assert.fail("No implementations found to test!");
		while (impls.hasNext()) {
			IBusinessFunctionality instance;
			instance = impls.next();
			BusinessObject actual = instance.getComplexItem();
			
			Assert.assertNotNull(String.format("Implementation is %s", instance.getClass().getName()), actual.getText());
		}		
	}

	@Test
	public void testSetComplexItem() {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);
		
		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();
		
		if (!impls.hasNext()) Assert.fail("No implementations found to test!");
		while (impls.hasNext()) {
			IBusinessFunctionality instance;
			instance = impls.next();
			BusinessObject value = new BusinessObject();
			value.setText("testSet");
			instance.setComplexItem(value);
			BusinessObject actual = instance.getComplexItem();
			
			Assert.assertEquals(String.format("Implementation is %s", instance.getClass().getName()), value.getText(), actual.getText());
		}		
	}
}
