package pl.pastuszka.basketball_monolith.league;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pl.pastuszka.basketball_monolith.league.DTOs.LeagueToListDTO;
import pl.pastuszka.basketball_monolith.league.DTOs.LeagueUpdateDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/leagues")
@AllArgsConstructor
public class LeagueControler {
    private final LeagueService leagueService;

    @GetMapping
    public List<LeagueToListDTO> getAllLeagues() {
        return leagueService.findAll().stream().
        map(l ->  new LeagueToListDTO(
            l.getId(),
            l.getName(),
            l.getCountry(),
            l.getFundingYear()
        ))
        .collect(Collectors.toList());
    }
    
    // Returns a ResponseEntity containing the League if found, or a 404 Not Found response if not found
    @GetMapping("/{id}")
    public ResponseEntity<League> getLeague(@PathVariable UUID id) {
        return leagueService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createLeague(@RequestBody League league) { 
        if (league.getId() == null) {
            league.setId(UUID.randomUUID());
        }
        leagueService.save(league);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLeague(@PathVariable UUID id, @RequestBody LeagueUpdateDTO leagueUpdateDTO) {
        return leagueService.findById(id)
            .map(l -> {
                l.setName(leagueUpdateDTO.name());
                l.setCountry(leagueUpdateDTO.country());
                l.setFundingYear(leagueUpdateDTO.fundingYear());
                leagueService.save(l);
                return ResponseEntity.noContent().<Void>build(); // 204 No Content response
            })
            .orElse(ResponseEntity.notFound().build()); // 404 Not Found response if league with given id does not exist
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable UUID id) {
        return leagueService.findById(id)
            .map(l -> {
                leagueService.delete(id);
                return ResponseEntity.noContent().<Void>build(); // 204 No Content response
            })
            .orElse(ResponseEntity.notFound().build()); // 404 Not Found response if league with given id does not exist
    }

}
