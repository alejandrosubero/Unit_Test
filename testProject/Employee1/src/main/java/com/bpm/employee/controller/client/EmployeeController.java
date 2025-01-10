package com.bpm.employee.controller.client;

import com.bpm.employee.componet.EmployeeManager;
import com.bpm.employee.mapper.EmpleadoMapper;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.model.AssignedModel;
import com.bpm.employee.pojo.EmpleadoPojo;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.HierarchicalTreePojo;
import com.bpm.employee.validation.EmpleadoValidation;
import com.google.gson.Gson;

import antlr.StringUtils;

import com.bpm.employee.service.EmpleadoService;
import com.bpm.employee.service.HierarchicalTreeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee/connect/")
public class EmployeeController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private EmpleadoValidation empleadoValidationService;

	@Autowired
	private EmpleadoMapper empleadoMapper;

	@Autowired
	private MapperEntityRespone mapperEntityRespone;

	@Autowired
	private EmployeeManager employeeManager;
	
	
	

	@GetMapping("/user/employee/number/{employeeNumber}")
	private ResponseEntity<EntityRespone> findByNumeroEmpleado(@PathVariable("employeeNumber") String employeeNumber) {

		if (employeeNumber == null || employeeNumber.isEmpty()) {
			return ResponseEntity.badRequest()
					.body(mapperEntityRespone.setEntityResponBadRequest("Employee number cannot be empty."));
		}

		EntityRespone entityRespone = mapperEntityRespone
				.setEntityTobj(empleadoService.findByNumeroEmpleado(employeeNumber));

		return ResponseEntity.ok(entityRespone);

	}

	// http://localhost:1112/wfe/employee/user/name/{userName}
	@GetMapping("user/name/{userName}")
	private ResponseEntity<EntityRespone> findEmployeeByUserName(@PathVariable("userName") String userName) {
		try {
			AssignedModel assigned = employeeManager.findEmployeeByUserName(userName);
			String menssage = assigned != null ? "Ok" : "The list was not Found";
			return ResponseEntity.ok(mapperEntityRespone.setEntityRespon(assigned, menssage));
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Fail error", e.getMessage());
			return ResponseEntity.badRequest().body(entityRespone);
		}
	}

	// http://localhost:1112/wfe/employee/approve/{employeeNumber}/{positionCode}
	@GetMapping("/approve/{employeeNumber}/{positionCode}")
	private ResponseEntity<EntityRespone> findApprover(@PathVariable("employeeNumber") String employeeNumber, @PathVariable("positionCode") String positionCode) {

		if (employeeNumber == null || employeeNumber.isEmpty() || positionCode == null || positionCode.isEmpty()) {
			return ResponseEntity.badRequest().body(
					mapperEntityRespone.setEntityResponBadRequest("Employee number or positionCode cannot be empty."));
		}

		try {
			AssignedModel assigned = employeeManager.findApprover(employeeNumber, positionCode);
			String menssage = assigned != null ? "Ok" : "The Approver was not Found";
			EntityRespone entityRespone = mapperEntityRespone.setEntityRespon(assigned, menssage);
			return ResponseEntity.ok(entityRespone);

		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",
					e.getMessage());
			return ResponseEntity.badRequest().body(entityRespone);
		}
	}

	// http://localhost:1112/wfe/employee/approve/{employeeNumber}
	@GetMapping("/approve/{employeeNumber}")
	private ResponseEntity<EntityRespone> findApprover(@PathVariable("employeeNumber") String employeeNumber) {

		if (employeeNumber == null || employeeNumber.isEmpty()) {
			return ResponseEntity.badRequest()
					.body(mapperEntityRespone.setEntityResponBadRequest("Employee number cannot be empty."));
		}

		try {
			AssignedModel assigned = employeeManager.findApprover(employeeNumber);
			String menssage = assigned != null ? "Ok" : "The Approver was not Found";
			EntityRespone entityRespone = mapperEntityRespone.setEntityRespon(assigned, menssage);
			return ResponseEntity.ok(entityRespone);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null,
					"Faill in the system (Error tray to find the employee Approver)", e.getMessage());
			return ResponseEntity.badRequest().body(entityRespone);
		}
	}

	// http://localhost:1112/wfe/employee/code/number/{employeeNumber}
	@GetMapping("/code/number/{employeeNumber}")
	private ResponseEntity<EntityRespone> findByEmployeeNumber(@PathVariable("employeeNumber") String numeroempleado) {

		if (numeroempleado == null || numeroempleado.isEmpty()) {
			return ResponseEntity.badRequest()
					.body(mapperEntityRespone.setEntityResponBadRequest("Employee number cannot be empty."));
		}

		try {
			AssignedModel assigned = employeeManager.findEmployee(numeroempleado);

			if (assigned == null) {
				return ResponseEntity.badRequest().body(mapperEntityRespone
						.setEntityResponBadRequest("Employee cannot be found. (Error in Employee number)"));
			}
			return ResponseEntity.ok(mapperEntityRespone.setEntityTobj(assigned));

		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null,
					"Faill in the system (Error tray to find the employee)", e.getMessage());
			return ResponseEntity.badRequest().body(entityRespone);
		}
	}

	// http://localhost:1112/wfe/employee/connect/save/or/update/list
	@PostMapping("/save/or/update/list")
	private ResponseEntity<EntityRespone> saveOrUpdateEmpleado(@RequestBody List<EmpleadoPojo> empleados) {

		EntityRespone entityRespone = null;
		try {

			if (empleados == null || empleados.isEmpty()) {
				return ResponseEntity.badRequest().body(
						mapperEntityRespone.setEntityResponBadRequest("the list Employee cannot be empty or null."));
			}

			entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.saveListEmployees(
					empleadoMapper.listPojoToListEntity(empleadoValidationService.validaListEmpleados(empleados))));

			return ResponseEntity.ok(entityRespone);

		} catch (DataAccessException e) {
			entityRespone = mapperEntityRespone.setEntityResponT(null,
					"Faill in the system (Error tray to find the employee)", e.getMessage());
			return ResponseEntity.badRequest().body(entityRespone);
		}

	}

	@GetMapping("/start")
	public String startTest() {
		return " <h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>" + "<br>" +

				"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>";
	}
}
