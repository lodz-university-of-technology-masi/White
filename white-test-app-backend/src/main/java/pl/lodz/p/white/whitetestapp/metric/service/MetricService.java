package pl.lodz.p.white.whitetestapp.metric.service;

import pl.lodz.p.white.whitetestapp.exception.ScreenshotFileSaveException;
import pl.lodz.p.white.whitetestapp.model.UsabilityData;

public interface MetricService {

    void add(UsabilityData usabilityData, String ip, String username);

    void saveScreenshot(String image, String username) throws ScreenshotFileSaveException;
}
