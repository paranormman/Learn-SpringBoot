package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto postDto);

    PostDto getPostById(Long postId);

    PostDto updatePostById(PostDto inputPost, Long postId);

}
