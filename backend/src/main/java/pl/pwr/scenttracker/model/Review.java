package pl.pwr.scenttracker.model;

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
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_gen")
    @SequenceGenerator(name = "review_gen", sequenceName = "review_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFUME_ID", nullable = false)
    private Perfume perfume;

    @Column(name = "SCENT")
    private Short scent;

    @Column(name = "LONGEVITY")
    private Short longevity;

    @Column(name = "SILLAGE")
    private Short sillage;

    @Column(name = "BOTTLE")
    private Short bottle;

    @Column(name = "VALUE")
    private Short value;

    @Column(name = "TEXT_PATH")
    private String textPath;
}
