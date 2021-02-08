package com.ssmtariq.taskmanager.model;

/**
 * @author SSM Tariq
 */

public enum RequestParameterEnum {
	TASK_ID("taskId"),
	DESCRIPTION("description"),
	STATUS("status"),
	PROJECT("project"),
	DUE_DATE("dueDate"),
	ASSIGNEE("assignee");

	private String value;

	RequestParameterEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
