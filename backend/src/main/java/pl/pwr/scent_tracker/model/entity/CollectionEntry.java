package pl.pwr.scent_tracker.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Blob;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "COLLECTION_ENTRY")
public class CollectionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coll_entry_gen")
    @SequenceGenerator(name = "coll_entry_gen", sequenceName = "coll_entry_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFUME_ID", nullable = false)
    private Perfume perfume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COLLECTION_TYPE_ID", nullable = false)
    private CollectionType collectionType;

    @Column(name = "QUANTITY", nullable = false)
    private Short quantity;

    @Lob
    @Column(name = "NOTE")
    private Blob note;
}
