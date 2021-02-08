package com.ssmtariq.taskmanager.exception;


/**
 * SystemException.
 *
 * @author ssm tariq
 *
 */
public class SystemException extends RuntimeException {
	/** serialVersionUID */
	static final long serialVersionUID = 7631143914838217030L;
	/** ApiError */
	ApiError error = null;

	/**
	 * SystemException
	 */
	public SystemException() {
		super();
	}

	/**
	 * SystemException
	 *
	 * @param mError ApiError
	 */
	public SystemException(ApiError mError) {
		super(mError.getMessage());
		this.error = mError;
	}

	/**
	 * SystemException
	 *
	 * @param cause Throwable
	 * @param cause ApiError
	 */
	public SystemException(ApiError apiError, Throwable cause) {
		super(cause);
		this.error = apiError;
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
