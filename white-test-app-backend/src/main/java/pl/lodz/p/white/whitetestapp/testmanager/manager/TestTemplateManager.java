package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateRepository;
import pl.lodz.p.white.whitetestapp.testmanager.response.TestTemplateResponse;
import pl.lodz.p.white.whitetestapp.testmanager.response.mapper.TestTemplateMapper;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestTemplateManager implements TestTemplateService {

    TestTemplateRepository repository;

    @Override
    public TestTemplate getOne(Long id) {
        return repository.getOne(id);
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

    @Autowired
    public TestTemplateManager(TestTemplateRepository repository) {
        this.repository = repository;
    }
}
