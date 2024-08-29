package com.chronoVesta.SecurityApp.SecurityApplication.utils;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.PostDto;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.User;
import com.chronoVesta.SecurityApp.SecurityApplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

    public boolean isOwnerOfPost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDto post =postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());
    }
}
