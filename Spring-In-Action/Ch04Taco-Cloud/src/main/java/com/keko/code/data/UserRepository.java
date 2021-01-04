package com.keko.code.data;

import org.springframework.data.repository.CrudRepository;

import com.keko.code.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

}
