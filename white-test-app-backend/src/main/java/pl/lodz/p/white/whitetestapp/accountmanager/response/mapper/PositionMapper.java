package pl.lodz.p.white.whitetestapp.accountmanager.response.mapper;

import pl.lodz.p.white.whitetestapp.accountmanager.response.PositionResponse;
import pl.lodz.p.white.whitetestapp.model.Position;

import java.util.List;
import static java.util.Arrays.asList;


public class PositionMapper {
    public static List<PositionResponse> toPositionResponse(Position position) {

        return asList(
                new PositionResponse()
                        .setName(position.getName())

        );
    }
}
