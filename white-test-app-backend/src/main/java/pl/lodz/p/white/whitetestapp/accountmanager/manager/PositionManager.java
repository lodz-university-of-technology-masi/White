package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.accountmanager.response.PositionResponse;
import pl.lodz.p.white.whitetestapp.accountmanager.response.mapper.PositionMapper;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionManager implements PositionService {

    private PositionRepository repository;

    @Override
    public Position getOne(String id) {
        return repository.getOne(id);
    }

    @Override
    public List<PositionResponse> getAllPositions() {
        return repository
                .findAll()
                .stream()
                .map(PositionMapper::toPositionResponse)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Autowired
    public PositionManager(PositionRepository repository) {
        this.repository = repository;
    }
}
