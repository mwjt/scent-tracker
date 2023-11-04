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
@Table(name = "COLLECTION_TYPE")
public class CollectionType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coll_type_gen")
    @SequenceGenerator(name = "coll_type_gen", sequenceName = "coll_type_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false, unique = true)
    private String name;
}
