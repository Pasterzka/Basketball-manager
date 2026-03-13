package pl.pastuszka.basketball_monolith.league.DTOs;

import java.util.UUID;

public record LeagueToListDTO(
    UUID id,
    String name,
    String country,
    int fundingYear
) {}