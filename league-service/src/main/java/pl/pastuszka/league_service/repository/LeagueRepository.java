package pl.pastuszka.league_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.pastuszka.league_service.entity.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, UUID> {
    
}
