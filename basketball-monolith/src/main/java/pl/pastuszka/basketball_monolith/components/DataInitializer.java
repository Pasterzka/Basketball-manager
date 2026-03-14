package pl.pastuszka.basketball_monolith.components;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import pl.pastuszka.basketball_monolith.league.League;
import pl.pastuszka.basketball_monolith.league.LeagueService;
import pl.pastuszka.basketball_monolith.team.Team;
import pl.pastuszka.basketball_monolith.team.TeamService;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final LeagueService leagueService;
    private final TeamService teamService;

    @Override
    public void run(String... args) throws Exception {

        League nba = League.builder()
                .id(UUID.fromString("a15d0ec5-7e4e-404b-b9a5-fac84c33fd55"))
                .name("NBA")
                .country("USA")
                .fundingYear(1946)
                .build();
        
        League euroleague = League.builder()
                .id(UUID.randomUUID())
                .name("EuroLeague")
                .country("Europe")
                .fundingYear(1958)
                .build();

        leagueService.save(nba);
        leagueService.save(euroleague);

        Team lakers = Team.builder()
                .id(UUID.randomUUID())
                .name("Los Angeles Lakers")
                .city("Los Angeles")
                .league(nba) // Set the league for the team
                .build();

        Team bulls = Team.builder()
                .id(UUID.randomUUID())
                .name("Chicago Bulls")
                .city("Chicago")
                .league(nba)
                .build();

        Team realMadrid = Team.builder()
                .id(UUID.randomUUID())
                .name("Real Madrid")
                .city("Madrid")
                .league(euroleague)
                .build();

        teamService.save(lakers);
        teamService.save(bulls);
        teamService.save(realMadrid);

        System.out.println("Data initialization completed.");
    }
    
}
