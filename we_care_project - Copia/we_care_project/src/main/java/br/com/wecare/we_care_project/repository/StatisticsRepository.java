package br.com.wecare.we_care_project.repository;

import br.com.wecare.we_care_project.model.StatisticsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsModel, Long> {

    StatisticsModel findByCityContainingIgnoreCase(String city);
    Page<StatisticsModel> findByWorkingForce(float workingForce, Pageable pageable);

    Page<StatisticsModel> findByEducationLevel(float educationLevel, Pageable pageable);

    Page<StatisticsModel> findByPoverty(float poverty, Pageable pageable);

    Page<StatisticsModel> findBySalary(float salary, Pageable pageable);

    Page<StatisticsModel> findByPovertyPercentage(float povertyPercentage, Pageable pageable);
}
