package pl.lodz.p.white.whitetestapp.accountmanager.service;

import pl.lodz.p.white.whitetestapp.accountmanager.response.PositionResponse;
import pl.lodz.p.white.whitetestapp.model.Position;

import java.util.List;

public interface PositionService {

    Position getOne(String id);

    List<PositionResponse> getAllPositions();

    void addNew(Position position);

}
