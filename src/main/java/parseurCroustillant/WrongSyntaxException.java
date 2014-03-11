package parseurCroustillant;

public class WrongSyntaxException extends Exception {

	private String _msg;

	public WrongSyntaxException(String msg) {
		_msg = msg;
	}

	public String getMessage() {
		return _msg;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
