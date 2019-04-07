package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.service.TestTemplateContentService;

@Service
public class TestTemplateContentManager implements TestTemplateContentService {

    @Autowired
    TestTemplateContentRepository repository;

    @Override
    public TestTemplateContent getOne(Long id) {
        return repository.getOne(id);
    }
}
