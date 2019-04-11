package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.repository.TestResultRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestResultService;

@Service
public class TestResultManager implements TestResultService {

    TestResultRepository repository;

    @Override
    public TestResult getOne(Long id) {
        return repository.getOne(id);
    }

    @Autowired
    public TestResultManager(TestResultRepository repository) {
        this.repository = repository;
    }
}
