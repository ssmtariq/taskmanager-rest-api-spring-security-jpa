package com.ssmtariq.taskmanager.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssmtariq.taskmanager.model.Project;

/**
 * @author SSM Tariq
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	public List<Project> findByOwnerId(Integer id);
	Project findByProjectId(Integer projectId);
}