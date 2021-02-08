package com.ssmtariq.taskmanager.model;

/**
 * @author SSM Tariq
 */

public enum StatusEnum {
	OPEN("open"),
	INPROGRESS("inprogress"),
	CLOSED("closed");

	private String value;

	StatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
