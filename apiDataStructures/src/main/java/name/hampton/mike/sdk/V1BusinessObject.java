package name.hampton.mike.sdk;

/**
 * A value class (or bean) used in generic 
 * external facing functionality. 
 * 
 * @author mhampton
 *
 */
public class V1BusinessObject {
	
	private String text;
	
	private int number;

	public String getText() {
		return text;
	}

	public void setText(String test) {
		this.text = test;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
