package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestCheckRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestResultDetailResponse;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TestResultService {

    TestResultDetailResponse getOne(Long id) throws WrongRequestException;

    void add(CandidateTestResultRequest testResultRequest, String username) throws WrongRequestException;

    void addChecked(TestCheckRequest testCheckRequest) throws WrongRequestException;

    List<TestResultResponse> getAll(String username);
}
