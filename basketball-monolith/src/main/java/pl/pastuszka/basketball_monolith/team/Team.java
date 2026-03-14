package pl.pastuszka.basketball_monolith.team;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.pastuszka.basketball_monolith.league.League;

@Entity
@Table(name = "teams")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Comparable<Team> {

    @Id
    private UUID id;

    // Many-to-one relationship with League, delete team will not delete the league
    @ManyToOne
    @JoinColumn(name = "league_id")
    @ToString.Exclude
    private League league;

    

    private String name;
    private String city;
    
    @Override
    public int compareTo(Team other) {
        // Sort teams alphabetically by name
        return this.getName().compareTo(other.getName());
    }
}
