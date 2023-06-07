package br.com.wecare.we_care_project.service;

import br.com.wecare.we_care_project.model.LoginModel;
import br.com.wecare.we_care_project.model.UserModel;
import br.com.wecare.we_care_project.repository.LoginRepository;
import br.com.wecare.we_care_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService extends IMyService<LoginModel> {

    @Autowired
    private LoginRepository repository;

    LoginService(JpaRepository<LoginModel, Long> repository) {
        super(repository);
    }

    public LoginModel loadUserByUsername(String email)  {
        return repository.findByEmail(email);
    }
}

