package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.repository.TestResultRepository;
import pl.lodz.p.white.whitetestapp.service.TestResultService;

@Service
public class TestResultManager implements TestResultService {

    @Autowired
    TestResultRepository repository;

    @Override
    public TestResult getOne(Long id) {
        return repository.getOne(id);
    }
}
