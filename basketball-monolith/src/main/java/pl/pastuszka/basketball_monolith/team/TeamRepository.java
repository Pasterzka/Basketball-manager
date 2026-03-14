package pl.pastuszka.basketball_monolith.team;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.pastuszka.basketball_monolith.league.League;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    List<Team> findAllByLeague(League league);
}
