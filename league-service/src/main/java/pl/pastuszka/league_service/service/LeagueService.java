package pl.pastuszka.league_service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.pastuszka.league_service.entity.League;
import pl.pastuszka.league_service.repository.LeagueRepository;

@Service
@AllArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    public Optional<League> findById(UUID id) {
        return leagueRepository.findById(id);
    }

    public League save(League league) {
        return leagueRepository.save(league);
    }

    public void deleteById(UUID id) {
        leagueRepository.deleteById(id);
    }
}
