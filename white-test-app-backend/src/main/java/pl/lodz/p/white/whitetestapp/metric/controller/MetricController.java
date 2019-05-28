package pl.lodz.p.white.whitetestapp.metric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.metric.service.MetricService;
import pl.lodz.p.white.whitetestapp.model.UsabilityData;

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
    ResponseEntity add(@RequestBody UsabilityData usabilityData) {
        service.add(usabilityData);
        return ResponseEntity.ok().build();
    }
}
