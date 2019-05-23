package pl.lodz.p.white.whitetestapp.accountmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.Position;

@Controller
@RestController
@RequestMapping("/api/position")
public class PositionController {

    public static final String POSITION_WAS_CREATED = "Position was created";
    private PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAllPositions() {
        return ResponseEntity.ok(service.getAllPositions());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    ResponseEntity get(@PathVariable("id") String id) throws EntityNotFoundException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    ResponseEntity changeStatus(@PathVariable("id") String id) throws EntityNotFoundException {
        ApiResponse response = new ApiResponse();
        response.setMessage(service.changeStatus(id));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    ResponseEntity addNewPosition(@RequestBody Position position) throws WrongRequestException {
        ApiResponse response = new ApiResponse();
        try {
            service.getOne(position.getName());
        } catch (EntityNotFoundException e) {
            service.addNew(position);
            response.setMessage(POSITION_WAS_CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        throw new WrongRequestException(WrongRequestException.EXISTING_DATA_PASSED);
    }
}
