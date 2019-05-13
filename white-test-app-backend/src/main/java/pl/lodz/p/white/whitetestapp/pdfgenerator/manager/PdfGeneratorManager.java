package pl.lodz.p.white.whitetestapp.pdfgenerator.manager;

import com.lowagie.text.Chunk;
import com.lowagie.text.RomanList;
import com.lowagie.text.Document;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.model.Answer;
import pl.lodz.p.white.whitetestapp.model.QuestionType;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.pdfgenerator.service.PdfGeneratorService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfGeneratorManager implements PdfGeneratorService {

    @Override
    public byte[] generate(TestTemplateContent requestedTest) throws DocumentCreationException {
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font font = new Font(helvetica, 12, Font.NORMAL);
            for (int i = 0; i < requestedTest.getQuestions().size(); i++) {
                document.add(new Paragraph(i + 1 + ". " + requestedTest.getQuestions().get(i).getContent(), font));
                document.add(Chunk.NEWLINE);
                String typeName = requestedTest.getQuestions().get(i).getQuestionType().name();
                if (typeName.equals(String.valueOf(QuestionType.OPEN))) {
                    for (int j = 0; j < 4; j++) {
                        document.add(Chunk.NEWLINE);
                    }
                } else {
                    createListOfAnswers(requestedTest, document, i);
                }
                document.add(Chunk.NEWLINE);
            }
            document.close();
            baos.close();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            throw new DocumentCreationException();
        }
    }

    private void createListOfAnswers(TestTemplateContent requestedTest, Document document, int i) {
        RomanList roman = new RomanList(35);
        List<Answer> answers = requestedTest.getQuestions().get(i).getAnswers();
        for (Answer answer : answers) {
            roman.add(new ListItem(answer.getContent()));
        }
        document.add(roman);
    }
}
