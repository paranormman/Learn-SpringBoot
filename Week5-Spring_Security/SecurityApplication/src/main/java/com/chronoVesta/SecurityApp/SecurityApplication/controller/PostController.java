package com.chronoVesta.SecurityApp.SecurityApplication.controller;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.PostDto;
import com.chronoVesta.SecurityApp.SecurityApplication.service.PostService;
import com.chronoVesta.SecurityApp.SecurityApplication.service.PostServiceImpl;
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

    @GetMapping(path = "/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping(path = "/{postId}")
    public PostDto updatePostById(@RequestBody PostDto inputPost,
                                  @PathVariable Long postId) {
        return postService.updatePostById(inputPost, postId);
    }

}
