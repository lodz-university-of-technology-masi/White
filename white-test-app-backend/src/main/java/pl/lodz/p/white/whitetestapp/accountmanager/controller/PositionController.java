package pl.lodz.p.white.whitetestapp.accountmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;

@Controller
@RestController
@RequestMapping("/api/position")
public class PositionController {

   private PositionService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Position get(@PathVariable("id") String id){
        return service.getOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAllPositions() {
        return ResponseEntity.ok(service.getAllPositions());
    }

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }
}
