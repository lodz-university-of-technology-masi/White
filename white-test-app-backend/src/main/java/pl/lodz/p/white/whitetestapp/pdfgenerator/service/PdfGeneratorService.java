package pl.lodz.p.white.whitetestapp.pdfgenerator.service;

import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;

public interface PdfGeneratorService {
    byte[] generate() throws DocumentCreationException;
}
