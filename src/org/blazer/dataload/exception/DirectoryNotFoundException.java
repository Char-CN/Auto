package org.blazer.dataload.exception;

public class DirectoryNotFoundException extends Exception {

	private static final long serialVersionUID = 8023882417137885626L;

	public DirectoryNotFoundException() {
		super();
	}

	public DirectoryNotFoundException(String message) {
		super(message);
	}

	public DirectoryNotFoundException(String message, Throwable tb) {
		super(message, tb);
	}

	public DirectoryNotFoundException(Throwable tb) {
		super(tb);
	}

}
