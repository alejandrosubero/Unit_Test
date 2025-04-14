package com.pts.controller;


import com.pts.mapper.EmailDataConfigMapper;
import com.pts.pojo.EmailDataConfigPojo;
import com.pts.service.EmailDataConfigService;
import com.pts.validation.EmailDataConfigValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/EmailDataConfig")
public class EmailDataConfigController {

    @Autowired
    EmailDataConfigService emailDataConfigService;

    @Autowired
    private EmailDataConfigMapper mapper;

    @Autowired
    private EmailDataConfigValidation validation;


    @RequestMapping(value = "/saveNewConfigure", method = RequestMethod.POST, consumes="application/json")
    private  Boolean saveEmailDataConfigService(@RequestBody EmailDataConfigPojo config) {
        return emailDataConfigService.saveEmailDataConfigService(mapper.pojoToEntity(validation.valida(config)));
    }

    @GetMapping("/getAllConfigure")
    private List<EmailDataConfigPojo> getAll() {
        return emailDataConfigService.getAll();
    }


    @GetMapping("/configureByPort/{prot}")
    private EmailDataConfigPojo findByPort( @PathVariable("prot") String port) {
        return  emailDataConfigService.findByPort(port);
    }

    @GetMapping("/configureByUsername/{username}")
    private EmailDataConfigPojo findByMailUsername( @PathVariable("username") String username) {
        return  emailDataConfigService.findByMailUsername(username);
    }

    @GetMapping("/deleteEmailDataConfig/{username}")
    private void deleteEmailDataConfig( @PathVariable("username") String username) {
        emailDataConfigService.deleteEmailDataConfig(username);
    }

    @GetMapping("/searchEmailDataConfig/{keyboard}")
    private List<EmailDataConfigPojo> findByMailUsernameContaining( @PathVariable("keyboard") String keyboard) {
       return emailDataConfigService.findByMailUsernameContaining(keyboard);
    }


}
