package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.NewTestTemplateRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestTemplateResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TestTemplateService {

    TestTemplate getOne(Long id) throws EntityNotFoundException;

    TestTemplate findOne(Long id) throws EntityNotFoundException;

    List<TestTemplateResponse> getAll(HttpServletRequest request) throws WrongRequestException;

    TestTemplate addNewTestTemplate(NewTestTemplateRequest testTemplate) throws WrongRequestException;

    void setPositionForTest(TestTemplate test, Position position) throws FailedSaveException;

    void translate(Long id, String sourceLang);

    void deleteTestById(Long id, String lang) throws EntityNotFoundException;
}
