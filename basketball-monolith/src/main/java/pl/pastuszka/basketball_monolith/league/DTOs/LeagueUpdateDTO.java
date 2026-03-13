package pl.pastuszka.basketball_monolith.league.DTOs;

public record LeagueUpdateDTO(
    String name,
    String country,
    int fundingYear
) {}
