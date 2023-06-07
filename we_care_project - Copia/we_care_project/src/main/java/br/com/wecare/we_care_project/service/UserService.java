package br.com.wecare.we_care_project.service;

import br.com.wecare.we_care_project.dtos.UserDto;
import br.com.wecare.we_care_project.model.UserModel;
import br.com.wecare.we_care_project.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService extends IMyService<UserModel>{

    @PersistenceContext
    private EntityManager entityManager;

    UserService(JpaRepository<UserModel, Long> repository) {
        super(repository);
    }

    public UserModel save(UserDto userDto) {
        try {
            UserModel user = new UserModel();
            BeanUtils.copyProperties(userDto, user);

            log.info("Usuário Cadastrado");

            return repository.save(user);

        } catch (Exception e) {
            log.error("Erro ao cadastrar as propriedades do Dto para o usuario: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar usuário");
        }
    }
    public Page<UserModel> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public UserModel findByNameContainingIgnoreCase(String name) {
            return ((UserRepository) repository).findByNameContainingIgnoreCase(name);
    }

    public UserModel findByCpf(long cpf) {

        return ((UserRepository) repository).findByCpf(cpf);
    }

    public UserModel findByEmail(String email) {
        return (((UserRepository) repository).findByEmail(email));
    }

    public UserModel findByLogin(String login) {
        return ((UserRepository) repository).findByLogin(login);
    }

    public List<UserModel> findByEmail2(String email) {
        TypedQuery<UserModel> query = entityManager.createQuery(
                "SELECT u FROM UserModel u WHERE u.email = :email",
                UserModel.class
        );
        query.setParameter("email", email);
        return query.getResultList();
    }




}
