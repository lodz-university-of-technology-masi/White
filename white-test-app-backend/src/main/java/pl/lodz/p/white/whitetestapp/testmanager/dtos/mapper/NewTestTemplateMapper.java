package pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper;

import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.NewTestTemplateRequest;

import java.util.List;

import static java.util.Arrays.asList;

public class NewTestTemplateMapper {
    private static AccountRepository accountRepository;
    private static PositionRepository positionRepository;

    public static List<TestTemplate> toTestTemplate(NewTestTemplateRequest newTestTemplateRequest){
        Account author = accountRepository.findByUsername(newTestTemplateRequest.getAuthor());
        Position position = positionRepository.findByTestName(newTestTemplateRequest.getTestName());
        return asList(
                new TestTemplate()
                        .setAuthor(author)
                      //  .setLang("PL")
                        .setPosition(position),
                new TestTemplate()
                        .setAuthor(author)
                       // .setLang("EN")
                        .setPosition(position)
        );
    }
}