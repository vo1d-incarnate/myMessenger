package ageevcode.myMessenger.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String text;
    @Column(updatable = false)
    @JsonView(Views.FullPost.class)
    private LocalDateTime createdAt;
    @JsonView(Views.FullPost.class)
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FullPost.class)
    private User author;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonView(Views.FullPost.class)
    private List<Comment> comments;

}
