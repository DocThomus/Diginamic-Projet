package fr.durandal.durandalback.storage;

public class StorageFileNotFoundException extends StorageException {
	private static final long serialVersionUID = -6843971551505403878L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}