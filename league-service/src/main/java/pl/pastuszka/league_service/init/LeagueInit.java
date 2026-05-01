package pl.pastuszka.league_service.init;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import pl.pastuszka.league_service.entity.League;
import pl.pastuszka.league_service.service.LeagueService;

@Component
@AllArgsConstructor
public class LeagueInit implements CommandLineRunner {
    private final LeagueService leagueService;

    @Override
    public void run(String... args) throws Exception {

        if (leagueService.findAll().isEmpty()) {
            leagueService.save(new League(UUID.fromString("11111111-1111-1111-1111-111111111111"), 1946, "NBA", "USA"));
            leagueService.save(new League(UUID.fromString("22222222-2222-2222-2222-222222222222"), 1958, "EuroLeague", "Europe"));
            leagueService.save(new League(UUID.fromString("33333333-3333-3333-3333-333333333333"), 1995, "PLK", "Poland"));

            System.out.println("[LeagueInit] Leagues initialized");
        }


    }
    
}
