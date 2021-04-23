package com.upgrad.phonedirectory.repository;

import com.upgrad.phonedirectory.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
