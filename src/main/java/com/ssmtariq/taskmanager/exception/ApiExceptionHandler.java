package com.ssmtariq.taskmanager.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ssmtariq.taskmanager.constant.TaskManagerApiConstants;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_RESPONSE_TEMPLATE;


/**
 * Exception Handler
 *
 * @author ssm tariq
 *
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	private static final ApiError DEFAULT_ERROR = new ApiError(
			TaskManagerApiConstants.ERROR_SYSTEM_EXCEPTION);

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<String> handleApplicationException(Exception ex, WebRequest request) {
		ApiError error = ((ApplicationException) ex).getError();

		return getErrorResponse(error);
	}

	@ExceptionHandler(SystemException.class)
	public final ResponseEntity<String> handleSystemExceptions(Exception ex, WebRequest request) {
		ApiError error = ((SystemException) ex).getError();
		return getErrorResponse(error);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
		return getErrorResponse(DEFAULT_ERROR);
	}

	private ResponseEntity<String> getErrorResponse(ApiError error) {
		return ResponseEntity.status(HttpStatus.valueOf(error.getStatus()))
				.header(TaskManagerApiConstants.HEADER_CONTENT_TYPE, TaskManagerApiConstants.CONTENT_TYPE_APPLICATION_JSON_UTF_8)
				.body(MessageFormat.format(ERROR_RESPONSE_TEMPLATE, error.getStatus(), error.getMessage()));
	}

}
