package name.hampton.mike.defaultImpl;

import name.hampton.mike.BusinessObject;
import name.hampton.mike.IBusinessFunctionality;

public class BusinessFunctionality implements IBusinessFunctionality{
	
	private String simpleItem = "simple";
	private BusinessObject complexItem = new BusinessObject(); 

	public String getSimpleItem() {
		return simpleItem;
	}

	public void setSimpleItem(String value) {
		simpleItem = value;		
	}

	public BusinessObject getComplexItem() {
		return complexItem;
	}

	public void setComplexItem(BusinessObject value) {
		complexItem = value;		
	}

}
