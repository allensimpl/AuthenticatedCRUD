package com.simplogics.baseapplication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.simplogics.baseapplication.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findUserByEmail(String email);
}
