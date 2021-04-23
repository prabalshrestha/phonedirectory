package com.upgrad.phonedirectory.service;

import com.upgrad.phonedirectory.model.Post;
import com.upgrad.phonedirectory.model.User;
import com.upgrad.phonedirectory.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;


    public void addContact(Post post) {
        postRepository.save(post);
    }

    public List<Post> getContact(User user){
        Optional<Post> post=postRepository.findById(user.getId());
        if(post.isPresent()){
            List<Post> allpost= (List<Post>) post.get();

            return allpost;
        }
        else {
            return null;
        }

    }

    public void deletePost(Integer postId) {
            postRepository.deleteById(postId);
    }
}
