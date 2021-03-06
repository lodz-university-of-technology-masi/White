package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.UsabilityData;

import java.util.List;

public interface MetricRepository extends JpaRepository<UsabilityData, Long> {

    List<UsabilityData> findAllByUsername(String username);
}
