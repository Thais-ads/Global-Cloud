package br.com.wecare.we_care_project.Interfaces;

import br.com.wecare.we_care_project.utils.InvalidEntityException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface IMyInterface<T> {

    /**
     * Recupera todos os objetos do tipo T.
     * @return uma lista de objetos do tipo T
     * @throws EntityNotFoundException se nenhum objeto for encontrado
     */
    List<T> getAll() throws EntityNotFoundException;

    /**
     * Salva um objeto do tipo T.
     * @param entity o objeto a ser salvo
     * @return o objeto salvo com o ID atribuído
     * @throws InvalidEntityException se o objeto não for válido para ser salvo
     */
    T save(T entity) throws InvalidEntityException;

    /**
     * Exclui um objeto do tipo T com o ID especificado.
     * @param id o ID do objeto a ser excluído
     * @throws EntityNotFoundException se o objeto com o ID especificado não for encontrado
     */
    void deleteById(Long id) throws EntityNotFoundException;

    /**
     * Recupera um objeto do tipo T com o ID especificado.
     * @param id o ID do objeto a ser recuperado
     * @return um Optional que contém o objeto se encontrado, ou vazio se não encontrado
     */
    Optional<T> findById(Long id);

}

