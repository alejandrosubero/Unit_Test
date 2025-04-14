package com.pts.controller;
import com.pts.mapper.MapperEntityRespone;
import com.pts.mapper.RolMapper;
import com.pts.mapper.UserMapper;
import com.pts.pojo.EntityRespone;
import com.pts.pojo.UserPojo;
import com.pts.service.UserService;
import com.pts.validation.RolValidation;
import com.pts.validation.UserValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/new/user")
public class UserPublicController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidation userValidationService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

   // @Autowired
  //  RolValidation rolValidationService;

  //  @Autowired
  //  RolMapper rolMapper;


    //@PostMapping("/saveNewUser")
    @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST, consumes="application/json")
    private EntityRespone saveNewUser(@RequestBody UserPojo user, @RequestHeader("keyAdmin")  String keyAdmin){
        return userService.newUser(userMapper.pojoToEntity(userValidationService.validaNewUser(user)), keyAdmin);
    }


    @GetMapping("/usernameNew/{username}")
    private  ResponseEntity<EntityRespone> findByUserName( @PathVariable("username") String  username) {

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(
                            userService.findByUserName(
                                    (String) userValidationService.validation(username)));

            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);

        } catch (DataAccessException e) {
            EntityRespone entityRespone =
                    mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/username_contain/{username}")
    private ResponseEntity<EntityRespone> findByUserNameContain(@PathVariable("username") String  username) {

        EntityRespone entityRespone = mapperEntityRespone.setEntityT(
                                      userService.findByUserNameContaining(
                                          (String) userValidationService.validation(username)));

        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


}


