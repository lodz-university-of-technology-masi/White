package pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper;

import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.NewTestTemplateRequest;

public class NewTestTemplateMapper {
    private static AccountRepository accountRepository;
    private static PositionRepository positionRepository;

    public static TestTemplate toTestTemplate(NewTestTemplateRequest newTestTemplateRequest){
        Account author = accountRepository.findByUsername(newTestTemplateRequest.getAuthor());
        Position position = positionRepository.findByName(newTestTemplateRequest.getPosition());
        String lang = newTestTemplateRequest.getLang();

        TestTemplate newTestTemplate = new TestTemplate();
        newTestTemplate.setAuthor(author);
        newTestTemplate.setPosition(position);
        if (lang.contains("EN")) {
            newTestTemplate.setEnVersion(new TestTemplateContent());
            newTestTemplate.setPlVersion(null);
        }
        else if (lang.contains("PL")) {
            newTestTemplate.setEnVersion(null);
            newTestTemplate.setPlVersion(new TestTemplateContent());
        }
        return newTestTemplate;
    }
}