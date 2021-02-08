package com.ssmtariq.taskmanager.component;

import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.DATE_FORMAT;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.EMPTY;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_MISSING_REQUIRED_PARAM_APPLICATION_EXCEPTION;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_WRONG_DATE_FIELD_APPLICATION_EXCEPTION;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_WRONG_PARAM_APPLICATION_EXCEPTION;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmtariq.taskmanager.exception.ApiError;
import com.ssmtariq.taskmanager.exception.ApplicationException;
import com.ssmtariq.taskmanager.model.RequestParameterEnum;
import com.ssmtariq.taskmanager.model.StatusEnum;

/**
 * @author SSM Tariq
 */

@Component
public class RequestValidator {

	public void validate(String requestBody) {
		if (hasInvalidParam(requestBody)) {
			throw new ApplicationException(new ApiError(ERROR_WRONG_PARAM_APPLICATION_EXCEPTION));
		}
		validateRequiredParams(requestBody);
		if (!isValidDateType(requestBody)) {
			throw new ApplicationException(new ApiError(ERROR_WRONG_DATE_FIELD_APPLICATION_EXCEPTION));
		}
	}

	@SuppressWarnings("unchecked")
	private static boolean hasInvalidParam(String requestBody) {
		try {
			var requestMap = (Map<String, Object>) parseRequestBody(requestBody, Map.class);
			var paramKeys = new ArrayList<String>((requestMap.keySet()));
			List<String> possibleParams = Stream.of(RequestParameterEnum.values())
                    .map(RequestParameterEnum::getValue)
                    .collect(Collectors.toList());
			for (String param : paramKeys) {
				if (!possibleParams.contains(param)) {
					return true;
				}
			}
			String status = (String) requestMap.get(RequestParameterEnum.STATUS.getValue());
			List<String> possibleStatus = Stream.of(StatusEnum.values())
                    .map(StatusEnum::getValue)
                    .collect(Collectors.toList());
			if (!possibleStatus.contains(status.toLowerCase())) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private static void validateRequiredParams(String jsonInString) {
		var requestMap = (Map<String, Object>) parseRequestBody(jsonInString, Map.class);
		var requiredParamMap = getRequiredParams(requestMap);
		if(requiredParamMap.containsValue(EMPTY)) {
			throw new ApplicationException(new ApiError(ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION));
		}
		if (!requiredParamMap.isEmpty()) {
			requiredParamMap.forEach((k, v) -> {
				if (v == null) {
					var apiError = new ApiError(String.format(ERROR_MISSING_REQUIRED_PARAM_APPLICATION_EXCEPTION));
					apiError.setMessage(String.format(apiError.getMessage(), k));
					throw new ApplicationException(apiError);
				}
			});
		}
	}

	@SuppressWarnings("unchecked")
	private static boolean isValidDateType(String jsonInString) {
		var requestMap = (Map<String, Object>) parseRequestBody(jsonInString, Map.class);
		try {
			String dueDate = String.valueOf(requestMap.get(RequestParameterEnum.DUE_DATE.getValue()));
			if (dueDate.isEmpty()) {
				return false;
			}
			var simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			simpleDateFormat.setLenient(false);
			simpleDateFormat.parse(dueDate);
			return true;
		} catch (ParseException | IllegalArgumentException ex) {
			return false;
		}
	}

	private static Object parseRequestBody(String jsonInString, Class<?> classname) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			Object requestObject = mapper.readValue(jsonInString, classname);
			return requestObject;
		} catch (IOException e) {
			throw new ApplicationException(new ApiError(ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION));
		}
	}

	private static Map<String, Object> getRequiredParams(Map<String, Object> paramMap) {
		var requiredParamMap = new HashMap<String, Object>();
		requiredParamMap.put(RequestParameterEnum.DESCRIPTION.getValue(), paramMap.get(RequestParameterEnum.DESCRIPTION.getValue()));
		requiredParamMap.put(RequestParameterEnum.STATUS.getValue(), paramMap.get(RequestParameterEnum.STATUS.getValue()));
		requiredParamMap.put(RequestParameterEnum.PROJECT.getValue(), paramMap.get(RequestParameterEnum.PROJECT.getValue()));
		return requiredParamMap;
	}

}