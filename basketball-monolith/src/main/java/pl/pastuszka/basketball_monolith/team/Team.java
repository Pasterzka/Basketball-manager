package pl.pastuszka.basketball_monolith.team;

import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToAny
    @JoinColumn(name = "league_id")
    private League league;

    private String name;
    private String city;
    
    @Override
    public int compareTo(Team other) {
        return this.getName().compareTo(other.getName());
    }
}
