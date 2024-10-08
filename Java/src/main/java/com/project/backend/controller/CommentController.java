package com.project.backend.controller;

import com.project.backend.dto.CommentDto;
//import com.project.backend.dto.ReactionsCommentDto;
import com.project.backend.dto.ReactionDto;
import com.project.backend.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        CommentDto savedComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<CommentDto> sendComment(@RequestBody CommentDto commentDto,@PathVariable("id") Long gameId){
        CommentDto savedComment = commentService.sendComment(commentDto, gameId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);

    }

    @GetMapping("")
    public ResponseEntity<List<CommentDto>> getListComment(){
        List<CommentDto> comments = commentService.listComment();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/findByGame/{id}")
    public ResponseEntity<List<CommentDto>> getListCommentByGame(@PathVariable("id") Long gameId){
        List<CommentDto> comments = commentService.listCommentByGame(gameId);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/findByAccount/{id}")
    public ResponseEntity<List<CommentDto>> getListCommentByAccount(@PathVariable("id") Long accountId){
        List<CommentDto> comments = commentService.listCommentByAccount(accountId);
        return ResponseEntity.ok(comments);
    }


    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto , @PathVariable("id") Long commentId){
        CommentDto updatedComment = commentService.updateComment(commentDto,commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment is deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long commentId){
        CommentDto comments = commentService.getCommentById(commentId);
        return ResponseEntity.ok(comments);
    }







}
