package tn.esprit.spring.exceptions;

public class ForumException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ForumException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
	public ForumException(String exMessage) {
        super(exMessage);
    }

}
