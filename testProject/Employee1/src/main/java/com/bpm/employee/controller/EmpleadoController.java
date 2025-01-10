
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


package com.bpm.employee.controller;

import com.bpm.employee.entitys.Cargo;
import com.bpm.employee.entitys.Empleado;
import com.bpm.employee.entitys.*;
import com.bpm.employee.validation.EmpleadoValidation;
import com.bpm.employee.mapper.EmpleadoMapper;
import com.bpm.employee.service.EmpleadoService;
import com.bpm.employee.mapper.MapperEntityRespone;
import com.bpm.employee.pojo.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.bpm.employee.pojo.EmpleadoPojo;
import com.bpm.employee.validation.FichaDesempenoValidation;
import com.bpm.employee.mapper.FichaDesempenoMapper;
import com.bpm.employee.validation.FichaPermisosValidation;
import com.bpm.employee.mapper.FichaPermisosMapper;
import com.bpm.employee.validation.FichaSueldoValidation;
import com.bpm.employee.mapper.FichaSueldoMapper;
import com.bpm.employee.validation.FichaPagosValidation;
import com.bpm.employee.mapper.FichaPagosMapper;
import com.bpm.employee.validation.FichaFaltasValidation;
import com.bpm.employee.mapper.FichaFaltasMapper;
import com.bpm.employee.validation.FichaMedicaValidation;
import com.bpm.employee.mapper.FichaMedicaMapper;
import com.bpm.employee.pojo.FichaDesempenoPojo;
import com.bpm.employee.pojo.FichaPermisosPojo;
import com.bpm.employee.pojo.FichaSueldoPojo;
import com.bpm.employee.pojo.FichaPagosPojo;
import com.bpm.employee.pojo.FichaFaltasPojo;
import com.bpm.employee.pojo.FichaMedicaPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoValidation empleadoValidationService;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private FichaDesempenoValidation fichadesempenoValidationService;

    @Autowired
    private FichaDesempenoMapper fichadesempenoMapper;

    @Autowired
    private FichaPermisosValidation fichapermisosValidationService;

    @Autowired
    private FichaPermisosMapper fichapermisosMapper;

    @Autowired
    private FichaSueldoValidation fichasueldoValidationService;

    @Autowired
    private FichaSueldoMapper fichasueldoMapper;

    @Autowired
    private FichaPagosValidation fichapagosValidationService;

    @Autowired
    private FichaPagosMapper fichapagosMapper;

    @Autowired
    private FichaFaltasValidation fichafaltasValidationService;

    @Autowired
    private FichaFaltasMapper fichafaltasMapper;

    @Autowired
    private FichaMedicaValidation fichamedicaValidationService;

    @Autowired
    private FichaMedicaMapper fichamedicaMapper;

