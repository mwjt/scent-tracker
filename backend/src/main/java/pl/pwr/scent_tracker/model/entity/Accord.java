package pl.pwr.scent_tracker.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "ACCORD")
public class Accord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accord_gen")
    @SequenceGenerator(name = "accord_gen", sequenceName = "accord_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false, unique = true)
    private String name;
}
