package pl.pastuszka.basketball_monolith.team.DTOs;

import java.util.UUID;

public record TeamToListDTO(
    UUID id,
    String name,
    String city
) {}
