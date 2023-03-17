package com.rednodes.rest.webservices.restfulwebsevices.jpa;

import com.rednodes.rest.webservices.restfulwebsevices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
