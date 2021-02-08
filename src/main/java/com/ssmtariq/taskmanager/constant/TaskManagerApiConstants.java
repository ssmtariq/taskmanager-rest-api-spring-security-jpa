package com.ssmtariq.taskmanager.constant;

/**
 * @author SSM Tariq
 */
public final class TaskManagerApiConstants {

	/* message source */
	public static final String API_ERROR_SOURCE = "ApiError";

	/* API constants */
	public static final String CONTENT_TYPE_APPLICATION_JSON_UTF_8 = "application/json;charset=UTF-8";

	public static final String HEADER_CONTENT_TYPE = "Content-Type";

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	
	public static final String KEY_PROJECT_ID = "projectId";
	public static final String KEY_TASK_ID = "taskId";
	public static final String KEY_ID = "id";

	/* For String operation */
	public static final String EMPTY = "";

	/* common codes */
	public static final String SUCCESS_OPERATION = "N000";
	public static final String ERROR_WRONG_PARAM_APPLICATION_EXCEPTION = "E0001";
	public static final String ERROR_MISSING_REQUIRED_PARAM_APPLICATION_EXCEPTION = "E0002";
	public static final String ERROR_WRONG_DATE_FIELD_APPLICATION_EXCEPTION = "E0003";
	public static final String ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION = "E0004";
	public static final String ERROR_SYSTEM_EXCEPTION = "E0005";
	public static final String ERROR_INVALID_TASK = "E0006";

	public static final String MSG_INSERT_SUCCESS = "Insertion Successfull!";
	public static final String MSG_UPDATE_SUCCESS = "Update Successfull!";
	public static final String SUCCESS_RESPONSE_TEMPLATE = "'{'\"status\":\"SUCCESS\",\"body\":'{'\"taskManager\":'{'\"code\":{0},\"message\":\"{1}\"'}'}'}'";
	public static final String ERROR_RESPONSE_TEMPLATE = "'{'\"status\":\"FAILURE\",\"body\":'{'\"taskManager\":'{'\"error\":'{'\"code\":{0},\"message\":\"{1}\"'}'}'}'}'";

	/**
	 * Constructor.
	 */
	private TaskManagerApiConstants() {
	}
}