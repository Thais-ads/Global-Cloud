package br.com.wecare.we_care_project.repository;

import br.com.wecare.we_care_project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByNameContainingIgnoreCase(String name);

    UserModel findByCpf(long cpf);

    UserModel findByEmail(String email);

    UserModel findByLogin(String login);

}
