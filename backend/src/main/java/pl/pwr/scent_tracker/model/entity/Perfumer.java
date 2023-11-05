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
@Table(name = "PERFUMER")
public class Perfumer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfumer_gen")
    @SequenceGenerator(name = "perfumer_gen", sequenceName = "perfumer_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "PHOTO_PATH")
    private String photoPath;

    @Column(name = "TEXT_PATH")
    private String textPath;

    @Column(name = "WEBSITE")
    private String website;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;
}
