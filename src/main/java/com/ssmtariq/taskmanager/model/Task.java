package com.ssmtariq.taskmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author SSM Tariq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskId;
	@Column(length = 255)
	private String description;
	private String status;
	@ManyToOne
	private Project project;
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@ManyToOne
	private User assignee;
}
