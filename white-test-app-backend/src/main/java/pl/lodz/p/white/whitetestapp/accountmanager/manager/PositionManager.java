package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;

import java.util.Optional;

@Service
public class PositionManager implements PositionService {

    PositionRepository repository;

    @Autowired
    public PositionManager(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Position getOne(String id) {
        Optional<Position> position = repository.findById(id);
        return position.orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void addNew(Position position) {
        repository.save(position);
    }
}
