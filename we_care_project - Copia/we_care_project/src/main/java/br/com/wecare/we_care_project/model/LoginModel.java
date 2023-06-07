package br.com.wecare.we_care_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


import java.io.Serializable;


@Data
@Entity
@Table(name = "TB_WE_LOGIN")
public class LoginModel implements Serializable {

    @Email
    @Id
    private String email;

    private String password;



}



