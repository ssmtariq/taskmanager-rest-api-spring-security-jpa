package com.ssmtariq.taskmanager.repository;
 
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.ssmtariq.taskmanager.model.Task;

/**
 * @author SSM Tariq
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
	public Task findByTaskId(Integer taskId);
	public List<Task> findByAssigneeId(Integer id);
	public List<Task> findByProjectProjectId(Integer projectId);
	@Query("select a from Task a where a.dueDate < :dueDate")
	public List<Task> findAllWithDueDateBefore(@Param("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dueDate);
	public List<Task> findByStatus(String status);
}