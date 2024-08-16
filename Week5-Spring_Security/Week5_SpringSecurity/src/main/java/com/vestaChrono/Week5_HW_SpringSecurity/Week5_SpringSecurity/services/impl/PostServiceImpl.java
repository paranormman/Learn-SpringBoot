package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.impl;


import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.PostDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.PostEntity;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.exception.ResourceNotFoundException;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories.PostRepository;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto postDto) {
        PostEntity createPost = modelMapper.map(postDto, PostEntity.class);
        return modelMapper.map(createPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity findPostById = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with Id " + postId));
        return modelMapper.map(findPostById, PostDto.class);
    }

    @Override
    public PostDto updatePostById(PostDto inputPost, Long postId) {
        PostEntity oldPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with Id "+ postId));

        inputPost.setId(postId);
        modelMapper.map(oldPost, inputPost);
        return modelMapper.map(postRepository.save(oldPost), PostDto.class);
    }
}
