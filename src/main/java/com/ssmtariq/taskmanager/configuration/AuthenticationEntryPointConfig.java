package com.ssmtariq.taskmanager.configuration;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.CONTENT_TYPE_APPLICATION_JSON_UTF_8;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_RESPONSE_TEMPLATE;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointConfig extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		try {
			response.addHeader("WWW-Authenticate", "Basic Realm - " + getRealmName());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(CONTENT_TYPE_APPLICATION_JSON_UTF_8);
			response.getWriter()
					.println(new JSONObject(MessageFormat.format(ERROR_RESPONSE_TEMPLATE, 401, authException.getMessage())));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("ssmtariq");
		super.afterPropertiesSet();
	}
}