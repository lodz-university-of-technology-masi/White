package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.accountmanager.response.PositionResponse;
import pl.lodz.p.white.whitetestapp.accountmanager.response.mapper.PositionMapper;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionManager implements PositionService {

    public static final String POSITION_WAS_UPDATED = "Position was updated";
    private PositionRepository repository;

    @Autowired
    public PositionManager(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Position getOne(String id) throws EntityNotFoundException {
        Optional<Position> position = repository.findById(id);
        return position.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PositionResponse> getAllPositions() {
        return repository
                .findAll()
                .stream()
                .filter(Position::isActivated)
                .map(PositionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionResponse> getAllPositionsUnfiltered() {
        return repository
                .findAll()
                .stream()
                .map(PositionMapper::toPositionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Position findOne(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = false)
    public void addNew(Position position) {
        repository.save(position);
    }

    @Override
    public String changeStatus(String id) throws EntityNotFoundException, ConstraintViolationException, IllegalArgumentException{
        Position position = findOne(id);
        position.setActivated(!position.isActivated());
        repository.saveAndFlush(position);
        return POSITION_WAS_UPDATED;
    }
}
