package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateRepository;
import pl.lodz.p.white.whitetestapp.service.TestTemplateService;

@Service
public class TestTemplateManager implements TestTemplateService {

    @Autowired
    TestTemplateRepository repository;

    @Override
    public TestTemplate getOne(Long id) {
        return repository.getOne(id);
    }
}
