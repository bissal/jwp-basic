package next.dao;

public class DataAccessException extends RuntimeException {
	private static final long serialVersionUID = 7270916189340026178L;
	
    public DataAccessException() {
        super();
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
