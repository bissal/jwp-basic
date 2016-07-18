package next.dao;

public class InvalidResultException extends RuntimeException {
	private static final long serialVersionUID = -6183670064753973395L;

	public InvalidResultException() {
        super();
    }

    public InvalidResultException(String message) {
        super(message);
    }

    public InvalidResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResultException(Throwable cause) {
        super(cause);
    }
}
