package pl.lodz.p.white.whitetestapp.csvgenerator.service;

import pl.lodz.p.white.whitetestapp.exception.ParseDataException;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;

public interface CsvDecompositorService {
    TestTemplateContent importCsv(String value, TestTemplate template) throws ParseDataException;
}
