package pl.pwr.scent_tracker.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "GALLERY")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gallery_gen")
    @SequenceGenerator(name = "gallery_gen", sequenceName = "gallery_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Lob
    @Column(name = "IMAGE", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
}
