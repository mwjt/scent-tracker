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
@Table(name = "PERFUME")
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfume_gen")
    @SequenceGenerator(name = "perfume_gen", sequenceName = "perfume_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFUMER_ID")
    private Perfumer perfumer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONCENTRATION_ID", nullable = false)
    private Concentration concentration;

    @Column(name = "GALLERY_PATH")
    private String galleryPath;

    @Column(name = "YEAR")
    private Short year;

    @Column(name = "SCENT", nullable = false)
    private Float scent;

    @Column(name = "LONGEVITY", nullable = false)
    private Float longevity;

    @Column(name = "SILLAGE", nullable = false)
    private Float sillage;

    @Column(name = "BOTTLE", nullable = false)
    private Float bottle;

    @Column(name = "VALUE", nullable = false)
    private Float value;
}
