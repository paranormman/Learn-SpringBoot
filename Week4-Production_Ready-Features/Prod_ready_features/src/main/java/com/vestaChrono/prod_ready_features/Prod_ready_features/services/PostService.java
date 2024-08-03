package com.vestaChrono.prod_ready_features.Prod_ready_features.services;


import com.vestaChrono.prod_ready_features.Prod_ready_features.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
