package pl.pastuszka.team_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.pastuszka.team_service.entity.Team;


@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    List<Team> findByLeagueId(UUID leagueId);
}
