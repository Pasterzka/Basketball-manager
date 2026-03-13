package pl.pastuszka.basketball_monolith.league;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.pastuszka.basketball_monolith.team.Team;

@Entity
@Table(name = "leagues")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class League implements Comparable<League> {

    @Id
    private UUID id;

    @Column(name = "funding_year")
    private int fundingYear;

    // One-to-many relationship with Team, delete league will delete all teams in it
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<Team> teams = new ArrayList<>();
    
    private String name;
    private String country;
    
    @Override
    public int compareTo(League other) {
        return this.getName().compareTo(other.getName());
    }
}
