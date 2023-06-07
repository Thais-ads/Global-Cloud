package br.com.wecare.we_care_project.controller;



import br.com.wecare.we_care_project.dtos.LoginDto;
import br.com.wecare.we_care_project.model.LoginModel;
import br.com.wecare.we_care_project.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login", description = "API para gerenciamento e autorização de usuarios e login")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginModel> login(@RequestBody LoginDto loginDto) {
        var login = new LoginModel();
        BeanUtils.copyProperties(loginDto, login);
        String email = login.getEmail();
        String password = login.getPassword();
        return ResponseEntity.status(HttpStatus.CREATED).body(loginService.save(login));

    }}

