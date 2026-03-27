package pl.pastuszka.team_service.dto;

import java.util.UUID;

public record TeamUpdateDTO(
    UUID id,
    String name,
    String city,
    UUID leagueId
) {}
