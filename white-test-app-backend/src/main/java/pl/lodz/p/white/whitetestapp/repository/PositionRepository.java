package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.Position;

public interface PositionRepository extends JpaRepository<Position, String> {
    Position findByName(String name);
}
