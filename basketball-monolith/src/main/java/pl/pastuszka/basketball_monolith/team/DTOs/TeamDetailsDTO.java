package pl.pastuszka.basketball_monolith.team.DTOs;

import java.util.UUID;

public record TeamDetailsDTO(
    UUID id,
    String name,
    String city,
    String leagueName
) {}
