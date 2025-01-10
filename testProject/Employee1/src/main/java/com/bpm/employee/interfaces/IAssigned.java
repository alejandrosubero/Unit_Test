package com.bpm.employee.interfaces;

import com.bpm.employee.model.AssignedModel;
import com.bpm.employee.model.RoleModel;
import com.bpm.employee.pojo.EmpleadoPojo;

public interface IAssigned {

    default public AssignedModel generateAssigned(EmpleadoPojo empleadoPojo) {
        try {
            RoleModel role = new RoleModel(empleadoPojo.getPosition().getDescription(), empleadoPojo.getPosition().getName(), empleadoPojo.getPosition().getCode());
            AssignedModel assigned = new AssignedModel(empleadoPojo.getName(), empleadoPojo.getNumeroEmpleado(), role, empleadoPojo.getComputerSheet().getMail());
            return assigned;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
