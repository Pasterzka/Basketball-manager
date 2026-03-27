package pl.pastuszka.team_service.service;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.pastuszka.team_service.entity.Team;
import pl.pastuszka.team_service.repository.TeamRepository;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> findByLeagueId(UUID leagueId) {
        return teamRepository.findByLeagueId(leagueId);
    }

    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public void deleteById(UUID id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Team with id " + id + " does not exist.");
        }
    }
}
