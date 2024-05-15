package hello;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "double_rolls")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Color is mandatory.")
    private String color;
    @NotBlank(message = "platform is mandatory.")
    private String platform;
    @NotNull(message = "roll is mandatory.")
    private Integer roll;
    @JsonIgnore
    private Instant created = Instant.now();
    private float total_red_money = 0;
    private float total_white_money = 0;
    private float total_black_money = 0;

}
