package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.response.TestTemplateResponse;

import java.util.List;

public interface TestTemplateService {

    TestTemplate getOne(Long id);

    TestTemplate findOne(Long id);

    List<TestTemplateResponse> getAll();

    int setPositionForTest(TestTemplate test, Position position);

    void translate(Long id, String sourceLang);

}
