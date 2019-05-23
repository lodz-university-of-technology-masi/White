package pl.lodz.p.white.whitetestapp.accountmanager.response.mapper;

import pl.lodz.p.white.whitetestapp.accountmanager.response.PositionResponse;
import pl.lodz.p.white.whitetestapp.model.Position;


public class PositionMapper {
    public static PositionResponse toPositionResponse(Position position) {
        return new PositionResponse().setName(position.getName()).
                setActivated(position.isActivated());
    }
}
