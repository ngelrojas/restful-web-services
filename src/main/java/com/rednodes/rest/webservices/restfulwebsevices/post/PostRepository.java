package com.rednodes.rest.webservices.restfulwebsevices.post;

import com.rednodes.rest.webservices.restfulwebsevices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
