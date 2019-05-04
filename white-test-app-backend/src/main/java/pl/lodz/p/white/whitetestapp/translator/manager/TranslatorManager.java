package pl.lodz.p.white.whitetestapp.translator.manager;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.translator.service.TranslatorService;

@Service
public class TranslatorManager implements TranslatorService {
    private Translate translate;

    public TranslatorManager() {
        this.translate = TranslateOptions
                .newBuilder()
                .setApiKey("AIzaSyDeOcjvFJprDnFryVjGm9WP2KWVYUjSojA")
                .build()
                .getService();
    }

    @Override
    public String translateSentence(String text, String sourceLang, String targetLang) {
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(sourceLang),
                        Translate.TranslateOption.targetLanguage(targetLang));

        return translation.getTranslatedText();
    }
}
