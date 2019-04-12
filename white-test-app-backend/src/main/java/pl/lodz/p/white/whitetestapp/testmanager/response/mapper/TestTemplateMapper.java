package pl.lodz.p.white.whitetestapp.testmanager.response.mapper;

import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.response.TestTemplateResponse;

import java.util.List;

import static java.util.Arrays.asList;
import static pl.lodz.p.white.whitetestapp.utils.Utils.optGet;

public class TestTemplateMapper {

    public static List<TestTemplateResponse> toTestTemplateResponse(TestTemplate testTemplate) {
        String author = optGet(testTemplate.getAuthor(), Account::getUsername);
        String position = optGet(testTemplate.getPosition(), Position::getName);
        return asList(
                new TestTemplateResponse()
                        .setId(testTemplate.getPlVersion().getId())
                        .setAuthor(author)
                        .setLang("PL")
                        .setPosition(position),
                new TestTemplateResponse()
                        .setId(testTemplate.getEnVersion().getId())
                        .setAuthor(author)
                        .setLang("EN")
                        .setPosition(position)
        );
    }
}
