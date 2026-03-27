package pl.pastuszka.league_service.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pl.pastuszka.league_service.dto.LeagueToListDTO;
import pl.pastuszka.league_service.dto.LeagueUpdateDTO;
import pl.pastuszka.league_service.entity.League;
import pl.pastuszka.league_service.service.LeagueService;

@RestController
@RequestMapping("/leagues")
@AllArgsConstructor
public class LeagueController {
    private final LeagueService leagueService;

    // Get all leagues and return them as a list of LeagueToListDTO objects
    @GetMapping
    public List<LeagueToListDTO> getAllLeagues() {
        return leagueService.findAll().stream()
            .map(l -> new LeagueToListDTO(
                l.getId(),
                l.getName(),
                l.getCountry(),
                l.getFundingYear()
            ))
        .collect(Collectors.toList());
    }

    // Get a specific league by its ID and return it as a ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<League> getLeague(@PathVariable UUID id) {
        return leagueService.findById(id)
            .map(ResponseEntity::ok)    // If league is found, return it with 200 OK response
            .orElse(ResponseEntity.notFound().build()); // If league is not found, return 404 Not Found response
    }

    // Create a new league and return a 201 Created response
    @PostMapping
    public ResponseEntity<Void> createLeague(@RequestBody League league) { 
        if (league.getId() == null) {
            league.setId(UUID.randomUUID());
        }
        leagueService.save(league);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created response
    }

    // Update an existing league by its ID and return a 200 OK response if successful, or a 404 Not Found response if the league does not exist
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLeague(@PathVariable UUID id, @RequestBody LeagueUpdateDTO leagueUpdateDTO) {
        return leagueService.findById(id)
            .map( l -> {
                l.setId(id);
                l.setName(leagueUpdateDTO.name());
                l.setCountry(leagueUpdateDTO.country());
                l.setFundingYear(leagueUpdateDTO.fundingYear());
                leagueService.save(l);
                return ResponseEntity.ok().<Void>build(); // 200 OK response
            })
            .orElse(ResponseEntity.notFound().build()); // If league is not found, return 404 Not Found response
    }

    // Delete a league by its ID and return a 200 OK response if successful, or a 404 Not Found response if the league does not exist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable UUID id) {
        return leagueService.findById(id)
            .map(l -> {
                leagueService.deleteById(id);
                return ResponseEntity.ok().<Void>build(); // 200 OK response
            })
            .orElse(ResponseEntity.notFound().build()); // If league is not found, return 404 Not Found response
    }
}
