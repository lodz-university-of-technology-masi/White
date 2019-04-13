package pl.lodz.p.white.whitetestapp.accountmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.Position;

@Controller
@RestController
@RequestMapping("/api/position")
public class PositionController {

    public static final String POSITION_WITH_THIS_NAME_DOESN_T_EXISTS = "Position with this name doesn't exists";
    public static final String POSITION_COULDN_T_BE_CREATED = "Position couldn't be created";
    public static final String POSITION_WAS_CREATED = "Position was created";
    PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity get(@PathVariable("id") String id) {
        Position returned = service.getOne(id);
        ApiResponse response = new ApiResponse();
        if (returned == null) {
            response.setMessage(POSITION_WITH_THIS_NAME_DOESN_T_EXISTS);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(returned);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    ResponseEntity addNewPosition(@RequestBody Position position) {
        ApiResponse response = new ApiResponse();
        if (service.getOne(position.getName()) != null) {
            response.setMessage(POSITION_COULDN_T_BE_CREATED);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            service.addNew(position);
            response.setMessage(POSITION_WAS_CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }
    }
}
