package fr.durandal.durandalback.storage;

public class StorageException extends RuntimeException {
	private static final long serialVersionUID = 2852335442374569059L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
