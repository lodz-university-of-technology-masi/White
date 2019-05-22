package pl.lodz.p.white.whitetestapp.csvgenerator.service;

import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;

public interface CsvGeneratorService {
    StringBuilder exportCsv(TestTemplateContent testTemplateContent);
}