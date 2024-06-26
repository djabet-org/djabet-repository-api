package hello;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "crash_points")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Crash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Vela is mandatory.")
    private Double crash_point;
    @NotBlank(message = "platform is mandatory.")
    private String platform;
    @JsonIgnore
    private Instant created = ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    @Transient
    private String createdTime = created.toString();
    private int total_bets_placed = 0;
    private float total_money_bets = 0;
    private float total_money_bets_won = 0;

    @Transient
    @JsonProperty
    private long createdTimeAsTimestamp = created.toEpochMilli();

}
