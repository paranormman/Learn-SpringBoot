package com.chronoVesta.SecurityApp.SecurityApplication.controller;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.PostDto;
import com.chronoVesta.SecurityApp.SecurityApplication.service.PostService;
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
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    public PostDto createNewPost(@RequestBody PostDto inputPost) {
        return postService.createNewPost(inputPost);
    }

    @GetMapping(path = "/{postId}")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping(path = "/free-content")
    @PreAuthorize("@subscriptionService.hasAccessBasedOnSubscription(principal, 'FREE')")
    public String getFreeContent() {
        return "This is FREE content accessible for all users :- ";
    }

    @GetMapping(path = "/basic-content")
    @PreAuthorize("@subscriptionService.hasAccessBasedOnSubscription(principal, 'BASIC')")
    public String getBasicContent() {
        return "This is BASIC content accessible for premium and basic plan users :- ";
    }

    @GetMapping(path = "/premium-content")
    @PreAuthorize("@subscriptionService.hasAccessBasedOnSubscription(principal, 'PREMIUM')")
    public String getPremiumContent() {
        return "This is PREMIUM content accessible only for premium plan users :- ";
    }

}
