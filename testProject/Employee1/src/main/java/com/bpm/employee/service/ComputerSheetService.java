package com.bpm.employee.service;

import com.bpm.employee.model.AssignedModel;
import com.bpm.employee.pojo.ComputerSheetPojo;
import com.bpm.employee.pojo.EmpleadoPojo;

import java.util.List;

public interface ComputerSheetService {

    public ComputerSheetPojo findByUserNameAndUserPassword(String userName, String userPassword);
    public List<ComputerSheetPojo> findByMail(String mail);

    public EmpleadoPojo findEmpledo(String userName, String userPassword);

    public AssignedModel getEmployeeAssigned(String userName, String userPassword);

}
