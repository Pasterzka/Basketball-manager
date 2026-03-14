package pl.pastuszka.basketball_monolith.team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.pastuszka.basketball_monolith.league.League;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team>  findAllByLeague(League league) {
        return teamRepository.findAllByLeague(league);
    }

    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public void delete(UUID id) {
        teamRepository.deleteById(id);
    }
}
