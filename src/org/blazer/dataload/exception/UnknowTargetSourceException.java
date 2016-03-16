package org.blazer.dataload.exception;

public class UnknowTargetSourceException extends Exception {

	private static final long serialVersionUID = 8052208716471168248L;

	public UnknowTargetSourceException() {
		super();
	}

	public UnknowTargetSourceException(String message) {
		super(message);
	}

	public UnknowTargetSourceException(String message, Throwable tb) {
		super(message, tb);
	}

	public UnknowTargetSourceException(Throwable tb) {
		super(tb);
	}

}
