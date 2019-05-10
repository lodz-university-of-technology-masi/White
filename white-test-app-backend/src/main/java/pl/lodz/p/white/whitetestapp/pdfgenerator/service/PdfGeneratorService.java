package pl.lodz.p.white.whitetestapp.pdfgenerator.service;

import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;

public interface PdfGeneratorService {
    byte[] generate(TestTemplateContent requestedTest) throws DocumentCreationException;
}
