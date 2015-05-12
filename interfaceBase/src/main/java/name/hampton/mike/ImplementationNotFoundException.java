package name.hampton.mike;

public class ImplementationNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImplementationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImplementationNotFoundException(String message) {
		super(message);
	}

	public ImplementationNotFoundException(Throwable cause) {
		super(cause);
	}
}