//    @Autowired
//    private EmployeeManager employeeManager;


    @GetMapping("/approve/{areaDivision}/{subAreaDivision}/{positioCode}")
    private ResponseEntity<EntityRespone> findApprover(@PathVariable("areaDivision") String areaDivision, @PathVariable("subAreaDivision") String subAreaDivision, @PathVariable("positioCode") String positioCode) {
        try {
            List<String> listNumeroEmpleado = empleadoService.findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCode(areaDivision,subAreaDivision,positioCode);
            String menssage = listNumeroEmpleado != null && listNumeroEmpleado.size()>0 ? "Ok" : "The list was not Found";
            EntityRespone entityRespone = mapperEntityRespone.setEntityRespon(listNumeroEmpleado, menssage);
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Fail error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    //http://localhost:1112/wfe/employee/code/number/{employeeNumber}
    @GetMapping("/computer/sheet/name/user/{userName}")
    private ResponseEntity<EntityRespone> findEmployee (@PathVariable("userName") String userName) {
        try {
            EmpleadoPojo empleado = empleadoService.findEmployeeByUserName(userName);
            String menssage = empleado != null ? "Ok" : "The list was not Found";
            return new ResponseEntity<EntityRespone>(mapperEntityRespone.setEntityRespon(empleado, menssage), HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Fail error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/saveOrUpdate")
    private ResponseEntity<EntityRespone> saveOrUpdateEmpleado(@RequestBody EmpleadoPojo empleado) {
        EntityRespone entityRespone = null;
        try {
                entityRespone = mapperEntityRespone.setEntityTobj(
                        empleadoService.saveOrUpdateEmpleado(empleadoMapper.pojoToEntity(
                                        empleadoValidationService.valida(empleado))));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            entityRespone = mapperEntityRespone.setEntityResponT(null, "Faill in the system (Error tray to find the employee)", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    //http://localhost:1112/wfe/employee/save/or/update/list
    @PostMapping("/save/or/update/list")
    private ResponseEntity<EntityRespone> saveOrUpdateEmpleado(@RequestBody List<EmpleadoPojo> empleados) {

        EntityRespone entityRespone = null;
        try {
            entityRespone = mapperEntityRespone.setEntityTobj(
                    empleadoService.saveListEmployees(
                            empleadoMapper.listPojoToListEntity(
                                    empleadoValidationService.validaListEmpleados(empleados)
                            )));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            entityRespone = mapperEntityRespone.setEntityResponT(null, "Faill in the system (Error tray to find the employee)", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/GetAllEmpleado")
    private ResponseEntity<EntityRespone> getAllEmpleado() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(
                empleadoService.getAllEmpleado()
        );
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/number/{numeroempleado}")
    private ResponseEntity<EntityRespone> findByNumeroEmpleado(@PathVariable("numeroempleado") String numeroempleado) {
        String busca = (String) empleadoValidationService.validation(numeroempleado);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByNumeroEmpleado(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetEmpleado/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findById(empleadoValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getsocialsecuritynumber/{socialsecuritynumber}")
    private ResponseEntity<EntityRespone> findBySocialSecurityNumber(@PathVariable("socialsecuritynumber") String socialsecuritynumber) {
        String busca = (String) empleadoValidationService.validation(socialsecuritynumber);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findBySocialSecurityNumber(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getname/{name}")
    private ResponseEntity<EntityRespone> findByName(@PathVariable("name") String name) {
        String busca = (String) empleadoValidationService.validation(name);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getlastname/{lastname}")
    private ResponseEntity<EntityRespone> findByLastName(@PathVariable("lastname") String lastname) {
        String busca = (String) empleadoValidationService.validation(lastname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByLastName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getfullname/{fullname}")
    private ResponseEntity<EntityRespone> findByFullName(@PathVariable("fullname") String fullname) {
        String busca = (String) empleadoValidationService.validation(fullname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByFullName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getaddress/{address}")
    private ResponseEntity<EntityRespone> findByAddress(@PathVariable("address") String address) {
        String busca = (String) empleadoValidationService.validation(address);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByAddress(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getphonenumber/{phonenumber}")
    private ResponseEntity<EntityRespone> findByPhoneNumber(@PathVariable("phonenumber") String phonenumber) {
        String busca = (String) empleadoValidationService.validation(phonenumber);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByPhoneNumber(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getfechaingreso/{fechaingreso}")
    private ResponseEntity<EntityRespone> findByFechaIngreso(@PathVariable("fechaingreso") Date fechaingreso) {
        Date busca = (Date) empleadoValidationService.validation(fechaingreso);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByFechaIngreso(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getfechaegreso/{fechaegreso}")
    private ResponseEntity<EntityRespone> findByFechaEgreso(@PathVariable("fechaegreso") Date fechaegreso) {
        Date busca = (Date) empleadoValidationService.validation(fechaegreso);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByFechaEgreso(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getcausaegreso/{causaegreso}")
    private ResponseEntity<EntityRespone> findByCausaEgreso(@PathVariable("causaegreso") String causaegreso) {
        String busca = (String) empleadoValidationService.validation(causaegreso);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByCausaEgreso(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettotaldiastrabajados/{totaldiastrabajados}")
    private ResponseEntity<EntityRespone> findByTotalDiasTrabajados(@PathVariable("totaldiastrabajados") Long totaldiastrabajados) {
        Long busca = (Long) empleadoValidationService.validation(totaldiastrabajados);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByTotalDiasTrabajados(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettotalhorasmes/{totalhorasmes}")
    private ResponseEntity<EntityRespone> findByTotalHorasMes(@PathVariable("totalhorasmes") Long totalhorasmes) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasmes);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByTotalHorasMes(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettotalhorasyear/{totalhorasyear}")
    private ResponseEntity<EntityRespone> findByTotalHorasYear(@PathVariable("totalhorasyear") Long totalhorasyear) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasyear);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByTotalHorasYear(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettotalhorasextrasyear/{totalhorasextrasyear}")
    private ResponseEntity<EntityRespone> findByTotalHorasExtrasYear(@PathVariable("totalhorasextrasyear") Long totalhorasextrasyear) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasextrasyear);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByTotalHorasExtrasYear(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getdatelastpay/{datelastpay}")
    private ResponseEntity<EntityRespone> findByDateLastPay(@PathVariable("datelastpay") Date datelastpay) {
        Date busca = (Date) empleadoValidationService.validation(datelastpay);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByDateLastPay(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettotalhorasferiadoyear/{totalhorasferiadoyear}")
    private ResponseEntity<EntityRespone> findByTotalHorasFeriadoYear(@PathVariable("totalhorasferiadoyear") Long totalhorasferiadoyear) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasferiadoyear);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByTotalHorasFeriadoYear(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getsocialsecuritynumbercontain/{socialsecuritynumber}")
    private ResponseEntity<EntityRespone> findBySocialSecurityNumberContain(@PathVariable("socialsecuritynumber") String socialsecuritynumber) {
        String busca = (String) empleadoValidationService.validation(socialsecuritynumber);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findBySocialSecurityNumberContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getnamecontain/{name}")
    private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String name) {
        String busca = (String) empleadoValidationService.validation(name);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getlastnamecontain/{lastname}")
    private ResponseEntity<EntityRespone> findByLastNameContain(@PathVariable("lastname") String lastname) {
        String busca = (String) empleadoValidationService.validation(lastname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByLastNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getfullnamecontain/{fullname}")
    private ResponseEntity<EntityRespone> findByFullNameContain(@PathVariable("fullname") String fullname) {
        String busca = (String) empleadoValidationService.validation(fullname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByFullNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getaddresscontain/{address}")
    private ResponseEntity<EntityRespone> findByAddressContain(@PathVariable("address") String address) {
        String busca = (String) empleadoValidationService.validation(address);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByAddressContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getphonenumbercontain/{phonenumber}")
    private ResponseEntity<EntityRespone> findByPhoneNumberContain(@PathVariable("phonenumber") String phonenumber) {
        String busca = (String) empleadoValidationService.validation(phonenumber);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByPhoneNumberContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

//    @GetMapping("/Getmailcontain/{mail}")
//    private ResponseEntity<EntityRespone> findByMailContain(@PathVariable("mail") String mail) {
//        String busca = (String) empleadoValidationService.validation(mail);
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByMailContaining(busca));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }

    @GetMapping("/Getnumeroempleadocontain/{numeroempleado}")
    private ResponseEntity<EntityRespone> findByNumeroEmpleadoContain(@PathVariable("numeroempleado") String numeroempleado) {
        String busca = (String) empleadoValidationService.validation(numeroempleado);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByNumeroEmpleadoContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getfechaingresocontain/{fechaingreso}")
    private ResponseEntity<EntityRespone> findByFechaIngresoContain(@PathVariable("fechaingreso") Date fechaingreso) {
        Date busca = (Date) empleadoValidationService.validation(fechaingreso);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByFechaIngresoContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getfechaegresocontain/{fechaegreso}")
    private ResponseEntity<EntityRespone> findByFechaEgresoContain(@PathVariable("fechaegreso") Date fechaegreso) {
        Date busca = (Date) empleadoValidationService.validation(fechaegreso);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByFechaEgresoContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getcausaegresocontain/{causaegreso}")
    private ResponseEntity<EntityRespone> findByCausaEgresoContain(@PathVariable("causaegreso") String causaegreso) {
        String busca = (String) empleadoValidationService.validation(causaegreso);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByCausaEgresoContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettotaldiastrabajadoscontain/{totaldiastrabajados}")
    private ResponseEntity<EntityRespone> findByTotalDiasTrabajadosContain(@PathVariable("totaldiastrabajados") Long totaldiastrabajados) {
        Long busca = (Long) empleadoValidationService.validation(totaldiastrabajados);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByTotalDiasTrabajadosContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettotalhorasmescontain/{totalhorasmes}")
    private ResponseEntity<EntityRespone> findByTotalHorasMesContain(@PathVariable("totalhorasmes") Long totalhorasmes) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasmes);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByTotalHorasMesContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettotalhorasyearcontain/{totalhorasyear}")
    private ResponseEntity<EntityRespone> findByTotalHorasYearContain(@PathVariable("totalhorasyear") Long totalhorasyear) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasyear);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByTotalHorasYearContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettotalhorasextrasyearcontain/{totalhorasextrasyear}")
    private ResponseEntity<EntityRespone> findByTotalHorasExtrasYearContain(@PathVariable("totalhorasextrasyear") Long totalhorasextrasyear) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasextrasyear);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByTotalHorasExtrasYearContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getdatelastpaycontain/{datelastpay}")
    private ResponseEntity<EntityRespone> findByDateLastPayContain(@PathVariable("datelastpay") Date datelastpay) {
        Date busca = (Date) empleadoValidationService.validation(datelastpay);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByDateLastPayContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettotalhorasferiadoyearcontain/{totalhorasferiadoyear}")
    private ResponseEntity<EntityRespone> findByTotalHorasFeriadoYearContain(@PathVariable("totalhorasferiadoyear") Long totalhorasferiadoyear) {
        Long busca = (Long) empleadoValidationService.validation(totalhorasferiadoyear);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByTotalHorasFeriadoYearContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

//    @PostMapping("/save")
//    private Boolean saveEmpleado(@RequestBody EmpleadoPojo empleado) {
//        return empleadoService.saveEmpleado(empleadoMapper.pojoToEntity(empleadoValidationService.valida(empleado)));
//    }

    @PostMapping("/Get/cargo")
    private ResponseEntity<EntityRespone> findByCargo(@RequestBody Cargo position) {
        Cargo findPosition = (Cargo) empleadoValidationService.validation(position);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(empleadoService.findByPosition(findPosition));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/Update")
//    private Long UpdateEmpleado(@RequestBody EmpleadoPojo empleado) {
//        empleadoService.updateEmpleado(empleadoMapper.pojoToEntity(empleadoValidationService.valida(empleado)));
//        return empleado.getId();
//    }

    @PostMapping("/Get_listaDesempeno_contain/")
    private List<Empleado> findByFichaDesempeno(@RequestBody FichaDesempenoPojo fichadesempeno) {
        return empleadoService.findByFichaDesempenoContaining(fichadesempenoMapper.pojoToEntity(fichadesempenoValidationService.valida(fichadesempeno)));
    }


    @PostMapping("/Get_permisos_contain/")
    private List<Empleado> findByFichaPermisos(@RequestBody FichaPermisosPojo fichapermisos) {
        return empleadoService.findByFichaPermisosContaining(fichapermisosMapper.pojoToEntity(fichapermisosValidationService.valida(fichapermisos)));
    }


    @PostMapping("/Get_pagos_contain/")
    private List<Empleado> findByFichaPagos(@RequestBody FichaPagosPojo fichapagos) {
        return empleadoService.findByFichaPagosContaining(fichapagosMapper.pojoToEntity(fichapagosValidationService.valida(fichapagos)));
    }


    @PostMapping("/Get_faltas_contain/")
    private List<Empleado> findByFichaFaltas(@RequestBody FichaFaltasPojo fichafaltas) {
        return empleadoService.findByFichaFaltasContaining(fichafaltasMapper.pojoToEntity(fichafaltasValidationService.valida(fichafaltas)));
    }


    @PostMapping("/findsueldo")
    private ResponseEntity<EntityRespone> findRelacionFichaSueldo(@RequestBody FichaSueldoPojo fichasueldo) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByRelacionFichaSueldo(fichasueldoMapper.pojoToEntity(fichasueldoValidationService.valida(fichasueldo))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @PostMapping("/findinformacionMedica")
    private ResponseEntity<EntityRespone> findRelacionFichaMedica(@RequestBody FichaMedicaPojo fichamedica) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(empleadoService.findByRelacionFichaMedica(fichamedicaMapper.pojoToEntity(fichamedicaValidationService.valida(fichamedica))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
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


