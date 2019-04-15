package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.accountmanager.response.PositionResponse;
import pl.lodz.p.white.whitetestapp.accountmanager.response.mapper.PositionMapper;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;

import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionManager implements PositionService {

    private PositionRepository repository;

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
    public List<PositionResponse> getAllPositions() {
        return repository
                .findAll()
                .stream()
                .map(PositionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void addNew(Position position) {
        repository.save(position);
    }
}
