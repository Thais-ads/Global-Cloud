package br.com.wecare.we_care_project.repository;

import br.com.wecare.we_care_project.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long> {

   LoginModel findByEmail(String email);
}
