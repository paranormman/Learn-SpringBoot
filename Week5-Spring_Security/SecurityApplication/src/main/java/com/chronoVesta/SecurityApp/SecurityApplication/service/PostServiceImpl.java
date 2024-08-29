package com.chronoVesta.SecurityApp.SecurityApplication.service;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.PostDto;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.PostEntity;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.User;
import com.chronoVesta.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.chronoVesta.SecurityApp.SecurityApplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
        public List<PostDto> getAllPosts(){

        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity createPost = modelMapper.map(inputPost, PostEntity.class);
        createPost.setAuthor(user);
        return modelMapper.map(postRepository.save(createPost), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("user {}", user);

        PostEntity findPostById = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not found With Id " + postId));
        return modelMapper.map(findPostById, PostDto.class);
    }

    @Override
    public PostDto updatePostById(PostDto inputPost, Long postId) {
        PostEntity oldPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not found With Id " + postId));
        inputPost.setId(postId);
        modelMapper.map(oldPost, inputPost);
//        PostEntity savePost = postRepository.save(oldPost);
        return modelMapper.map(postRepository.save(oldPost), PostDto.class);
    }
}
