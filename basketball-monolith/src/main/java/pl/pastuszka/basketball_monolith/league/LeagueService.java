package pl.pastuszka.basketball_monolith.league;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    // Returns an Optional containing the League if found, or an empty Optional if not found
    public Optional<League> findById(UUID id) {
        return leagueRepository.findById(id);
    }

    public void save(League league) {
        leagueRepository.save(league);
    }

    public void delete(UUID id) {
        leagueRepository.deleteById(id);
    }

    
}
