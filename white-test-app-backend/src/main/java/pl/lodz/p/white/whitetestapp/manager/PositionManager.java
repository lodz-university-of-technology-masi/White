package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;
import pl.lodz.p.white.whitetestapp.service.PositionService;

@Service
public class PositionManager implements PositionService {

    @Autowired
    PositionRepository repository;

    @Override
    public Position getOne(String id) {
        return repository.getOne(id);
    }
}
