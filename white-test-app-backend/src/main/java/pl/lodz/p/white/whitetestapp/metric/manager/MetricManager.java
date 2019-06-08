package pl.lodz.p.white.whitetestapp.metric.manager;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.exception.ScreenshotFileSaveException;
import pl.lodz.p.white.whitetestapp.metric.service.MetricService;
import pl.lodz.p.white.whitetestapp.model.UsabilityData;
import pl.lodz.p.white.whitetestapp.repository.MetricRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MetricManager implements MetricService {

    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final MetricRepository repository;

    @Autowired
    public MetricManager(MetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(UsabilityData usabilityData, String ip, String username) {
        usabilityData
                .setIp(ip)
                .setUsername(username)
                .setSavetime(Instant.now())
                .setmId((long) repository.findAllByUsername(username).size());
        repository.saveAndFlush(usabilityData);
    }

    @Override
    public void saveScreenshot(final String image, final String username) throws ScreenshotFileSaveException {
        try {
            byte[] data = Base64.decodeBase64(image.substring(image.indexOf(',')));
            String filename = (username != null ? username : "") +
                    "_" +
                    LocalDateTime.now().format(TIME_FORMAT)
                            .replace(" ", "_")
                            .replace("/", "_")
                            .replace(":", "_") +
                    ".jpg";
            File imageMetric = new File(filename);
            String location = imageMetric.getAbsolutePath();
            FileOutputStream fileOutputStream = new FileOutputStream(location, false);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new ScreenshotFileSaveException();
        }
    }
}
