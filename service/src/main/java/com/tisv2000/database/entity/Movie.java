package com.tisv2000.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(
        name = "WithReviews",
        attributeNodes = {
                @NamedAttributeNode("reviews")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "reviews")
@EqualsAndHashCode(exclude = "reviews")
@Table(name = "movie")
public class Movie implements EntityBase<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;

    private String title;
    private Integer year;
    private String country;
    private String image;
    private String description;
}
