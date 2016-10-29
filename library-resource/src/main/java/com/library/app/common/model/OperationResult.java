package com.library.app.common.model;

public class OperationResult {
	private boolean success;
	private String errorIdentification;
	private String errorDescription;
	private Object entity;

	private OperationResult(final Object entity) {
		this.success = true;
		this.entity = entity;
	}

	private OperationResult(final String errorIdentification, final String errorDescription) {
		this.success = false;
		this.errorIdentification = errorIdentification;
		this.errorDescription = errorDescription;
	}

	public static OperationResult success(final Object entity) {
		return new OperationResult(entity);
	}

	// static method factory
	public static OperationResult success() {
		return new OperationResult(null);
	}

	public static OperationResult error(final String errorIdentification, final String errorDescription) {
		return new OperationResult(errorIdentification, errorDescription);
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getEntity() {
		return entity;
	}

	public String getErrorIdentifaction() {
		return errorIdentification;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	@Override
	public String toString() {
		return "OperationResult [success=" + success + ", errorIdentification=" + errorIdentification
				+ ", errorDescription=" + errorDescription + ", entity=" + entity + "]";
	}

}
