package pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper;

import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestTemplateResponse;

import java.util.ArrayList;
import java.util.List;

import static pl.lodz.p.white.whitetestapp.Constants.EN;
import static pl.lodz.p.white.whitetestapp.Constants.PL;
import static pl.lodz.p.white.whitetestapp.utils.Utils.optGet;

public class TestTemplateMapper {

    public static List<TestTemplateResponse> toTestTemplateResponse(TestTemplate testTemplate) {
        Long templateId = testTemplate.getId();
        String author = optGet(testTemplate.getAuthor(), Account::getUsername);
        String position = optGet(testTemplate.getPosition(), Position::getName);
        List<TestTemplateResponse> response =  new ArrayList<>();

        if(testTemplate.getPlVersion() != null ) {
            if (!testTemplate.getPlVersion().isDeleted()) {
                response.add(new TestTemplateResponse()
                        .setId(testTemplate.getPlVersion().getId())
                        .setName(testTemplate.getName())
                        .setAuthor(author)
                        .setLang(PL)
                        .setPosition(position)
                        .setTestTemplateId(templateId));
            }
        }

        if(testTemplate.getEnVersion() != null) {
            if (!testTemplate.getEnVersion().isDeleted()) {
                response.add(new TestTemplateResponse()
                        .setId(testTemplate.getEnVersion().getId())
                        .setName(testTemplate.getName())
                        .setAuthor(author)
                        .setLang(EN)
                        .setPosition(position)
                        .setTestTemplateId(templateId));
            }
        }
        return response;
    }
}
