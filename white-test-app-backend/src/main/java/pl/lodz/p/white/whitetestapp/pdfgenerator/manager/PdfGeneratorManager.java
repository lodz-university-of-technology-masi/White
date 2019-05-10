package pl.lodz.p.white.whitetestapp.pdfgenerator.manager;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.pdfgenerator.service.PdfGeneratorService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGeneratorManager implements PdfGeneratorService {

    @Override
    public ByteArrayOutputStream generate() throws DocumentCreationException {
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Lorem ipsum"));
            document.close();
            baos.close();
            return baos;
        } catch (DocumentException | IOException e) {
            throw new DocumentCreationException();
        }
    }
}
