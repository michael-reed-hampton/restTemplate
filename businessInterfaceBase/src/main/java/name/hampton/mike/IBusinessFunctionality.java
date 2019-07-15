package name.hampton.mike;


import name.hampton.mike.internal.BusinessObject;

/**
 * The interface to the functionality.
 * 
 * Template to help with development of
 * a system.
 * 
 * @author mhampton
 *
 */
public interface IBusinessFunctionality {
	
	String getSimpleItem();
	
	void setSimpleItem(String value);

	BusinessObject getComplexItem();
	
	void setComplexItem(BusinessObject value);

}
