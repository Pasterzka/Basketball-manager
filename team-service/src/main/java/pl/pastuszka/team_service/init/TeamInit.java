package pl.pastuszka.team_service.init;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import pl.pastuszka.team_service.entity.Team;
import pl.pastuszka.team_service.service.TeamService;

@Component
@AllArgsConstructor
public class TeamInit implements CommandLineRunner {
    private final TeamService teamService;

    @Override
    public void run(String... args) throws Exception {
        UUID leagueId1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID leagueId2 = UUID.fromString("22222222-2222-2222-2222-222222222222");
        UUID leagueId3 = UUID.fromString("33333333-3333-3333-3333-333333333333");

        Team team1 = new Team(UUID.randomUUID(), "Los Angeles Lakers", "Los Angeles", leagueId1);
        Team team2 = new Team(UUID.randomUUID(), "Boston Celtics", "Boston", leagueId1);
        Team team3 = new Team(UUID.randomUUID(), "Real Madrid", "Madrid", leagueId2);
        Team team4 = new Team(UUID.randomUUID(), "FC Barcelona", "Barcelona", leagueId2);
        Team team5 = new Team(UUID.randomUUID(), "Anwil Włocławek", "Włocławek", leagueId3);
        Team team6 = new Team(UUID.randomUUID(), "Stal Ostrów Wielkopolski", "Ostrów Wielkopolski", leagueId3);

        if (teamService.findByLeagueId(leagueId1).isEmpty()) {
            teamService.save(team1);
            teamService.save(team2);
        }

        if (teamService.findByLeagueId(leagueId2).isEmpty()) {
            teamService.save(team3);
            teamService.save(team4);
        }

        if (teamService.findByLeagueId(leagueId3).isEmpty()) {
            teamService.save(team5);
            teamService.save(team6);
        }

        System.out.println("[TeamInit] Teams initialized");
    }
    
}
