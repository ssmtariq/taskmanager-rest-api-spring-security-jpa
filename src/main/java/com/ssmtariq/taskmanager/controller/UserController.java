package com.ssmtariq.taskmanager.controller;

import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.CONTENT_TYPE_APPLICATION_JSON_UTF_8;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.HEADER_CONTENT_TYPE;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.MSG_INSERT_SUCCESS;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.SUCCESS_OPERATION;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.SUCCESS_RESPONSE_TEMPLATE;

import java.text.MessageFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssmtariq.taskmanager.model.User;
import com.ssmtariq.taskmanager.service.impl.UserDetaillsServiceImpl;

/**
 * @author SSM Tariq
 */

@RestController
public class UserController {

	@Autowired
	private UserDetaillsServiceImpl userDetaillsServiceImpl;

	/* Create user */
	@PostMapping("/user/insert")
	public ResponseEntity<?> add(@RequestBody User user) {
		var password = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password);
		userDetaillsServiceImpl.save(user);
		return ResponseEntity.status(HttpStatus.OK)
				.header(HEADER_CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON_UTF_8)
				.body(MessageFormat.format(SUCCESS_RESPONSE_TEMPLATE, SUCCESS_OPERATION, MSG_INSERT_SUCCESS));
	}
	
	/* Get all users */
	@GetMapping("/users")
	public List<User> list() {
		return userDetaillsServiceImpl.listAll();
	}

	/* Delete User */
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable Integer id) {
		userDetaillsServiceImpl.delete(id);
	}

	/* Update User */
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
		try {
			userDetaillsServiceImpl.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}