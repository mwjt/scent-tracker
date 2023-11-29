package pl.pwr.scent_tracker.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_gen")
    @SequenceGenerator(name = "tag_gen", sequenceName = "tag_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    List<Perfume> perfumes;
}
