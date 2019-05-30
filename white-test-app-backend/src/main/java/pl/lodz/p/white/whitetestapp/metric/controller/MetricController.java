package pl.lodz.p.white.whitetestapp.metric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.exception.ScreenshotFileSaveException;
import pl.lodz.p.white.whitetestapp.metric.service.MetricService;
import pl.lodz.p.white.whitetestapp.model.UsabilityData;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping("/api/metric")
public class MetricController {

    private final MetricService service;

    @Autowired
    public MetricController(MetricService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@RequestBody UsabilityData usabilityData, HttpServletRequest request) {
        service.add(usabilityData, request.getRemoteAddr(), request.getUserPrincipal().getName());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/screenshot", method = RequestMethod.POST)
    ResponseEntity saveScreenshot(@RequestBody String image, HttpServletRequest request) throws ScreenshotFileSaveException {
        service.saveScreenshot(image, request.getUserPrincipal().getName());
        return ResponseEntity.ok().build();
    }
}
