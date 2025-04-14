
/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@author Subero Alejandro
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/



package com.pts.controller;
import com.pts.entitys.User;
import com.pts.validation.UserValidation;
import com.pts.mapper.UserMapper;
import com.pts.service.UserService;
import com.pts.mapper.MapperEntityRespone;
import com.pts.pojo.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.pts.pojo.UserPojo;
import com.pts.pojo.UserTeamPojo;
import com.pts.entitys.Rol;
import com.pts.validation.RolValidation;
import com.pts.mapper.RolMapper;
import com.pts.pojo.RolPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidationService;
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private RolValidation rolValidationService;

    @Autowired
    private RolMapper rolMapper;


    @PostMapping("/addUserToTeam")
	private Boolean addUserToTeam(@RequestBody UserTeamPojo teamUser) {
		return userService.addUserToTeam(teamUser.getUserCode(), teamUser.getIdTeamGroup(), teamUser.getTeam(), teamUser.getDescription());
	}
    

	@GetMapping("/GetAllUser")
	private ResponseEntity<EntityRespone> getAllUser() {
    //  EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.getAllUser());
		EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.allUsers());
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
	}

	@PostMapping("/save")
	private Boolean saveUser(@RequestBody UserPojo user) {
		return userService.saveUser(userMapper.pojoToEntity(userValidationService.valida(user)));
	}
    
        @GetMapping("/Getusercode/{usercode}")
        private  ResponseEntity<EntityRespone> findByUserCode(@PathVariable("usercode") String  usercode) {
        String busca = (String) userValidationService.validation(usercode);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserCode(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getuserfirsname/{userfirsname}")
        private  ResponseEntity<EntityRespone> findByUserFirsName(@PathVariable("userfirsname") String  userfirsname) {
        String busca = (String) userValidationService.validation(userfirsname);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserFirsName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getuserlastname/{userlastname}")
        private  ResponseEntity<EntityRespone> findByUserLastName(@PathVariable("userlastname") String  userlastname) {
        String busca = (String) userValidationService.validation(userlastname);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserLastName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getfullname/{fullname}")
        private  ResponseEntity<EntityRespone> findByFullName(@PathVariable("fullname") String  fullname) {
        String busca = (String) userValidationService.validation(fullname);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByFullName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getusername/{username}")
        private  ResponseEntity<EntityRespone> findByUserName(@PathVariable("username") String  username) {
        String busca = (String) userValidationService.validation(username);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getmail/{mail}")
        private  ResponseEntity<EntityRespone> findByMail(@PathVariable("mail") String  mail) {
        String busca = (String) userValidationService.validation(mail);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByMail(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getpassword/{password}")
        private  ResponseEntity<EntityRespone> findByPassword(@PathVariable("password") String  password) {
        String busca = (String) userValidationService.validation(password);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByPassword(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getimagen/{imagen}")
        private  ResponseEntity<EntityRespone> findByImagen(@PathVariable("imagen") String  imagen) {
        String busca = (String) userValidationService.validation(imagen);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByImagen(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getaccountnonexpired/{accountnonexpired}")
        private  ResponseEntity<EntityRespone> findByAccountNonExpired(@PathVariable("accountnonexpired") Boolean  accountnonexpired) {
        Boolean busca = (Boolean) userValidationService.validation(accountnonexpired);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByAccountNonExpired(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getaccountnonlocked/{accountnonlocked}")
        private  ResponseEntity<EntityRespone> findByAccountNonLocked(@PathVariable("accountnonlocked") Boolean  accountnonlocked) {
        Boolean busca = (Boolean) userValidationService.validation(accountnonlocked);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByAccountNonLocked(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getcredentialsnonexpired/{credentialsnonexpired}")
        private  ResponseEntity<EntityRespone> findByCredentialsNonExpired(@PathVariable("credentialsnonexpired") Boolean  credentialsnonexpired) {
        Boolean busca = (Boolean) userValidationService.validation(credentialsnonexpired);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByCredentialsNonExpired(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getenabled/{enabled}")
        private  ResponseEntity<EntityRespone> findByEnabled(@PathVariable("enabled") Boolean  enabled) {
        Boolean busca = (Boolean) userValidationService.validation(enabled);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByEnabled(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getusercodecontain/{usercode}")
        private ResponseEntity<EntityRespone> findByUserCodeContain(@PathVariable("usercode") String  usercode) {
              String busca = (String) userValidationService.validation(usercode);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserCodeContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getuserfirsnamecontain/{userfirsname}")
        private ResponseEntity<EntityRespone> findByUserFirsNameContain(@PathVariable("userfirsname") String  userfirsname) {
              String busca = (String) userValidationService.validation(userfirsname);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserFirsNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getuserlastnamecontain/{userlastname}")
        private ResponseEntity<EntityRespone> findByUserLastNameContain(@PathVariable("userlastname") String  userlastname) {
              String busca = (String) userValidationService.validation(userlastname);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserLastNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getfullnamecontain/{fullname}")
        private ResponseEntity<EntityRespone> findByFullNameContain(@PathVariable("fullname") String  fullname) {
              String busca = (String) userValidationService.validation(fullname);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByFullNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getusernamecontain/{username}")
        private ResponseEntity<EntityRespone> findByUserNameContain(@PathVariable("username") String  username) {
              String busca = (String) userValidationService.validation(username);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getmailcontain/{mail}")
        private ResponseEntity<EntityRespone> findByMailContain(@PathVariable("mail") String  mail) {
              String busca = (String) userValidationService.validation(mail);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByMailContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getpasswordcontain/{password}")
        private ResponseEntity<EntityRespone> findByPasswordContain(@PathVariable("password") String  password) {
              String busca = (String) userValidationService.validation(password);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByPasswordContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getimagencontain/{imagen}")
        private ResponseEntity<EntityRespone> findByImagenContain(@PathVariable("imagen") String  imagen) {
              String busca = (String) userValidationService.validation(imagen);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByImagenContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getaccountnonexpiredcontain/{accountnonexpired}")
        private ResponseEntity<EntityRespone> findByAccountNonExpiredContain(@PathVariable("accountnonexpired") Boolean  accountnonexpired) {
              Boolean busca = (Boolean) userValidationService.validation(accountnonexpired);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByAccountNonExpiredContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getaccountnonlockedcontain/{accountnonlocked}")
        private ResponseEntity<EntityRespone> findByAccountNonLockedContain(@PathVariable("accountnonlocked") Boolean  accountnonlocked) {
              Boolean busca = (Boolean) userValidationService.validation(accountnonlocked);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByAccountNonLockedContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getcredentialsnonexpiredcontain/{credentialsnonexpired}")
        private ResponseEntity<EntityRespone> findByCredentialsNonExpiredContain(@PathVariable("credentialsnonexpired") Boolean  credentialsnonexpired) {
              Boolean busca = (Boolean) userValidationService.validation(credentialsnonexpired);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByCredentialsNonExpiredContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getenabledcontain/{enabled}")
        private ResponseEntity<EntityRespone> findByEnabledContain(@PathVariable("enabled") Boolean  enabled) {
              Boolean busca = (Boolean) userValidationService.validation(enabled);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByEnabledContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetUser/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findById(userValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }



        @DeleteMapping("/deleteUser/{id}")
            private boolean deleteUser(@PathVariable("id") String id) {
            return userService.deleteUser(userValidationService.valida_id(id)); }


        @PostMapping("/Get_rol_contain/")
        private List<User> findByRol(@RequestBody RolPojo  rol){ 
            return userService.findByRolContaining(rolMapper.pojoToEntity(rolValidationService.valida(rol))); }

}


// public Boolean addUserToTeam(String userCode, String team, String description) 

//@GetMapping("/greeting")
//public ResponseEntity<String> greeting(@RequestHeader("accept-language") String language) {
//    // code that uses the language variable
//    return new ResponseEntity<String>(greeting, HttpStatus.OK);
//}




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


