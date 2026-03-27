package pl.pastuszka.league_service.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leagues")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class League implements Comparable<League> {

    @Id
    private UUID id;

    private int fundingYear;
    private String name;
    private String country;

    @Override
    public int compareTo(League arg0) {
        return this.name.compareTo(arg0.name);
    }

}
