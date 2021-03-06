package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Comment;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.repo.CommentRepo;
import ageevcode.myMessenger.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.BiConsumer;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;


    @Autowired
    public CommentController(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    @PostMapping
    @JsonView(Views.FullComment.class)
    public Comment create(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
        //return commentService.create(comment, userDetails);
        comment.setAuthor(user);

        Comment updatedComment = commentRepo.save(comment);
        wsSender.accept(EventType.CREATE, updatedComment);
        return updatedComment;
    }
}
