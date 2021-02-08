package com.ssmtariq.taskmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer projectId;
	private String name;
	@ManyToOne
	private User owner;

}
