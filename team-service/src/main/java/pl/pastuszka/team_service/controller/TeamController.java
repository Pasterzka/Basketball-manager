package pl.pastuszka.team_service.controller;

import java.util.UUID;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import pl.pastuszka.team_service.dto.TeamToListDTO;
import pl.pastuszka.team_service.dto.TeamUpdateDTO;
import pl.pastuszka.team_service.entity.Team;
import pl.pastuszka.team_service.service.TeamService;

@RestController
@RequestMapping("/leagues/{leagueId}/teams")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final RestTemplate restTemplate;

    // Check if league exists before performing any team operations
    private boolean leagueExists(UUID leagueId){
        String gatewayUrl = "http://localhost:8080/leagues/" + leagueId;
        try {
            restTemplate.getForEntity(gatewayUrl, Void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }catch (Exception e) {
            System.err.println("Error while checking league existence: " + e.getMessage());
            return false;
        }
    }

    // Get all teams in a league
    @GetMapping
    public ResponseEntity<?> getTeams(@PathVariable UUID leagueId) {
        if (!leagueExists(leagueId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teamService.findByLeagueId(leagueId).stream()
            .map(team -> new TeamToListDTO(
                team.getId(), 
                team.getName(), 
                team.getCity(), 
                team.getLeagueId()
            ))
            .toList());
    }

    // Get a specific team by ID within a league
    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable UUID leagueId, @PathVariable UUID teamId) {
        if (!leagueExists(leagueId)) {
            return ResponseEntity.notFound().build();
        }

        return teamService.findById(teamId)
            .filter(team -> team.getLeagueId().equals(leagueId))
            .map(team -> new TeamToListDTO(
                team.getId(), 
                team.getName(), 
                team.getCity(), 
                team.getLeagueId()
            ))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Add team to league
    @PostMapping
    public ResponseEntity<?> createTeam(@PathVariable UUID leagueId, @RequestBody Team team) {
        if (!leagueExists(leagueId)) {
            return ResponseEntity.notFound().build(); // 404 no league found
        }

        if (team.getId() == null){
            team.setId(UUID.randomUUID());
        }

        team.setLeagueId(leagueId);

        teamService.save(team);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 status created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable UUID leagueId, @PathVariable UUID id, @RequestBody TeamUpdateDTO dto){
        if (!leagueExists(leagueId)) {
            return ResponseEntity.notFound().build(); // 404 no league found
        }

        return teamService.findById(id)
        .map(t ->{
            t.setId(dto.id());
            t.setName(dto.name());
            t.setCity(dto.city());
            t.setLeagueId(dto.leagueId());

            teamService.save(t);
            return ResponseEntity.ok().build();
        })
        .orElse(ResponseEntity.notFound().build()); // 404 team not found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable UUID leagueId, @PathVariable UUID id){
        if (!leagueExists(leagueId)) {
            return ResponseEntity.notFound().build(); // 404 no league found
        }

        return teamService.findById(id)
            .map(l -> {
                teamService.deleteById(id);
                return ResponseEntity.ok().<Void>build(); // 200 OK response
            })
            .orElse(ResponseEntity.notFound().build()); // If team is not found, return 404 Not Found response
    }
}
