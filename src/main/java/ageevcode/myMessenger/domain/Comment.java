package ageevcode.myMessenger.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String text;
    @ManyToOne
    @JoinColumn(name = "post_id")
    //@JsonView(Views.FullComment.class)
    @JsonView(Views.IdName.class)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonView(Views.IdName.class)
    private User author;

}
