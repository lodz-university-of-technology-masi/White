package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.Metric;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
