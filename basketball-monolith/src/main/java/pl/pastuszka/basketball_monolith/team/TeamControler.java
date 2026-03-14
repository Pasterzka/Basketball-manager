package pl.pastuszka.basketball_monolith.team;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pl.pastuszka.basketball_monolith.league.LeagueService;
import pl.pastuszka.basketball_monolith.team.DTOs.TeamCreateUpdateDTO;
import pl.pastuszka.basketball_monolith.team.DTOs.TeamToListDTO;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/leagues/{leagueId}/teams")
@AllArgsConstructor
public class TeamControler {
    private final TeamService teamService;
    private final LeagueService leagueService;

    @GetMapping
    public ResponseEntity<List<TeamToListDTO>> getTeams(@PathVariable UUID leagueId) {
        return leagueService.findById(leagueId)
            .map(l -> ResponseEntity.ok(
                teamService.findAllByLeague(l).stream()
                    .map(t -> new TeamToListDTO(
                        t.getId(),
                        t.getName(),
                        t.getCity()
                    ))
                    .toList() // status 200 OK response with list of teams in the body
            ))
            .orElse(ResponseEntity.notFound().build()); // If league is not found, return 404 Not Found response
    }
    

    @PostMapping
    public ResponseEntity<Void> createTeam(@PathVariable UUID leagueId, @RequestBody TeamCreateUpdateDTO dto){
        return leagueService.findById(leagueId)
            .map(l -> {
                Team team = Team.builder()
                    .id(UUID.randomUUID())
                    .name(dto.name())
                    .city(dto.city())
                    .league(l)
                    .build();
                teamService.save(team);
                return ResponseEntity.status(201).<Void>build(); // 201 Created response
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // If league is not found, return 404 Not Found response
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeam(@PathVariable UUID leagueId, @PathVariable UUID id, @RequestBody TeamCreateUpdateDTO dto) {
        return teamService.findById(id)
            .map(t -> {
                t.setName(dto.name());
                t.setCity(dto.city());
                teamService.save(t);
                return ResponseEntity.ok().<Void>build(); // 200 OK response
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // If team is not found, return 404 Not Found response
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID leagueId, @PathVariable UUID id) {
        if (teamService.findById(id).isPresent()) {
            teamService.delete(id);
            return ResponseEntity.ok().<Void>build(); // 200 OK response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // If team is not found, return 404 Not Found response
        }
    }
    
}
