package com.ssmtariq.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssmtariq.taskmanager.service.impl.DataPreparationService;

/**
 * @author SSM Tariq
 */

@RestController
public class DataPreparationController {

	@Autowired
	private DataPreparationService dataPreparationService;

	/* Prepare data for demonstration */
	@GetMapping("/prepare/demo")
	public void getDemoData() {
		dataPreparationService.prepareData();
	}

}