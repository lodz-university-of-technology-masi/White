package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.response.TestTemplateResponse;

import java.util.List;

public interface TestTemplateService {

    TestTemplate getOne(Long id);

    List<TestTemplateResponse> getAll();
}
