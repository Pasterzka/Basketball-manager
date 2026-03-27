package pl.pastuszka.league_service.dto;

import java.util.UUID;

public record LeagueToListDTO(
    UUID id,
    String name,
    String country,
    int fundingYear
) {}
