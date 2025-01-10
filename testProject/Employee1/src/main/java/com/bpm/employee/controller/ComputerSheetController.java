package com.bpm.employee.controller;

import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import com.bpm.employee.pojo.UserLoguin;
import com.bpm.employee.service.ComputerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/computer/sheet")
public class ComputerSheetController {

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private ComputerSheetService computerSheetService;



    @PostMapping("/Employee/Assigned/data/check")
    private ResponseEntity<EntityRespone> getEmployeeAssigned(@RequestBody UserLoguin empleado) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityRespon(
                    computerSheetService.getEmployeeAssigned(
                            empleado.getUserName(),
                            empleado.getUserPassword()),
                    "Ok");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(
                    null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("empleado/mail/{mail}/")
    private ResponseEntity<EntityRespone> findApprover(@PathVariable("mail") String mail) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityT(computerSheetService.findByMail(mail));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/empleado/data/check")
    private ResponseEntity<EntityRespone> findEmployeeByUserNameAndUserPassword(@RequestBody UserLoguin empleado) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityRespon(computerSheetService.findEmpledo(empleado.getUserName(), empleado.getUserPassword()), "Ok");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone =
                    mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

}
