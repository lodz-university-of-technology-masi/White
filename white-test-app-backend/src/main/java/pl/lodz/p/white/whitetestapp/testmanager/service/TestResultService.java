package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;

public interface TestResultService {

    TestResult getOne(Long id);

    void add(CandidateTestResultRequest testResultRequest) throws WrongRequestException;
}
