package com.ssmtariq.taskmanager.service.impl;

import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.DATE_FORMAT;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.KEY_PROJECT_ID;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmtariq.taskmanager.exception.ApiError;
import com.ssmtariq.taskmanager.exception.ApplicationException;
import com.ssmtariq.taskmanager.model.RequestParameterEnum;
import com.ssmtariq.taskmanager.model.Task;
import com.ssmtariq.taskmanager.repository.ProjectRepository;
import com.ssmtariq.taskmanager.repository.TaskRepository;

/**
 * @author SSM Tariq
 */

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserDetaillsServiceImpl userDetaillsServiceImpl;
	@Autowired
	private ProjectRepository projectRepository;

	public List<Task> listAll() {
		return taskRepository.findAll();
	}

	public List<Task> listByUser(Integer id) {
		List<Task> tasks = new ArrayList<>();
		taskRepository.findByAssigneeId(id).forEach(tasks::add);
		return tasks;
	}

	public List<Task> listByProject(Integer projectId) {
		List<Task> tasks = new ArrayList<>();
		taskRepository.findByProjectProjectId(projectId).forEach(tasks::add);
		return tasks;
	}

	public List<Task> listByDueDateExpired() {
		List<Task> tasks = new ArrayList<>();
		long millis = System.currentTimeMillis();
	    var formatter = new SimpleDateFormat(DATE_FORMAT);  
	    var strDate = formatter.format(new Date(millis));
	    Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		taskRepository.findAllWithDueDateBefore(date).forEach(tasks::add);
		return tasks;
	}

	public List<Task> listByStatus(String status) {
		List<Task> tasks = new ArrayList<>();
		taskRepository.findByStatus(status).forEach(tasks::add);
		return tasks;
	}

	public void save(Task task) {
		task.setStatus(task.getStatus().toLowerCase());
		taskRepository.saveAndFlush(task);
	}

	public Task get(Integer id) {
		return taskRepository.findByTaskId(id);
	}

	public void delete(Integer id) {
		taskRepository.deleteById(id);
	}
	
	@SuppressWarnings("unchecked")
	public Task prepareTask(String json) {
		Map<String, Object> taskMap = null;
		Date date = null;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			taskMap = (Map<String, Object>)mapper.readValue(json, Map.class);
			var simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			date = simpleDateFormat.parse(String.valueOf(taskMap.get(RequestParameterEnum.DUE_DATE.getValue())));
		} catch (IOException | ParseException e) {
			throw new ApplicationException(new ApiError(ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION));
		}
		var projectMap = (Map<String, Object>) taskMap.get(RequestParameterEnum.PROJECT.getValue());
		var project = projectRepository.findByProjectId((Integer) projectMap.get(KEY_PROJECT_ID));
		if(!Objects.nonNull(project)) {
			throw new ApplicationException(new ApiError(ERROR_INVALID_REQUEST_BODY_APPLICATION_EXCEPTION));
		}
		var task = new Task(null, String.valueOf(taskMap.get(RequestParameterEnum.DESCRIPTION.getValue())), String.valueOf(taskMap.get(RequestParameterEnum.STATUS.getValue())), project, date, userDetaillsServiceImpl.getCurrentUser());
		return task;
	}
}