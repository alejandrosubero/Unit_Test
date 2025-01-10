
/*
Create on Mon Aug 08 06:22:32 EDT 2022
*Copyright (C) 122.
@author Alejandro subero
@author JSH
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/


package com.bpm.employee.serviceImplement;

import com.bpm.employee.entitys.*;
import com.bpm.employee.mapper.EmpleadoMapper;
import com.bpm.employee.pojo.EmpleadoPojo;
import com.bpm.employee.entitys.*;
import com.bpm.employee.interfaces.RadomCode;
import com.bpm.employee.service.EmpleadoService;
import com.bpm.employee.repository.EmpleadoRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoServiceImplement implements EmpleadoService, RadomCode {

    protected static final Log logger = LogFactory.getLog(EmpleadoServiceImplement.class);
    @Autowired
    private EmpleadoRepository empleadorepository;

    @Autowired
    private EmpleadoMapper mapper;

    @Override
    public Empleado findBySocialSecurityNumber(String socialSecurityNumber) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findBySocialSecurityNumber(socialSecurityNumber);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByName(String name) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByName(name);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByLastName(String lastName) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByLastName(lastName);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByFullName(String fullName) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByFullName(fullName);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByAddress(String address) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByAddress(address);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByPhoneNumber(String phoneNumber) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByPhoneNumber(phoneNumber);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }



    @Override
    public Empleado findByPosition(Cargo cargo) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByPosition(cargo);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public EmpleadoPojo findByNumeroEmpleado(String numeroEmpleado) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByNumeroEmpleado(numeroEmpleado);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(empleadoEntity);
    }

    @Override
    public Empleado findByFechaIngreso(Date fechaIngreso) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByFechaIngreso(fechaIngreso);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByFechaEgreso(Date fechaEgreso) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByFechaEgreso(fechaEgreso);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByCausaEgreso(String causaEgreso) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByCausaEgreso(causaEgreso);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByTotalDiasTrabajados(Long totalDiasTrabajados) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByTotalDiasTrabajados(totalDiasTrabajados);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByTotalHorasMes(Long totalHorasMes) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByTotalHorasMes(totalHorasMes);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByTotalHorasYear(Long totalHorasYear) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByTotalHorasYear(totalHorasYear);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByTotalHorasExtrasYear(Long totalHorasExtrasYear) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByTotalHorasExtrasYear(totalHorasExtrasYear);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByDateLastPay(Date dateLastPay) {
        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByDateLastPay(dateLastPay);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }

    @Override
    public Empleado findByTotalHorasFeriadoYear(Long totalHorasFeriadoYear) {

        logger.info("Starting getEmpleado");
        Empleado empleadoEntity = new Empleado();
        Optional<Empleado> fileOptional1 = empleadorepository.findByTotalHorasFeriadoYear(totalHorasFeriadoYear);
        if (fileOptional1.isPresent()) {
            try {
                empleadoEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return empleadoEntity;
    }


    @Override
    public List<Empleado> getAllEmpleado() {
        logger.info("Get all employees");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        empleadorepository.findAll().forEach(empleado -> listaEmpleado.add(empleado));
        return listaEmpleado;
    }


    @Override
    public boolean saveEmpleado(Empleado employee) {
        logger.info("Save employee");
        try {
            empleadorepository.save(employee);
            logger.info("The Employee is saved...");
            return true;
        } catch (DataAccessException e) {
            logger.error("The Employee is not saved...");
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateEmpleado(Empleado employee) {
        logger.info("Update employee");
        boolean clave = false;
        if(employee != null) {
            Empleado empreBase = findById(employee.getId());
            Empleado empre = empreBase != null ? getEmployee(empreBase, employee) : employee;

            try {
                empleadorepository.save(empre);
                clave = true;
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                clave = false;
            }
        }
        return clave;
    }


    @Override
    public Empleado findById(Long id) {
        Optional <Empleado> empleadoById  = empleadorepository.findById(id);
        if(empleadoById.isPresent()){
            return empleadoById.get();
        }
        return null;
    }


    @Override
    public boolean saveListEmployees(List<Empleado> employees) {
        Boolean response = false;
        if(employees != null && employees.size()>0){
            for (Empleado employee: employees) {
               if(this.saveOrUpdateEmpleado(employee).equals("true")){
                   response = true;
               }else if(this.saveOrUpdateEmpleado(employee).equals("User name of Employee exist")) {
//                   TODO: make the correct response for users.
                   logger.info("ERROR IN THE EMPLOYEE USERNAME" + employee.getName() + "ERROR: "+ "User name of Employee exist");
                   return false;
               }
            }
        }
        return response;
    }

    @Override
    public String saveOrUpdateEmpleado(Empleado employee) {
        logger.info("save Or Update Employee");
        String clave = "false";
        Optional<Empleado> employeeBase = this.isPresentEmpledo(employee);
        if (employeeBase !=null && employeeBase.isPresent()) {
            if(this.updateEmpleado(employee)){
                clave ="true";
            }
            logger.info(" Employee is update");
        } else {
            if(this.findEmployeeByUserName(employee.getComputerSheet().getUserName()) == null){
                employee = this.setNewEmployeeParameters(employee);

                if(this.saveEmpleado(employee)){
                    clave ="true";
                }
                logger.info(" Employee is save");
            }else{
                clave = "User name of Employee exist";
            }
        }
        return clave;
    }



    // TODO: ** new employee **
    private Empleado setNewEmployeeParameters(Empleado employee){
        employee.setFullName(employee.getName() + " " + employee.getLastName());
        employee.setActivo(true);
        employee.setRelief(false);
        employee.setVacation(false);
        employee.setMedicalRest(false);
        employee.setTotalDiasTrabajados(0l);
        employee.setTotalHorasExtrasYear(0l);
        employee.setTotalHorasFeriadoYear(0l);
        employee.setTotalHorasMes(0l);
        employee.setTotalHorasYear(0l);

        employee.getPosition().setCode(employee.getPosition().getCode().toUpperCase());
        employee.getPosition().setAreaOrDivision(employee.getPosition().getAreaOrDivision().toUpperCase());
        employee.getPosition().setSubAreaOrDivision(employee.getPosition().getSubAreaOrDivision().toUpperCase());
        employee.getPosition().setName(employee.getPosition().getName().toUpperCase());

        return employee;
    }

    private Optional<Empleado> isPresentEmpledo(Empleado employee) {
        Optional<Empleado> employeeBase = null;
        if (employee.getId() == null) {
            String empleadoCode = this.generateCodeLikeNumber(6, 3);
            employee.setNumeroEmpleado(empleadoCode);
           Optional <Empleado> employeeV = empleadorepository.findByNumeroEmpleado(employee.getNumeroEmpleado());

            if (employeeV != null && employeeV.isPresent()) {
                this.isPresentEmpledo(employee);
            }
        }

        if (employee.getId() != null && employee.getNumeroEmpleado() != null) {
            employeeBase = empleadorepository.findByNumeroEmpleado(employee.getNumeroEmpleado());
        }
        return employeeBase;
    }


    @Override
    public List<Empleado> findBySocialSecurityNumberContaining(String socialsecuritynumber) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findBySocialSecurityNumberContaining(socialsecuritynumber);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByNameContaining(String name) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByNameContaining(name);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByLastNameContaining(String lastname) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByLastNameContaining(lastname);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByFullNameContaining(String fullname) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByFullNameContaining(fullname);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByAddressContaining(String address) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByAddressContaining(address);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByPhoneNumberContaining(String phonenumber) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByPhoneNumberContaining(phonenumber);
        return listaEmpleado;
    }


    @Override
    public List<Empleado> findByNumeroEmpleadoContaining(String numeroempleado) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByNumeroEmpleadoContaining(numeroempleado);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByFechaIngresoContaining(Date fechaingreso) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByFechaIngresoContaining(fechaingreso);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByFechaEgresoContaining(Date fechaegreso) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByFechaEgresoContaining(fechaegreso);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByCausaEgresoContaining(String causaegreso) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByCausaEgresoContaining(causaegreso);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByTotalDiasTrabajadosContaining(Long totaldiastrabajados) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByTotalDiasTrabajadosContaining(totaldiastrabajados);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByTotalHorasMesContaining(Long totalhorasmes) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByTotalHorasMesContaining(totalhorasmes);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByTotalHorasYearContaining(Long totalhorasyear) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByTotalHorasYearContaining(totalhorasyear);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByTotalHorasExtrasYearContaining(Long totalhorasextrasyear) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByTotalHorasExtrasYearContaining(totalhorasextrasyear);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByDateLastPayContaining(Date datelastpay) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByDateLastPayContaining(datelastpay);
        return listaEmpleado;
    }

    @Override
    public List<Empleado> findByTotalHorasFeriadoYearContaining(Long totalhorasferiadoyear) {
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        listaEmpleado = empleadorepository.findByTotalHorasFeriadoYearContaining(totalhorasferiadoyear);
        return listaEmpleado;
    }


    @Override
    public List<Empleado> findByFichaDesempenoContaining(FichaDesempeno listaDesempeno) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        for (Empleado empleado : this.getAllEmpleado()) {
            for (FichaDesempeno listaDesempenox : empleado.getlistaDesempeno()) {
                if (listaDesempenox.equalsFichaDesempeno(listaDesempeno)) {
                    listaEmpleado.add(empleado);
                }
            }
        }
        return listaEmpleado;

    }


    @Override
    public List<Empleado> findByFichaPermisosContaining(FichaPermisos permisos) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        for (Empleado empleado : this.getAllEmpleado()) {
            for (FichaPermisos permisosx : empleado.getpermisos()) {
                if (permisosx.equalsFichaPermisos(permisos)) {
                    listaEmpleado.add(empleado);
                }
            }
        }
        return listaEmpleado;

    }

    @Override
    public List<Empleado> findByRelacionFichaSueldo(FichaSueldo fichasueldo) {
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        for (Empleado empleado : this.getAllEmpleado()) {
            if (empleado.getsueldo().equalsFichaSueldo(fichasueldo)) {
                listaEmpleado.add(empleado);
            }
        }
        return listaEmpleado;
    }


    @Override
    public List<Empleado> findByFichaPagosContaining(FichaPagos pagos) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        for (Empleado empleado : this.getAllEmpleado()) {
            for (FichaPagos pagosx : empleado.getpagos()) {
                if (pagosx.equalsFichaPagos(pagos)) {
                    listaEmpleado.add(empleado);
                }
            }
        }
        return listaEmpleado;

    }


    @Override
    public List<Empleado> findByFichaFaltasContaining(FichaFaltas faltas) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("Get allProyect");
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        for (Empleado empleado : this.getAllEmpleado()) {
            for (FichaFaltas faltasx : empleado.getfaltas()) {
                if (faltasx.equalsFichaFaltas(faltas)) {
                    listaEmpleado.add(empleado);
                }
            }
        }
        return listaEmpleado;

    }

    @Override
    public List<Empleado> findByRelacionFichaMedica(FichaMedica fichamedica) {
        List<Empleado> listaEmpleado = new ArrayList<Empleado>();
        for (Empleado empleado : this.getAllEmpleado()) {
            if (empleado.getinformacionMedica().equalsFichaMedica(fichamedica)) {
                listaEmpleado.add(empleado);
            }
        }
        return listaEmpleado;
    }

    @Override
    public List<String> findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCode(String areaDivision, String subAreaDivision, String positioCode) {
        try {
            List<String> listEmpledoNumber = empleadorepository
                    .findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCodeAndActivo(
                            areaDivision,
                            subAreaDivision,
                            positioCode,
                            true
                    );
            return listEmpledoNumber;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<String> findNumeroEmpleadoByUserName(String userName) {
        try {
            return empleadorepository.findNumeroEmpleadoByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EmpleadoPojo findEmployeeByUserName(String userName) {
        List<String> listNumeroEmpleado = this.findNumeroEmpleadoByUserName(userName);
        if(listNumeroEmpleado !=null && listNumeroEmpleado.size() > 0){
           return this.findByNumeroEmpleado(listNumeroEmpleado.get(0));
        }
        return null;
    }


    public Empleado getEmployee(Empleado employeeBase, Empleado employee) {

        if (employeeBase != null && employeeBase.getId() != null) {
//        if (employee.getId()!= null)
//        this.id = employee.getId();

            if (employee.getSocialSecurityNumber() != null)
                employeeBase.setSocialSecurityNumber(employee.getSocialSecurityNumber());

            if (employee.getName() != null)
                employeeBase.setName(employee.getName());

            if (employee.getLastName() != null)
                employeeBase.setLastName(employee.getLastName());

            if (employee.getFullName() != null)
                employeeBase.setFullName(employee.getFullName());

            if (employee.getAddress() != null)
                employeeBase.setAddress(employee.getAddress());

            if (employee.getPhoneNumber() != null)
                employeeBase.setPhoneNumber(employee.getPhoneNumber());

            if (employee.getPosition() != null)
                employeeBase.setPosition(employee.getPosition());

//        if (employee.getNumeroEmpleado()!= null)
//            employeeBase.setNumeroEmpleado(employee.getNumeroEmpleado());

//        if (employee.getFechaIngreso() != null)
//           employeeBase.setFechaIngreso(employee.getFechaIngreso());

            if (employee.getFechaEgreso() != null)
                employeeBase.setFechaEgreso(employee.getFechaEgreso());


            if (employee.getCausaEgreso() != null)
                employeeBase.setCausaEgreso(employee.getCausaEgreso());

            if (employee.getTotalDiasTrabajados() != null)
                employeeBase.setTotalDiasTrabajados(employee.getTotalDiasTrabajados());

            if (employee.getTotalHorasMes() != null)
                employeeBase.setTotalHorasMes(employee.getTotalHorasMes());

            if (employee.getTotalHorasYear() != null)
                employeeBase.setTotalHorasYear(employee.getTotalHorasYear());

            if (employee.getTotalHorasExtrasYear() != null)
                employeeBase.setTotalHorasExtrasYear(employee.getTotalHorasExtrasYear());

            if (employee.getDateLastPay() != null)
                employeeBase.setDateLastPay(employee.getDateLastPay());

            if (employee.getTotalHorasFeriadoYear() != null)
                employeeBase.setTotalHorasFeriadoYear(employee.getTotalHorasFeriadoYear());

            if (employee.getlistaDesempeno() != null)
                employeeBase.setlistaDesempeno(employee.getlistaDesempeno());

            if (employee.getpermisos() != null)
                employeeBase.setpermisos(employee.getpermisos());

            if (employee.getsueldo() != null)
                employeeBase.setsueldo(employee.getsueldo());

            if (employee.getpagos() != null)
                employeeBase.setpagos(employee.getpagos());

            if (employee.getfaltas() != null)
                employeeBase.setfaltas(employee.getfaltas());

            if (employee.getinformacionMedica() != null)
                employeeBase.setinformacionMedica(employee.getinformacionMedica());

            return employeeBase;
        } else {
            return employee;
        }

    }




 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


}
