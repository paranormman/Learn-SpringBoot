package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.controller;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.PostDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost) {
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostDto updatePostById(@RequestBody PostDto inputPost,
                                  @PathVariable Long postId) {
        return postService.updatePostById(inputPost, postId);
    }


}
