package pl.lodz.p.white.whitetestapp.pdfgenerator.service;

import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;

import java.io.ByteArrayOutputStream;

public interface PdfGeneratorService {
    ByteArrayOutputStream generate() throws DocumentCreationException;
}
