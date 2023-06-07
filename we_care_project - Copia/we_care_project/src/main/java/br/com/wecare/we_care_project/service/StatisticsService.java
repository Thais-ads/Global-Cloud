package br.com.wecare.we_care_project.service;

import br.com.wecare.we_care_project.dtos.StatisticsDto;
import br.com.wecare.we_care_project.model.StatisticsModel;
import br.com.wecare.we_care_project.model.UserModel;
import br.com.wecare.we_care_project.repository.StatisticsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StatisticsService extends IMyService<StatisticsModel>{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StatisticsRepository repository;


    StatisticsService(JpaRepository<StatisticsModel, Long> repository) {
        super(repository);
    }
    public StatisticsModel save(StatisticsDto statisticsDto) {
        try {
            StatisticsModel statistics = new StatisticsModel();
            BeanUtils.copyProperties(statisticsDto, statistics);

            log.info(("Estatistica cadastrada"));

            return repository.save(statistics);
        } catch (Exception e) {
            log.error(("Erro ao cadastrar as propriedades do Dto para a Stastistics: " + e.getMessage()));
            throw new RuntimeException("Erro ao cadastrar Statistics");
        }
    }
    public Page<StatisticsModel> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public StatisticsModel findByCityContainingIgnoreCase(String city) {
        return (repository).findByCityContainingIgnoreCase(city);
    }

    public Page<StatisticsModel> findByWorkingForce(float workingForce, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return (repository).findByWorkingForce(workingForce, pageable);
    }

    public Page<StatisticsModel> findByEducationLevel(float educationLevel,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return (repository).findByEducationLevel(educationLevel, pageable);
    }

    public Page<StatisticsModel> findByPoverty(float poverty,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return (repository).findByPoverty(poverty, pageable);
    }

    public Page<StatisticsModel> findBySalary(float salary,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return (repository).findBySalary(salary, pageable);
    }

    public Page<StatisticsModel> findByPovertyPercentage(float povertyPercentage, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return (repository).findByPovertyPercentage(povertyPercentage, pageable);
    }

    public Page<StatisticsModel> getByBiggerThan(String tableEntity, float value, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        TypedQuery<StatisticsModel> query = entityManager.createQuery(
                "SELECT sm FROM StatisticsModel sm WHERE sm.value > :value AND sm.tableEntity = :tableEntity",
                StatisticsModel.class
        );
        query.setParameter("value", value);
        query.setParameter("tableEntity", tableEntity);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<StatisticsModel> resultList = query.getResultList();
        long totalCount = getTotalCount(tableEntity, value);

        return new PageImpl<>(resultList, pageable, totalCount);
    }

    public Page<StatisticsModel> getByLesserThan(String tableEntity, float value, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        TypedQuery<StatisticsModel> query = entityManager.createQuery(
                "SELECT sm FROM StatisticsModel sm WHERE sm.value < :value AND sm.tableEntity = :tableEntity",
                StatisticsModel.class
        );
        query.setParameter("value", value);
        query.setParameter("tableEntity", tableEntity);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<StatisticsModel> resultList = query.getResultList();
        long totalCount = getTotalCount(tableEntity, value);

        return new PageImpl<>(resultList, pageable, totalCount);
    }


    private long getTotalCount(String tableEntity, float value) {
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(sm) FROM StatisticsModel sm WHERE sm.value > :value AND sm.tableEntity = :tableEntity",
                Long.class
        );
        countQuery.setParameter("value", value);
        countQuery.setParameter("tableEntity", tableEntity);
        return countQuery.getSingleResult();
    }



}
