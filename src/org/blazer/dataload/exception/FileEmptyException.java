package org.blazer.dataload.exception;

public class FileEmptyException extends Exception {

	private static final long serialVersionUID = -6552685386752228957L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(String message, Throwable tb) {
		super(message, tb);
	}

	public FileEmptyException(Throwable tb) {
		super(tb);
	}

}
