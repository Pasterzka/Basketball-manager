package pl.pastuszka.team_service.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teams")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Comparable<Team>{

    @Id
    private UUID id;

    private String name;
    private String city;
    private UUID leagueId;

    @Override
    public int compareTo(Team arg0) {
        return this.name.compareTo(arg0.getName());
    }
    
}
