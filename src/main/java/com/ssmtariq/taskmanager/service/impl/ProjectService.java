package com.ssmtariq.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssmtariq.taskmanager.model.Project;
import com.ssmtariq.taskmanager.repository.ProjectRepository;

/**
 * @author SSM Tariq
 */

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserDetaillsServiceImpl userDetaillsServiceImpl;

	public List<Project> listAll() {
		return projectRepository.findAll();
	}

	public List<Project> listByUser(Integer id) {
		List<Project> projects = new ArrayList<>();
		projectRepository.findByOwnerId(id).forEach(projects::add);
		return projects;
	}

	public void save(Project project) {
		project.setOwner(userDetaillsServiceImpl.getCurrentUser());
		projectRepository.save(project);
	}

	public Project get(Integer id) {
		return projectRepository.findById(id).get();
	}

	public void delete(Integer id) {
		projectRepository.deleteById(id);
	}
}