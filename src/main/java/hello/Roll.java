package hello;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    
    private String color;
    private String platform;
    private int roll;
    private Instant created = Instant.now();
    private float total_red_money;
    private float total_white_money;
    private float total_black_money;

}
