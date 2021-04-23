package com.upgrad.phonedirectory.repository;

import com.upgrad.phonedirectory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    User findByPassword(String password);
}
