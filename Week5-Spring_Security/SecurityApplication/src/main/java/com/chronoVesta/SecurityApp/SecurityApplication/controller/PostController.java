package com.chronoVesta.SecurityApp.SecurityApplication.controller;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.PostDto;
import com.chronoVesta.SecurityApp.SecurityApplication.service.PostService;
import com.chronoVesta.SecurityApp.SecurityApplication.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost) {
        return postService.createNewPost(inputPost);
    }

    @GetMapping(path = "/{postId}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN') OR hasAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping(path = "/{postId}")
    public PostDto updatePostById(@RequestBody PostDto inputPost,
                                  @PathVariable Long postId) {
        return postService.updatePostById(inputPost, postId);
    }

}
