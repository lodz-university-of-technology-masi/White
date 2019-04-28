package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateRepository;
import pl.lodz.p.white.whitetestapp.testmanager.response.TestTemplateResponse;
import pl.lodz.p.white.whitetestapp.testmanager.response.mapper.TestTemplateMapper;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestTemplateManager implements TestTemplateService {

    TestTemplateRepository repository;

    @Autowired
    public TestTemplateManager(TestTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public TestTemplate getOne(Long id) {
        try {
            return repository.getOne(id);
        } catch (PersistenceException e) {
            return null;
        }

    }

    @Override
    public TestTemplate findOne(Long id){
        TestTemplate object = repository.findById(id).orElse(null);
        return object;
    }

    @Override
    public List<TestTemplateResponse> getAll() {
        return repository
                .findAll()
                .stream()
                .map(TestTemplateMapper::toTestTemplateResponse)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public int setPositionForTest(TestTemplate test, Position position) {
        try{
            test.setPosition(position);
            repository.saveAndFlush(test);
            return 1;
        } catch (PersistenceException e){
            return 0;
        }
    }

}
