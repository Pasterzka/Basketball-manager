package pl.pastuszka.league_service.dto;

import java.util.UUID;

public record LeagueUpdateDTO(
    UUID id,
    String name,
    String country,
    int fundingYear
) {}
