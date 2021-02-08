package com.ssmtariq.taskmanager.exception;


/**
 * ApplicationException
 *
 * @author ssm tariq
 *
 */
public class ApplicationException extends RuntimeException {
	/** serialVersionUID */
	static final long serialVersionUID = -5788345229281494152L;
	/** ApiError */
	ApiError error = null;

	/**
	 * ApplicationException
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * ApplicationException
	 *
	 * @param mError ApiError
	 */
	public ApplicationException(ApiError mError) {
		super(mError.getMessage());
		this.error = mError;
	}

	/**
	 * get ApiError
	 *
	 * @return ApiError
	 */
	public ApiError getError() {
		return error;
	}
}
