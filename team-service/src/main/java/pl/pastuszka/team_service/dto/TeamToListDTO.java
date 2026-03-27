package pl.pastuszka.team_service.dto;

import java.util.UUID;

public record TeamToListDTO(
    UUID id,
    String name,
    String city,
    UUID leagueID
) {}
