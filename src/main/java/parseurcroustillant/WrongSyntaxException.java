package parseurcroustillant;

public class WrongSyntaxException extends Exception {

	private String mMsg;

	public WrongSyntaxException(String msg) {
		mMsg = msg;
	}

	public String getMessage() {
		return mMsg;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
